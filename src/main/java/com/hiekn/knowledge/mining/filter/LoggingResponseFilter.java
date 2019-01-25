package com.hiekn.knowledge.mining.filter;

import com.hiekn.knowledge.mining.util.ByteUtils;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;
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
import java.text.SimpleDateFormat;

@Provider
public class LoggingResponseFilter implements ContainerResponseFilter {
    private static final Logger log = LoggerFactory.getLogger(LoggingRequestFilter.class);

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private HttpServletResponse servletResponse;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        ContainerRequest request = (ContainerRequest) requestContext;
        ContainerResponse response = (ContainerResponse) responseContext;
        Runtime runtime = Runtime.getRuntime();
        servletRequest.getSession().getId();
        log.debug("计时结束: {}  URI: {}  最大内存： {}  总内存: {}  已用内存: {}",
                new SimpleDateFormat("hh:mm:ss.SSS").format(System.currentTimeMillis()),
                request.getAbsolutePath(),
                ByteUtils.formatByteSize(runtime.maxMemory()),
                ByteUtils.formatByteSize(runtime.totalMemory()),
                ByteUtils.formatByteSize(runtime.totalMemory()-runtime.freeMemory()));
    }
}