package com.lin.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName Filter
 * @Author linjb
 * @Data 2019/5/22
 */

@Component
@WebFilter(urlPatterns = "/", filterName = "filter")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
//        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
//        System.out.printf("过滤器实现");
        System.out.println(((HttpServletRequest) servletRequest).getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
//        System.out.println("过滤器销毁了");
    }
}
