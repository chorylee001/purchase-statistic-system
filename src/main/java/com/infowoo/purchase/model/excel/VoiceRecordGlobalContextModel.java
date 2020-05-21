package com.infowoo.purchase.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by WuKong on 2018/12/27.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoiceRecordGlobalContextModel extends BaseRowModel {

    @ExcelProperty(value = "序号" ,index = 0)
    private Integer order;
    @ExcelProperty(value = "话术ID" ,index = 1)
    private String documentId;
    @ExcelProperty(value = "匹配配型" ,index = 1)
    private String matchType;
    @ExcelProperty(value = "音频链接" ,index = 2)
    private String audioUrl;
    @ExcelProperty(value = "动作" ,index = 3)
    private String action;
    @ExcelProperty(value = "匹配次数" ,index = 4)
    private Integer matchCount;
    @ExcelProperty(value = "匹配关键字" ,index = 5)
    private String matchKeyword;
    @ExcelProperty(value = "文案" ,index = 6)
    private String content;
    @ExcelProperty(value = "是否合成" ,index = 7)
    private String useCompose;
}
