package com.boot.template.common.model;

import java.io.Serializable;

/**
 * @author nurhier
 * @date 2020/2/15
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5575071696467462120L;
    /**
     * 状态，-1：操作错误，0：操作失败， 1：操作成功
     */
    private int status;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * http code 编码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    private Result() {

    }

    public Result(int status, boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public Result(int status, boolean success, T data) {
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public Result(int status, boolean success, String msg, T data) {
        this.status = status;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Result(int status, boolean success, Integer code, String msg) {
        this.status = status;
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Result(int status, boolean success, Integer code, String msg, T data) {
        this.status = status;
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public enum ResultEnum {
        /**
         * 操作成功
         */
        SUCCESS(1, true, 200, "success"),
        /**
         * 操作失败
         */
        FAILURE(0, false, 400, "failure"),
        /**
         * 操作错误
         */
        ERROR(-1, false, 500, "error");
        /**
         * 状态值
         */
        private final int status;
        /**
         * 是否成功
         */
        private boolean success;
        /**
         * 错误码
         */
        private final int code;
        /**
         * 消息
         */
        private final String msg;


        ResultEnum(final int status, boolean success, final int code, final String msg) {
            this.status = status;
            this.success = success;
            this.code = code;
            this.msg = msg;
        }

        /**
         * 获取状态码
         *
         * @return 状态码
         */
        public int getStatus() {
            return status;
        }

        /**
         * 获取成功状态
         *
         * @return 是否成功
         */
        public boolean getSuccess() {
            return success;
        }

        /**
         * 获取错误码
         *
         * @return 错误码
         */
        public int getCode() {
            return this.code;
        }

        /**
         * 获取消息
         *
         * @return 消息
         */
        public String getMsg() {
            return this.msg;
        }

    }
}
