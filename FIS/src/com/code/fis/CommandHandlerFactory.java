package com.code.fis;

public class CommandHandlerFactory {
	
	
	public CommandHandler getCommandHandler(String command ){
		
		if(command!=null){
			
			if(command.equalsIgnoreCase(CommandTypes.DIR.name())){
				return new DIRCommandHandler();
			}
			else if(command.equalsIgnoreCase(CommandTypes.INFO.name())){
				return new INFOCommandHandler();
			}
			else if(command.equalsIgnoreCase(CommandTypes.CD.name())){
				return new CDCommandHandler();
			}
			else if(command.equalsIgnoreCase("CD.")){
				return new CDCommandHandler();
			}
			else if(command.equalsIgnoreCase("CD..")){
				return new CDCommandHandler();
			}
			
			else if(command.equalsIgnoreCase(CommandTypes.PWD.name())){
				return new PWDCommandHandler();
			}
			else if(command.equalsIgnoreCase(CommandTypes.QUIT.name())){
				return new QUITCommandHandler();
			}
			else { 
				return null;
			}
		}
		
		return null;
	}
	

}
