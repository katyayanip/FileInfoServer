package com.code.fis;

import java.io.Serializable;

public class Response implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String response;
	boolean isTerminate;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public boolean isTerminate() {
		return isTerminate;
	}
	public void setTerminate(boolean isTerminate) {
		this.isTerminate = isTerminate;
	}
	
	

}
