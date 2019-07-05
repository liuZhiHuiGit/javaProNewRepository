package com.xinghui.handler;


import com.xinghui.AjaxRespProcessor;
import com.xinghui.ResultDto;
import com.xinghui.enums.HttpCodeEnum;
import com.xinghui.exception.CustException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * @Desc 统一异常处理类
 */

@ControllerAdvice
public class CustExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CustExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDto helper(Exception ex) {

        logger.error("OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :", ex);

        if (ex instanceof CustException) {
            if (((CustException) ex).getCode() != null) {
                return AjaxRespProcessor.fail(((CustException) ex).getCode(), ex.getMessage());
            } else {
                return AjaxRespProcessor.fail(ex.getMessage());
            }
        } else if (ex instanceof BindException) {
            BindingResult bindingResult = ((BindException) ex).getBindingResult();
            if (bindingResult != null && bindingResult.hasErrors()) {
                return AjaxRespProcessor.fail((bindingResult.getAllErrors().get(0).getDefaultMessage()));
            }
        }

        return AjaxRespProcessor.fail(HttpCodeEnum.unknow_exception.getDesc());
    }
}
