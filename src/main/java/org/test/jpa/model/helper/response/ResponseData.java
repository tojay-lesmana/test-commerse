package org.test.jpa.model.helper.response;

public class ResponseData<T> {

	private DefaultResponse response;
	private T data;
	
	public ResponseData() {
		this.response = new DefaultResponse();
	}

	public DefaultResponse getResponse() {
		return response;
	}

	public void setResponse(DefaultResponse response) {
		this.response = response;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
