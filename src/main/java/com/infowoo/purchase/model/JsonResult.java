package com.infowoo.purchase.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@Data
public class JsonResult implements Serializable {

    private static final String ZERO_FILL_TEMPLATE = "%04d";

    public static final Long SUCCESS_CODE = 0L;

    public static final Long ERROR_STATE_CODE = 1L;

    public static final Long EMPTY_STATE_CODE = 1L;

    public static final Long ERROR_PRIVILEGE_CODE = 1L;

    public static final Long ERROR_DATA_ACCESS_CODE = 1L;
    public static final Long SHIELDING_DIAL_TIME_CODE = 50001L;


    public static final Long NEED_FORCE_MODIFY_PASSWORD_CODE = 5L;

//----------------status 状态值

    public static final Long SUCCESS_STATUS = 1L;
    public static final Long FAIL_STATUS = -1L;

    private String msg = "";

    /**
     * 项目初始只有code代表接口状态。
     * 后面为了遵守前端规范，需要status字段代表，就又加了status值。
     */
    private Long code = 0L;

    private Long status = 1L;


    private Object data = null;


    public JsonResult(String msg, Long code, Long status, Object data) {
        this.msg = msg;
        this.code = code;
        this.status = status;
        this.data = data;
    }

    /**
     * 构造成功的JsonResult
     *
     * @param msg  String
     * @param data Object
     * @return JsonResult
     */
    public static JsonResult buildSuccessResult(String msg, Object data) {
        return new JsonResult(msg, SUCCESS_CODE,SUCCESS_STATUS, data);
    }
    public static JsonResult buildSuccessResult(String msg) {
        return new JsonResult(msg, SUCCESS_CODE,SUCCESS_STATUS, null);
    }

    /**
     * 构造状态不正确的JsonResult
     *
     * @param msg  String
     * @param data Object
     * @return JsonResult
     */
    public static JsonResult buildErrorStateResult(String msg, Object data) {
        return new JsonResult(msg, ERROR_STATE_CODE,FAIL_STATUS, data);
    }

    public static JsonResult buildErrorStateResult(String msg) {
        return new JsonResult(msg, ERROR_STATE_CODE,FAIL_STATUS, null);
    }


}
