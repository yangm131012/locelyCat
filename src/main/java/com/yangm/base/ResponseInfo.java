package com.yangm.base;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class ResponseInfo<T> implements Serializable {

	private static final long serialVersionUID = -4417715614021482064L;

	private int code;
	private String message;
	private T data;

	public ResponseInfo(int code) {
		super();
		this.code = code;
	}

	public ResponseInfo(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ResponseInfo(int code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static <T> ResponseInfo<T> createBySuccess() {
		return new ResponseInfo<T>(ResponseCode.SUCCESS.getCode());
	}
	
	public static <T> ResponseInfo<T> createBySuccessMessage(String message) {
		return new ResponseInfo<T>(ResponseCode.SUCCESS.getCode(), message);
	}
	
    public static <T> ResponseInfo<T> createBySuccess(String msg,T data){
        return new ResponseInfo<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResponseInfo<T> createByError(){
        return new ResponseInfo<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }


    public static <T> ResponseInfo<T> createByErrorMessage(String errorMessage){
        return new ResponseInfo<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ResponseInfo<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new ResponseInfo<T>(errorCode, errorMessage);
    }
}
