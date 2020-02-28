package com.boot.template.common.exception;


/**
 * 业务异常
 *
 * @author nurhier
 * 2020/2/28
 **/
public class BaseBusinessException extends RuntimeException {
    private static final long serialVersionUID = 7475815868303742630L;

    public BaseBusinessException(String msg) {
        super(msg);
    }

    public BaseBusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BaseBusinessException(String msg, Object[] args) {
        super(String.format(msg, args));
    }

    public BaseBusinessException(Throwable cause) {
        super(cause);
    }

    public BaseBusinessException() {
        super();
    }

}
