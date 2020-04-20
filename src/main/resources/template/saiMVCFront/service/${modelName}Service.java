package ${basePackage}.service;

import ${basePackage}.dto.${modelName}ReqDTO;
import ${basePackage}.dto.${modelName}DTO;

import java.util.List;

public interface ${modelName}Service extends PageService<${modelName}ReqDTO, ${modelName}DTO>{

    List<${modelName}DTO> list(${modelName}ReqDTO ${modelSmallName}ReqDTO);

}