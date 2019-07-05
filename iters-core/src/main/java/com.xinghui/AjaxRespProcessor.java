package com.xinghui;

/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * @Desc Ajax返回处理类
 */
public class AjaxRespProcessor {

    /**
     * 失败返回
     *
     * @param errMsg
     * @return
     */
    public static ResultDto fail(String errMsg) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(RespCode.FAIL.getCode());
        resultDto.setMessage(errMsg);
        return resultDto;
    }

    /**
     * 自定义返回码
     *
     * @param code
     * @param errMsg
     * @return
     */
    public static ResultDto fail(Integer code, String errMsg) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(errMsg);
        return resultDto;
    }

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public static ResultDto success(Object data) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(RespCode.SUCCESS.getCode());
        resultDto.setMessage(RespCode.SUCCESS.getMessage());
        resultDto.setData(data);
        return resultDto;
    }

}
