package ${basePackage}.domain;

import java.util.Date;
import java.math.BigDecimal;

public class ${modelName} {
<#list fieldList as item>
    private ${item.fieldDataType} ${item.fieldKey};<#if item.comment?? && item.comment!="">//item.comment</#if>
</#list>
<#list fieldList as item>

    public void set${item.fieldUpKey}(${item.fieldDataType} ${item.fieldKey}){
        this.${item.fieldKey}=${item.fieldKey};
    }

    public ${item.fieldDataType} get${item.fieldUpKey}(){
        return this.${item.fieldKey};
    }
</#list>
}