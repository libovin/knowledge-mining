package com.hiekn.knowledge.mining.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class LoggingResponseFilter implements ContainerResponseFilter {
    private static final Logger log = LoggerFactory.getLogger(LoggingRequestFilter.class);

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private HttpServletResponse servletResponse;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        log.info("SessionId:{} IP: {} 访问结束, 最大内存：{} 总内存: {} 已用内存: {}",
                servletRequest.getSession().getId(),
                servletRequest.getRemoteAddr(),
                ByteUtils.formatByteSize(runtime.maxMemory()),
                ByteUtils.formatByteSize(runtime.totalMemory()),
                ByteUtils.formatByteSize(runtime.totalMemory() - runtime.freeMemory()));
    }
}
