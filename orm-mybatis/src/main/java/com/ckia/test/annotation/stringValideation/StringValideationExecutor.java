package com.ckia.test.annotation.stringValideation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ckia
 * @description: 自测试使用字符串截取校验方式
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9上午4:34
 */
@Aspect
@Component
public class StringValideationExecutor {

    @Pointcut("@annotation(com.ckia.test.annotation.stringValideation.StringValidation)")
    public void pointCut(){
        System.out.println("pointCut");
    }

    @Around("pointCut()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        System.out.println("进来了");
        return null;
    }

    @Before("pointCut()")
    public void after(JoinPoint point) {
        System.out.println("after进来了");

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        for (Class clazz : argClass) {
            System.out.println(clazz);
        }
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            // 判断是否存在@DateBase注解
            if (method.isAnnotationPresent(StringValidation.class)) {
                StringValidation annotation = method.getAnnotation(StringValidation.class);
                // 取出注解中的数据源名
                String value = annotation.value();
                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
