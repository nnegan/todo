package com.todo.common.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * 응답 규격화 객체
*/
@SuppressWarnings("serial")
@JacksonXmlRootElement(localName = "response")
public class CommonResponseModel<T>
	implements Serializable {
	
	private String		returnCode;
	private String		message;
	private T			data;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
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
	@Override
	public String toString() {
		return "CommonResponseModel [returnCode=" + returnCode + ", message=" + message + " data=" + data + "]";
	}
}
