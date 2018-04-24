package com.code.fis;

public class DIRCommandHandler implements CommandHandler{

	@Override
	public CommandRespone handle(RequestCommand command) {
		
		String responseStr=FileConnector.listOfFiles(command.getFolder());
		CommandRespone response =new CommandRespone();
		response.setResponse(responseStr);
		response.setResponseCodes(ResponseCodes.SUCCESS);
		
		return response;
	}
	

}
