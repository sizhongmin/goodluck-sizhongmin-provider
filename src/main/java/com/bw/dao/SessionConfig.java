package com.bw.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by a on 2017/7/28.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds =86400*30)
public class SessionConfig {

}
