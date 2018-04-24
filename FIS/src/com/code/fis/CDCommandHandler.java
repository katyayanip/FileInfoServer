package com.code.fis;

import java.util.Stack;

public class CDCommandHandler implements CommandHandler {

	@Override
	public CommandRespone handle(RequestCommand command) {
		
		CommandRespone response =new CommandRespone();
		
		String[] split=command.getInputStr().split(" ");
		Stack<String> dirs = command.getDirStack();
		if (split != null && split.length == 2) {
			
			
			if (FileConnector.isDirectoryExists(split[1], command.getFolder())) {
				String newFolder=command.getFolder()+"\\"+split[1];
				dirs.push(command.getFolder());
				command.setFolder(newFolder);
				response.setResponseCodes(ResponseCodes.SUCCESS);
				response.setResponse("Current directory is "+ split[1] );
			}else{
				response.setResponseCodes(ResponseCodes.DIRECTORYNOTFOUND);
			}
		} else if (split != null && split.length == 1) {
			
			if(split[0].contains("..")){
				if(!dirs.isEmpty()){
					String previous=dirs.pop();
					command.setFolder(previous);
					response.setResponseCodes(ResponseCodes.SUCCESS);
					response.setResponse("Current directory is "+previous );
				}
			}
			else if(split[0].contains(".")){
				response.setResponseCodes(ResponseCodes.SUCCESS);
				response.setResponse("Current directory is "+ command.getFolder());
			}
			else{
				response.setResponseCodes(ResponseCodes.INVALIDCOMMAND);
			}
			
		}
		
		
		
		return response;
	}

}
