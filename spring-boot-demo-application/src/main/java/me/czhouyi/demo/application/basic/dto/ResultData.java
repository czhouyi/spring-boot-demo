package me.czhouyi.demo.application.basic.dto;

/**
 * ResultData
 *
 * @author czhouyi@gmail.com
 */
public class ResultData<T> extends Response {
    private static final String MSG_CODE_INTERNAL_ERROR = "500";
    private T data;

    public T getData() {
        return this.data;
    }

    public ResultData<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> success() {
        return new ResultData<>();
    }

    public static <T> ResultData<T> fail(String msgContent) {
        return fail(null, MSG_CODE_INTERNAL_ERROR, msgContent);
    }

    public static <T> ResultData<T> fail(String msgCode, String msgContent) {
        return fail(null, msgCode, msgContent);
    }

    public static <T> ResultData<T> fail(T data, String msgCode, String msgContent) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setData(data);
        resultData.setMsgCode(msgCode);
        resultData.setMsgContent(msgContent);
        return resultData;
    }

    public String toString() {
        return "ResultData{code=" + this.getCode() + ", msgCode='" + this.getMsgCode() + "', msgContent='" + this.getMsgContent() + "', data='" + this.data + "'" + '}';
    }
}
