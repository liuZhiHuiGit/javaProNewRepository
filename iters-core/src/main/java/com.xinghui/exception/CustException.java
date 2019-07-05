package com.xinghui.exception;

import lombok.Data;

/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * @Desc 自定义异常
 */
@Data
public class CustException extends RuntimeException {

    private Integer code;
    private String msg;

    public CustException(Integer code) {
        this.code = code;
    }

    public CustException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
