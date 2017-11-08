package com.xidian.spatial.exception;


import com.xidian.spatial.enumation.ResultEnum;

/**
 * 文件描述：自定义业务异常
 * 创建作者：陈苗
 * 创建时间：2017/6/3 17:59
 */
public class BusinessException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMesg());
        this.code = resultEnum.getCode();
    }
}
