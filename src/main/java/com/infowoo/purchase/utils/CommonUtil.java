package com.infowoo.purchase.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WuKong on 2018/10/25.
 */
@Slf4j
public class CommonUtil {

    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    public static void CheckNotAllNull(Collection collection,String message){
        Assert.isTrue(isContainsNoNull(collection),message);
    }


    public static Boolean isContainsNoNull(Collection collection){
        Boolean res = Boolean.FALSE;
        for(Object o:collection){
            if(o!=null){
                res = Boolean.TRUE;
                return res;
            }
        }
        return res;
    }

    public static Boolean matchAnyKey(String message , List<String> keyWords){
        if(!CollectionUtils.isEmpty(keyWords)){
            for(String keyWord:keyWords){
                if(message.contains(keyWord)){
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public static Date getTodayDate(Time time) {
        // Construct date and time objects
        Calendar dateCal = Calendar.getInstance();
        dateCal.set(Calendar.HOUR_OF_DAY,0);
        dateCal.set(Calendar.MINUTE,0);
        dateCal.set(Calendar.SECOND,0);
        dateCal.set(Calendar.MILLISECOND,0);
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(time);

        // Extract the time of the "time" object to the "date"
        dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
        dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
        dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

        // Get the time value!
        return dateCal.getTime();
    }

    public static List<String> regMatcher(String str,String regex){

        List<String> result = new ArrayList<>();

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);

        while(m.find()){
            result.add(m.group(1));
        }

        return result;
    }

    public static Boolean execCommand(String command) {

        if(StringUtils.isEmpty(command)){
            log.info("command can not be null.");
            return Boolean.FALSE;
        }
        StringTokenizer st = new StringTokenizer(command);
        String[] cmdarray = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++)
            cmdarray[i] = st.nextToken();


        ProcessBuilder pb = new ProcessBuilder("sh","-c",command);
        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();
//            Process process = Runtime.getRuntime().exec(command);
            //取出缓冲区的数据避免死锁
            processBufferData(process);
            //等待子进程完成
            int status = process.waitFor();
            if (status != 0) {
                log.info("command executed failure.command:"+command);
                return Boolean.FALSE;
            }
            process.destroy();


        }catch (InterruptedException|IOException ie){
            log.error(ie.getMessage(),ie);
        }
        return Boolean.TRUE;
    }

    /**
     * 对Process中的数据进行输入流读取
     * @param process
     */
    public static void processBufferData(Process process){
        try {
            //进程输入流
            final InputStream is = process.getInputStream();
            //进程错误输入流
            final InputStream es = process.getErrorStream();
            //对进程进行读取
            new Thread() {
                public void run() {
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    try {
                        String inputLine = null;
                        while ((inputLine = br.readLine()) != null) {
                            if (inputLine != null){
                                log.info(inputLine);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally{
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            new Thread() {
                public void  run() {
                    BufferedReader br = new  BufferedReader(new  InputStreamReader(es));
                    try {
                        String errorLine = null ;
                        while ((errorLine = br.readLine()) !=  null ) {
                            if (errorLine != null){
                                log.info(errorLine);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally{
                        try {
                            es.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }catch (Exception ex){
            ex.printStackTrace();
            try{
                process.getErrorStream().close();
                process.getInputStream().close();
                process.getOutputStream().close();
            }
            catch(Exception ee){}
        }
    }

    public static void main(String[] args) {

        System.out.println(CommonUtil.getUUID32());
    }
}
