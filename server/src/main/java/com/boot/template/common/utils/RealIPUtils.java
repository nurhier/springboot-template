package com.boot.template.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nurhier
 * @date 2020/2/28
 */
public class RealIPUtils {
    private RealIPUtils() {}

    /**
     * 获取request客户端IP
     *
     * @param request request
     * @return java.lang.String
     * @date 2020/2/18 17:14
     */
    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real_Ip");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
