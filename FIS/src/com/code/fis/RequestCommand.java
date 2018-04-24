package com.code.fis;

import java.util.Stack;

public class RequestCommand {

	private String inputStr;
	private String folder;
	private Stack<String> dirStack;
	
	public Stack<String> getDirStack() {
		return dirStack;
	}

	public void setDirStack(Stack<String> dirStack) {
		this.dirStack = dirStack;
	}

	public RequestCommand(String inputStr,String folder){
		this.inputStr=inputStr;
		this.folder=folder;
	}
	
	public String getInputStr() {
		return inputStr;
	}

	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	
	
	
	
	
	
}
