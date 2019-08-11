package com.ckia.test.service;

import com.ckia.test.entity.LoggerDto;

import java.util.List;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-22下午4:41
 */
public interface LogService {

    LoggerDto getLoggerDto(LoggerDto logger);

    void saveLoggerDto(LoggerDto logger);

    List<LoggerDto> getlogList();

    void updateLoggerDto(LoggerDto logger);

    void saveListLoggerDto(List<LoggerDto> loggers);
}
