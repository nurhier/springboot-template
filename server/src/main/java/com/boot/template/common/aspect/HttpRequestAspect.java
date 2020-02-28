package com.boot.template.common.aspect;

import com.boot.template.common.utils.RealIPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP 访问日志记录和异常处理
 *
 * @author nurhier
 * 2020/2/18
 **/
@Aspect
@Component
@Slf4j
public class HttpRequestAspect {
    /**
     * aop切入点
     */
    @Pointcut("execution(* com.boot.template.module..*.controller.*.*(..)))")
    public void pointCut() {
        // pointCut
    }

    /**
     * 切入方法
     *
     * @param pjp pjp
     * @return {@link Object}
     * @throws Throwable throwable
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra != null ? sra.getRequest() : null;
        recordPreExecutionLogs(request, pjp);
        Long startTime = System.currentTimeMillis();
        result = pjp.proceed();
        recordPostExecutionLogs(request, startTime, result);
        return result;
    }

    /**
     * 记录函数执行后日志
     *
     * @param request   http请求
     * @param startTime 函数执行开始时间
     * @param result    函数执行结果
     */
    public void recordPostExecutionLogs(HttpServletRequest request, Long startTime, Object result) {
        if (!log.isInfoEnabled()) {
            return;
        }
        try {
            String serverUri = null;
            String remoteClientAddr = null;
            if (request != null) {
                serverUri = request.getRequestURI();
                remoteClientAddr = RealIPUtils.getRemoteAddress(request);
            }
            log.info("HTTP REQUEST {} {} {} {} {}", "SS", remoteClientAddr, serverUri, System.currentTimeMillis(),
                     System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("记录HTTP请求调用后日志失败", e);
        }
    }

    /**
     * 记录函数执行前日志
     *
     * @param request http请求
     * @param pjp     ProceedingJoinPoint
     */
    public void recordPreExecutionLogs(HttpServletRequest request, ProceedingJoinPoint pjp) {
        if (!log.isInfoEnabled()) {
            return;
        }
        try {
            Object[] args = pjp.getArgs();
            String serverUri = null;
            String remoteClientAddr = null;
            if (request != null) {
                serverUri = request.getRequestURI();
                remoteClientAddr = RealIPUtils.getRemoteAddress(request);
            }
            log.info("HTTP REQUEST {} {} {} {}", "SR", remoteClientAddr, serverUri, System.currentTimeMillis());
        } catch (Exception e) {
            log.error("记录HTTP请求调用前日志失败", e);
        }
    }

}
