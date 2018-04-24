package com.code.fis;

public class QUITCommandHandler implements CommandHandler{

	@Override
	public CommandRespone handle(RequestCommand command) {
		
		CommandRespone response=new CommandRespone();
		response.setResponseCodes(ResponseCodes.GOODBYE);
		response.setTerminate(true);
		return response;
	}

}
