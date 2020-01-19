package ${basePackage}.domain;

import com.sai.web.diamond.BaseDO;
import lombok.Getter;
import lombok.Setter;
<#list fieldDateTypeFullNameList as item>
        <#if item?? && item!="">
import ${item};
        </#if>
</#list>

@Setter
@Getter
public class ${modelName}DO extends BaseDO{
<#list fieldList as item>
    private ${item.fieldDataType} ${item.fieldKey};<#if item.comment?? && item.comment!="">//item.comment</#if>
</#list>
}