package me.czhouyi.demo.application.basic.dto;

public class Response extends DTO {
    private static final long serialVersionUID = 1L;
    private int code = 200;
    private String msgCode = "200";
    private String msgContent = "success";

    public Response() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsgCode() {
        return this.msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setMsgCode(errCode);
        response.setMsgCode(errMessage);
        return response;
    }

    public static Response buildSuccess() {
        Response response = new Response();
        return response;
    }

    public String toString() {
        return "Response{code=" + this.code + ", msgCode='" + this.msgCode + '\'' + ", msgContent='" + this.msgContent + '\'' + '}';
    }
}