package com.szjzht.admin.common.constant;



/**
 * 通用返回对象
 * @Auther: mayn
 * @Date: 2019/9/5 13:52
 * @Description:
 */
public class Result {

    private int code;

    private String msg;
    //业务是否成功处理
    private boolean success;
    //数据对象
    private Object data;



    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("请求成功");
        result.setSuccess(true);
        result.setData(null);
        return  result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("请求成功");
        result.setSuccess(true);
        result.setData(data);
        return  result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
