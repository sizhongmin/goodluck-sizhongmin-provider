package com.bw.aspect;


import com.bw.bean.Log;
import com.mongodb.BasicDBObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/20.
 */
@Aspect
@Component
public class MongodbAspect {
    @Autowired
    MongoTemplate mongoTemplate;
    private Logger logger = Logger.getLogger("mongodb");

    @Pointcut("execution(public * com.bw.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取要记录的日志内容
        BasicDBObject logInfo = getBasicDBObject(request, joinPoint);
        logger.info(logInfo);
    }


    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
        Log log=new Log();
        log.setRequestURL(request.getRequestURL().toString());
        log.setRequestURI(request.getRequestURI());
        log.setQueryString(request.getQueryString());
        log.setRemoteAddr(request.getRemoteAddr());
        log.setRemoteHost(request.getRemoteHost());
        log.setRemotePort(request.getRemotePort());
        log.setLocalAddr(request.getLocalAddr());
        log.setLocalName(request.getLocalName());
        log.setMethod(request.getMethod());
        log.setHeaders(getHeadersInfo(request));
        log.setParameters(request.getParameterMap());
        log.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.setArgs(Arrays.toString(joinPoint.getArgs()));
        mongoTemplate.save(log);
        // 基本信息
        BasicDBObject r = new BasicDBObject();
        r.append("requestURL", request.getRequestURL().toString());
        r.append("requestURI", request.getRequestURI());
        r.append("queryString", request.getQueryString());
        r.append("remoteAddr", request.getRemoteAddr());
        r.append("remoteHost", request.getRemoteHost());
        r.append("remotePort", request.getRemotePort());
        r.append("localAddr", request.getLocalAddr());
        r.append("localName", request.getLocalName());
        r.append("method", request.getMethod());
        r.append("headers", getHeadersInfo(request));
        r.append("parameters", request.getParameterMap());
        r.append("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        r.append("args", Arrays.toString(joinPoint.getArgs()));
        return r;
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
