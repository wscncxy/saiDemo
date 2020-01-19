package ${basePackage}.domain;

import lombok.Data;

import java.math.BigDecimal;
import com.sai.web.dto.EditBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ${modelName}EditDTO extends EditBaseDTO{
<#list fieldList as item>
    private ${item.fieldDataType} ${item.fieldKey};<#if item.comment?? && item.comment!="">//item.comment</#if>
</#list>
}