package ${basePackage}.domain;

import lombok.Data;

import java.math.BigDecimal;
import com.sai.web.diamond.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ${modelName}DTO extends BaseDO{

    public ${modelName}DTO(){}

    public ${modelName}DTO(${modelName}DO ${modelSmallName}){
        <#list fieldList as item>
            set${item.fieldUpKey}(${modelSmallName}.get${item.fieldUpKey}());
        </#list>
    }
<#list fieldList as item>
    private ${item.fieldDataType} ${item.fieldKey};<#if item.comment?? && item.comment!="">//item.comment</#if>
</#list>

}