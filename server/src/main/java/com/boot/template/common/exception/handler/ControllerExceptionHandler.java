package com.boot.template.common.exception.handler;

import com.boot.template.common.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author nurhier
 * @date 2020/2/15
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, Exception exception) {
        printLog("系统异常", request, exception);
        return ResultUtils.fail("系统异常");
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        printLog("【异常】空指针异常", request, e);
        return ResultUtils.fail(HttpStatus.INTERNAL_SERVER_ERROR, "空指针异常");
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        printLog("【异常】请求参数不合法", request, e);
        return ResultUtils.fail(HttpStatus.BAD_REQUEST, "请求参数不合法");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
                                                                           HttpServletRequest request) {
        printLog("【异常】缺少请求参数", request, e);
        return ResultUtils.fail(HttpStatus.BAD_REQUEST, "缺少请求参数");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                   HttpServletRequest request) {
        printLog("【异常】参数不可用异常", request, e);
        return ResultUtils.fail(HttpStatus.BAD_REQUEST, "参数不可用");
    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSqlException(SQLException e, HttpServletRequest request) {
        printLog("【异常】数据库操作异常", request, e);
        return ResultUtils.fail(HttpStatus.INTERNAL_SERVER_ERROR, "数据库操作异常");
    }

    /**
     * printLog
     *
     * @param errorMessage 错误信息
     * @param request      request
     * @param e            异常
     * @date 2020/2/28 17:27
     */
    private void printLog(String errorMessage, HttpServletRequest request, Exception e) {
        String localAddr = "";
        String requestUri = "";
        String method = "";
        if (request != null) {
            localAddr = request.getLocalAddr();
            requestUri = request.getRequestURI();
            method = request.getMethod();
        }
        log.error("{}, 服务器地址：{}, 请求URI：{}, 请求方法：{},", errorMessage, localAddr, requestUri, method, e);
    }

}
