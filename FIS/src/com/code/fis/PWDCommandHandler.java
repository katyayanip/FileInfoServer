package com.code.fis;

public class PWDCommandHandler implements CommandHandler {

	@Override
	public CommandRespone handle(RequestCommand command) {
		
		CommandRespone response=new CommandRespone();
		
			response.setResponse(command.getFolder());
			response.setResponseCodes(ResponseCodes.SUCCESS);
		
		return response;
	}

}
