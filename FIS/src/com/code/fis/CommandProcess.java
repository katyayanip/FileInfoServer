package com.code.fis;

public class CommandProcess {
	
	
	public static Response process(String commandStr,String serverFolder ,RequestCommand request){
		
		Response response=new Response();
		
		
		if (commandStr != null && commandStr.length() > 0) {
			
			String[] split = commandStr.split(" ");
			String command = split[0];
			CommandRespone cmdresponse = null;
			if (CommandTypes.isValidCommand(command)) {
				
				cmdresponse = new CommandHandlerFactory().getCommandHandler(command).handle(request);
				response.setResponse(cmdresponse.toString());
				response.setTerminate(cmdresponse.isTerminate());

			} else {
				cmdresponse = new CommandRespone();
				cmdresponse.setResponseCodes(ResponseCodes.UNKNOWNCOMMAND);
				response.setResponse(cmdresponse.toString());
				
			}
		
	}
		return response;
		
	}

}
