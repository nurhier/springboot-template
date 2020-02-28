package com.boot.template.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 启用 spring retry
 *
 * @author nurhier
 * @date 2020/2/21
 */
@Configuration
@EnableRetry
public class RetryConfig {
}
