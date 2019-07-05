package com.xinghui.controller;


import com.xinghui.AjaxRespProcessor;
import com.xinghui.ResultDto;

/**
 * @Author chenkuanxing
 * @Date 2019/6/19
 * @Desc
 */
public abstract class BaseController<T> {

    /**
     * 失败返回
     *  @param code
     * @param message
     * @return
     */
    public ResultDto fail(Integer code, String message) {
        return AjaxRespProcessor.fail(code, message);

    }

    /**
     * 失败返回
     *
     * @param message
     * @return
     */
    public ResultDto fail(String message) {
        return AjaxRespProcessor.fail(message);

    }


    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public ResultDto<T> success(Object data) {
        return AjaxRespProcessor.success(data);
    }


    /**
     * 成功返回
     * @return
     */
    public ResultDto success(){
        return AjaxRespProcessor.success(null);
    }



}
