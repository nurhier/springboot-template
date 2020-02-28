package com.boot.template.common.utils;

import com.boot.template.common.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author nurhier
 * @date 2020/2/15
 */
public class ResultUtils {
    public static ResponseEntity<?> success() {
        return success(null, null);
    }

    public static <T> ResponseEntity<?> success(T object) {
        return success(object, null);
    }

    public static <T> ResponseEntity<?> success(T object, String message) {
        HttpStatus httpStatus = HttpStatus.OK;
        String msg = StringUtils.isNotEmpty(message) ? message : Result.ResultEnum.SUCCESS.getMsg();
        Result<T> resultData = new Result<>(Result.ResultEnum.SUCCESS.getStatus(),
                                            Result.ResultEnum.SUCCESS.getSuccess(),
                                            httpStatus.value(),
                                            msg, object);
        return new ResponseEntity<>(resultData, httpStatus);
    }

    public static ResponseEntity<?> fail() {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public static ResponseEntity<?> fail(HttpStatus httpStatus) {
        return fail(httpStatus, null);
    }

    public static ResponseEntity<?> fail(String message) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static ResponseEntity<?> fail(HttpStatus httpStatus, String message) {
        String msg = StringUtils.isNotEmpty(message) ? message : Result.ResultEnum.SUCCESS.getMsg();
        HttpStatus httpStatus1 = httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus;
        Result<?> resultVo = new Result<>(Result.ResultEnum.FAILURE.getStatus(),
                                          Result.ResultEnum.FAILURE.getSuccess(),
                                          httpStatus1.value(), msg);
        return new ResponseEntity<>(resultVo, httpStatus1);
    }
}
