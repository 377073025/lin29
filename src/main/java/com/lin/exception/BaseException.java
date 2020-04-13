package com.lin.exception;

import com.lin.util.MessageUtils;
import org.springframework.util.StringUtils;

/**
 * @author lin
 * @version 1.0
 * @date 2020/3/29 22:33
 */
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String msg;

    public BaseException(String code, Object[] args, String msg) {
        this.code = code;
        this.args = args;
        this.msg = msg;
    }

    public BaseException(String code, Object[] args) {
        this(code, args, null);
    }

    public BaseException(String msg) {
        this(null, null, msg);
    }

    public BaseException(String code, String msg) {
        this(code, null, msg);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = msg;
        }
        return message;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getMsg() {
        return msg;
    }

}
