package com.todo.common.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * 범용 단일 응답용 모델
 */
@SuppressWarnings("serial")
@JacksonXmlRootElement(localName = "response")
public class CommonDataModel<T>  extends CommonResponseModel<T> implements Serializable {
	
	private T data;

	public CommonDataModel() {
		this(null);
	}
	
	public CommonDataModel(T data) {
		this.data	= data;
	}

	@Override
	public String toString() {
		return "CommonDataModel [data=" + data + "]";
	}
	
}