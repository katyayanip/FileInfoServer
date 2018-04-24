package com.code.fis;

public enum CommandTypes {
	
	DIR,INFO,CD,PWD,QUIT;
	
	
	public static boolean isValidCommand(String command){
		
		for(CommandTypes commandType: values()){
			
			if(commandType.name().equalsIgnoreCase(command)){
				return true;
			}
			
			if(command.equalsIgnoreCase("CD.")||command.equalsIgnoreCase("CD..") ){//Quick hack... need to change
				return true;
			}
			 
		}
		return false;
	}

}
