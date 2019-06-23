package com.ckia.test.service.impl;

import com.ckia.test.mapper.LogMapper;
import com.ckia.test.mapper.UserMapper;
import com.ckia.test.pojo.LoggerDto;
import com.ckia.test.pojo.UserDto;
import com.ckia.test.service.LogService;
import com.ckia.test.service.UserService;
import com.ckia.test.utils.GenerateStringUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private LogMapper logMapper;


    @Override
    public LoggerDto getLoggerDto(LoggerDto logger) {
        return logMapper.getLoggerDto(logger);
    }

    @Override
    public void saveLoggerDto(LoggerDto logger) {
        logMapper.saveLoggerDto(logger);
    }

    @Override
    public List<LoggerDto> getlogList() {
        return logMapper.getlogList();
    }

    @Override
    public void updateLoggerDto(LoggerDto logger) {
        logMapper.updateLoggerDto(logger);
    }

    @Override
    public void saveListLoggerDto(List<LoggerDto> loggers) {
        logMapper.saveListLoggerDto(loggers);
    }
}
