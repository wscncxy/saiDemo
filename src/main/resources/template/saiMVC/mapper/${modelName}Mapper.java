package ${basePackage}.mapper;

import ${basePackage}.domain.${modelName};

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public interface ${modelName}Mapper {

    int insert(${modelName} ${modelSmallName});

    ${modelName} selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKey(${modelName} ${modelSmallName});

    List<${modelName}> selectList(Map<String, Object> param);
}