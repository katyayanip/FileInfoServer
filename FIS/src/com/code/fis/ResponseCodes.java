package com.code.fis;

public enum ResponseCodes {
	
	
	SUCCESS(200,"OK"),
	FILENOTFOUND(501,"File not found"),
	DIRECTORYNOTFOUND(502,"Directory not found"),
	INVALIDCOMMAND(500,"Invalid command. Missing Parameters"),
	UNKNOWNCOMMAND(503,"Unknown command"),
	GOODBYE(200,"Good Bye");
	
	
	private final int code;
	private final String msg;
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	private ResponseCodes(int code,String msg){
		this.code=code;
		this.msg=msg;
	}

	 @Override
	  public String toString() {
	    return code + ": " + msg;
	  }

}
