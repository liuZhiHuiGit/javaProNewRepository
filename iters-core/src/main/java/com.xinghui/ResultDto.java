package com.xinghui;

import lombok.Data;

/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * @Desc
 */

@Data
public class ResultDto<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

}
