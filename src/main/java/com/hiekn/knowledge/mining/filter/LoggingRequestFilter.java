package com.hiekn.knowledge.mining.filter;

import org.glassfish.jersey.server.ContainerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Provider
public class LoggingRequestFilter implements ContainerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingRequestFilter.class);

    @Context
    private HttpServletRequest servletRequest;
    @Context
    private HttpServletResponse servletResponse;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        ContainerRequest request = (ContainerRequest) requestContext;
        log.info("SessionId:{} IP：{} 访问 URI: {} ",
                servletRequest.getSession().getId(),
                servletRequest.getRemoteAddr(),
                request.getAbsolutePath());
    }
}
