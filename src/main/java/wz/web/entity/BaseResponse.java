package wz.web.entity;

import com.fasterxml.jackson.databind.deser.Deserializers;

/**
 * Created by zhuowang on 16/10/5.
 */
public class BaseResponse {
    private String errorCode;
    private String message;
    private Object result;

    public BaseResponse(){
        this.errorCode = "0";
        this.message = "操作成功";
        this.result = null;
    }

    public BaseResponse(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
        this.result = null;
    }

    public BaseResponse(String errorCode, String message, Object result){
        this.errorCode = errorCode;
        this.message = message;
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
