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
public class VoiceRecordAskModel extends BaseRowModel {

    @ExcelProperty(value = "序号" ,index = 0)
    private Integer order;
    @ExcelProperty(value = "话术ID" ,index = 1)
    private String documentId;
    @ExcelProperty(value = "问题" ,index = 1)
    private String askKey;
    @ExcelProperty(value = "音频链接" ,index = 2)
    private String audioUrl;
    @ExcelProperty(value = "文案文本" ,index = 3)
    private String content;
}
