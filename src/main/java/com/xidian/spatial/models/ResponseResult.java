package com.xidian.spatial.models;

/**
 * 文件描述：统一消息结果对象
 * 创建作者：陈苗
 * 创建时间：2017/6/3 16:38
 */
public class ResponseResult<T> {
    /**
     * 提示码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public ResponseResult() {

    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success(Object obj) {
        return new ResponseResult(0, "成功", obj);
    }

    public static ResponseResult success() {
        return new ResponseResult(0, "成功", null);
    }

    public static ResponseResult failure(Integer code, String mesg) {
        return new ResponseResult(code, mesg, null);
    }
}
