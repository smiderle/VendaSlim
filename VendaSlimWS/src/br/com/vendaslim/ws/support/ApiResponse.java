package br.com.vendaslim.ws.support;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
		
	private static final long serialVersionUID = -5584906004219908759L;
	public static String STATUS_SUCCESS = "SUCCESS";
	public static String STATUS_FAILURE = "FAILURE";

	private String status = STATUS_SUCCESS;
	private String code = "200";
	private String message = "Request processed successfully";

	private T result;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	
}