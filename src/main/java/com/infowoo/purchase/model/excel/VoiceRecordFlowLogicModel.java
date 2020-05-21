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
public class VoiceRecordFlowLogicModel extends BaseRowModel {

    @ExcelProperty(value = "序号" ,index = 0)
    private Integer order;
    @ExcelProperty(value = "匹配类型" ,index = 1)
    private String matchType;
    @ExcelProperty(value = "匹配关键字" ,index = 2)
    private String matchKeyword;
    @ExcelProperty(value = "匹配次数" ,index = 3)
    private Integer matchCount;
    @ExcelProperty(value = "前一话术ID" ,index = 4)
    private String preDocumentId;
    @ExcelProperty(value = "下一话术ID" ,index = 5)
    private String nextDocumentId;
    @ExcelProperty(value = "意图" ,index = 6)
    private String intentName;
}
