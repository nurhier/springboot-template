package com.boot.template.common.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author nurhier
 * @date 2020/2/28
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "master")
@PropertySource(value = "classpath:redis.yml")
public class RedisConfig {
    /**
     * ip地址
     */
    @Value("${ip}")
    private String ip;
    /**
     * 端口
     */
    @Value("${port}")
    private Integer port;
}
