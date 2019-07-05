package com.xinghui;
/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * @Desc
 * */
public enum RespCode {

    FAIL(-100, "fail"), SUCCESS(100, "success");

    private String message ;
    private int code ;

    private RespCode(int code, String message){
        this.message = message ;
        this.code = code ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
