package com.danny.util;

/**
 * @author huyuyang@lxfintech.com
 * @Title: CommonResult
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-20 17:11:42
 */
public class CommonResult {
    final String SUCCESS="success";
    final String ERROR="error";
    private String count="";
    /* 响应数据 */
    private Object data;
    /* 响应状态 */
    private Boolean status=true;
    /* 响应消息 */
    private String message;

    public CommonResult(){}

    public CommonResult(Object data){
        this.data=data;
    }

    public CommonResult(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    public CommonResult(String message, Boolean status, String count, Object data) {
        this.set(message, status);
        this.data = data;
        this.count=count;
    }



    public void set(String message, Boolean status) {
        this.status = status;
        this.message = message;
        this.count="";
        this.data="";
    }

    public void set(String message, Boolean status,String count, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.count=count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
        this.message=this.status?"成功":"失败";
        this.count="";
        this.data="";
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*public String getCommonJson(){
        return JsonGenerateUtil.getCommonJson(status==true?SUCCESS:ERROR, message, count==null?"":count, data==null?"":data);
    }*/

}
