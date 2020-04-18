package com.todo.common.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class CommmonResponseInterceptor implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse hres;

        hres	= (HttpServletResponse)response;
        hres.addHeader("Connection", "close");
        hres.addHeader("Cache-control", "no-store");
        hres.addHeader("Pragma", "no-cache");

        chain.doFilter(request, response);

    }

}
