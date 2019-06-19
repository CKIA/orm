package com.ckia.test.annotation.stringValideation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ckia
 * @description: 代理类的基础，必须先是设置对象（必须是接口对象赋值实现类），不然获取的代理对象会空指针。
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-9下午2:39
 */
public class ProxyBase implements InvocationHandler {
    public static final String BEFORE_STRING = "before";
    public static final String AFTER_STRING = "after";
    // 是否代理所有方法
    private boolean isAllProxy = false;
    // 被代理对象
    private Object srcObj;
    // 具体代理的方法集合
    private List<Method> proxyMethodList;// 代理的方法集合

    static class ProxyHelp {
        private static final Class<JsProxy> JsProxy = JsProxy.class;

        @SuppressWarnings("unchecked")
        // 强转对象
        public static <T> T parseObject(Object srcObj, Class<T> srcClass) {
            return (T) srcObj;
        }

        // 获取代理对象，或是原生对象
        public static <T> T getInstance(T srcObj) {
            T resultObject = null;
            Class<? extends Object> tclass = srcObj.getClass();
            if (tclass.getAnnotation(ProxyHelp.JsProxy) != null) {
                resultObject = trySetProxy(srcObj, Arrays.asList(tclass.getMethods()), tclass.getAnnotation(ProxyHelp.JsProxy), true);
                if (resultObject != null) {
                    return resultObject;
                }
            }
            Method[] methods = srcObj.getClass().getMethods();
            List<Method> methodList = new ArrayList<>();
            JsProxy myproxy = null;
            for (Method tempMethod : methods) {
                if (tempMethod.getAnnotation(ProxyHelp.JsProxy) != null) {
                    if (myproxy == null) {
                        myproxy = tempMethod.getAnnotation(ProxyHelp.JsProxy);
                    }
                    if (myproxy.annotationType().getName().equals(tempMethod.getAnnotation(ProxyHelp.JsProxy).annotationType().getName())) {
                        methodList.add(tempMethod);
                    }
                }
            }
            resultObject = trySetProxy(srcObj, methodList, myproxy, false);
            if (resultObject != null) {
                return resultObject;
            }
            return srcObj;
        }

        // 尝试获取代理
        @SuppressWarnings("unchecked")
        private static <T> T trySetProxy(T srcObj, List<Method> methodList, JsProxy myproxy, boolean isClassAnno) {
            try {
                Object proxyObj = myproxy.value().newInstance();
                if (proxyObj instanceof ProxyBase) {
                    ProxyBase proxyBase = (ProxyBase) proxyObj;
                    proxyBase.srcObj = srcObj;
                    proxyBase.isAllProxy = isClassAnno;
                    proxyBase.proxyMethodList = methodList;
                    Object tempObj = Proxy.newProxyInstance(srcObj.getClass().getClassLoader(), srcObj.getClass().getInterfaces(), proxyBase);
                    return (T) tempObj;// 代理对象必然能够强转
                }
            } catch (InstantiationException | IllegalAccessException e) {
            }
            return null;
        }
    }

    /**
     * <Pre>
     * 获取对象的代理：对象必须是接口对象，注解必须是ProxyBase的子类
     * java.lang.ClassCastException:错误则是对象不能转化为
     * </pre>
     */
    public static <T> T getInstance(T t) {
        return ProxyHelp.getInstance(t);
    }

    public ProxyBase() {
    }

    /**
     * 重写的方法
     */
    public void afterAction() {
    }

    /**
     * 重写的方法
     */
    public void beforeAction() {
    }

    private Method getMethod(List<Method> proxyMethod2, Method method) {
        for (Method m : proxyMethod2) {
            if (m.getName().equals(method.getName())) {// 比较参数是否一一对应
                Class<?>[] paramTypes1 = m.getParameterTypes();
                Class<?>[] paramTypes2 = method.getParameterTypes();
                if (paramTypes1.length == paramTypes2.length && paramTypes1.length == 0) {
                    return m;
                } else if (paramTypes1.length == paramTypes2.length) {
                    for (int i = 0; i < paramTypes2.length; i++) {
                        if (paramTypes1[i] == paramTypes2[i]) {
                            return null;
                        }
                        if (i == paramTypes2.length - 1) {
                            return m;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 用来获取被代理对象,用来强转,使用请注意类型。
     */
    public <T> T getObj(Class<T> t) {
        return ProxyHelp.parseObject(srcObj, t);
    }

    // 代理
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isAllProxy) {
            JsProxy myProxy = srcObj.getClass().getAnnotation(ProxyHelp.JsProxy);
            return runInvoke(method, args, myProxy);
        } else {
            Method tempMethod = getMethod(proxyMethodList, method);
            if (tempMethod != null) {
                return runInvoke(method, args, tempMethod.getAnnotation(ProxyHelp.JsProxy));
            } else {
                return method.invoke(srcObj, args);
            }
        }
    }

    private Object runInvoke(Method method, Object[] args, JsProxy myProxy) throws IllegalAccessException, InvocationTargetException {
        Object resultObj;
        if (myProxy != null) {
            ProxyType[] proxyLocation = myProxy.type();
            for (ProxyType proxyType : proxyLocation) {
                if (AFTER_STRING.equals(proxyType.value)) {
                    beforeAction();
                }
            }
            resultObj = method.invoke(srcObj, args);
            for (ProxyType proxyType : proxyLocation) {
                if (BEFORE_STRING.equals(proxyType.value)) {
                    afterAction();
                }
            }
        } else {
            resultObj = method.invoke(srcObj, args);
        }
        return resultObj;
    }

}