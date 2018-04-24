package com.code.fis;

public class INFOCommandHandler implements CommandHandler{

	@Override
	public CommandRespone handle(RequestCommand command) {
		
		CommandRespone response=new CommandRespone();
		
		if(command!=null){
			
			String[] split =command.getInputStr().split(" ");
			
			if(split.length==2){
				
				String fileInfo=FileConnector.getFileInfo(split[1], command.getFolder());
				
				if(fileInfo!=null && !fileInfo.isEmpty()){
					response.setResponse(fileInfo);
					response.setResponseCodes(ResponseCodes.SUCCESS);
				}else{
					response.setResponseCodes(ResponseCodes.FILENOTFOUND);
				}
				
			}else if(split.length==1){
				response.setResponseCodes(ResponseCodes.INVALIDCOMMAND);
			}
			
		}
		
		return response;
	}

}
