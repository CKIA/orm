package com.ckia.test.service.impl;

import com.ckia.test.repository.LogRepository;
import com.ckia.test.entity.LoggerDto;
import com.ckia.test.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 用户操作服务类
 * @ProjectName: testaop
 * @Author: ckia
 * @Date: 19-5-23 下午2:23
 * @Version: 1.0
 */
@Component
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;


    @Override
    public LoggerDto getLoggerDto(LoggerDto logger) {
        return logRepository.getOne(logger.getL_id());
    }

    @Override
    public void saveLoggerDto(LoggerDto logger) {
        logRepository.save(logger);
    }

    @Override
    public List<LoggerDto> getlogList() {
        return logRepository.findAll();
    }

    @Override
    public void updateLoggerDto(LoggerDto logger) {
        logRepository.saveAndFlush(logger);
    }

    @Override
    public void saveListLoggerDto(List<LoggerDto> loggers) {
        logRepository.saveAll(loggers);
    }
}
