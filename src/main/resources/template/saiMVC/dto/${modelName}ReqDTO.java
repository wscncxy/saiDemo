package ${basePackage}.domain;

import lombok.Data;

import java.math.BigDecimal;
import com.sai.web.dto.ReqBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ${modelName}ReqDTO extends ReqBaseDTO{
<#list fieldList as item>
    <#if item.fieldDataType == 'Date' >
        private ${item.fieldDataType} ${item.fieldKey}Start;<#if item.comment?? && item.comment!="">//item.comment开始</#if>
        private ${item.fieldDataType} ${item.fieldKey}End;<#if item.comment?? && item.comment!="">//item.comment结束</#if>
    <#else>
        private ${item.fieldDataType} ${item.fieldKey};<#if item.comment?? && item.comment!="">//item.comment</#if>
    </#if>
</#list>
}