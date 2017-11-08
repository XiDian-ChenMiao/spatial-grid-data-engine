package com.xidian.spatial.enumation;

/**
 * 文件描述：结果枚举
 * 创建作者：陈苗
 * 创建时间：2017/6/3 18:15
 */
public enum ResultEnum {

    UNKNOW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    PRIMARY_SCHOOL(100, "小学生，别闹"),
    MIDDLE_SCHOOL(101, "中学生，好好学习");

    private Integer code;

    private String mesg;

    ResultEnum(Integer code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMesg() {
        return mesg;
    }
}
