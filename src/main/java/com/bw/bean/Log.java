package com.bw.bean;

import lombok.Data;

import java.util.Map;

/**
 * Created by admin on 2017/7/20.
 */
@Data
public class Log {
 private String requestURL;
 private String requestURI;
 private String queryString;
 private String remoteAddr;
 private String remoteHost;
 private Integer remotePort;
 private String localAddr;
 private String localName;
 private String method;
 private Map<String,String> headers;
 private Map<String,String[]> parameters;
 private String classMethod;
 private String args;


}
