package com.ckia.test.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ckia
 * @description: TODO
 * @title: ckia学习累积使用
 * @projectName orm
 * @date 19-6-22上午3:43
 */
public class DBFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request);
        System.out.println(response);
        System.out.println(chain);
    }
}
