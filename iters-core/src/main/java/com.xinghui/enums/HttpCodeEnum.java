package com.xinghui.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * 可用状态枚举
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HttpCodeEnum implements IEnum<Integer> {
    /**
     * 成功
     */
    sucess(100, "成功."),
    /**
     * 失败
     */
    FAIL(-100, "失败."),
    /**
     * 未登录
     */
    deny(401,"请重新登录."),

    /**
     * 权限不足
     */
    Forbidden(403,"权限不足"),

    unknow_exception(1003, "系统未响应，请联系管理员陈宽星.");

    private Integer value;
    private String desc;

    HttpCodeEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }
}
