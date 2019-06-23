package com.ckia.test.mapper;

import com.ckia.test.pojo.LoggerDto;

import java.util.List;

/**
 * @author ckia
 * @description: mybatis查询服务
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午2:54
 */
//@Mapper
public interface LogMapper {

    LoggerDto getLoggerDto(LoggerDto logger);

    void saveLoggerDto(LoggerDto logger);

    List<LoggerDto> getlogList();

    void updateLoggerDto(LoggerDto logger);

    void saveListLoggerDto(List<LoggerDto> loggers);
}
