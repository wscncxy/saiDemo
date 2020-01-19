package ${basePackage}.mapper;

import ${basePackage}.domain.${modelName}DO;
import ${basePackage}.dto.${modelName}ReqDTO;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public interface ${modelName}Mapper {

    int insert(${modelName}DO ${modelSmallName});

    ${modelName}DO selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKey(${modelName}DO ${modelSmallName});

    List<${modelName}DO> selectList(Map<String, Object> param);
}