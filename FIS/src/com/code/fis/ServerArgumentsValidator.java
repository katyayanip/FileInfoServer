package com.code.fis;

import java.io.File;

public class ServerArgumentsValidator {
	
	
	public static int getPort(String portStr) {
		int port = 0;
		try {
			port = Integer.parseInt(portStr);
		} catch (NumberFormatException e) {
			//
		}
		return port;
	}
	
	public static boolean isValidFolder(String remotefolder){
		
		boolean isValid=true;
		
		File folder = new File(remotefolder);
		File[] listOfFiles = folder.listFiles();
		
		isValid=listOfFiles.length>0 ? true: false;
		
		return isValid;
		
	}

}
