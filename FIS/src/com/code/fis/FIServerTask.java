package com.code.fis;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class FIServerTask implements Runnable {
	
	private String folder;
	private LinkedBlockingQueue<Socket> clientSocketQueue;
	
	private volatile boolean stop; 
	
	public FIServerTask(String folder,LinkedBlockingQueue<Socket> clientSocketQueue){
		this.folder=folder;
		this.clientSocketQueue=clientSocketQueue;
	}

	@Override
	public void run() {
		
		Socket socket =null;
		DataInputStream inputStream = null;
		
		RequestCommand request = null;
		while (!stop) {
			while (!clientSocketQueue.isEmpty()) {

				socket = (Socket) clientSocketQueue.poll();
				
				while (socket != null) {// To maintain the client connection till client quits.
					try {
						inputStream = new DataInputStream(socket.getInputStream());
						String inputCommand = inputStream.readUTF();
						System.out.println("Incoming command ..." + inputCommand);
						if(request==null){
							request=new RequestCommand(inputCommand, folder);
							Stack<String> dirStack=new Stack<String>();
							dirStack.push(folder);
							request.setDirStack(dirStack);
						}
						else{
							request.setInputStr(inputCommand);
						}

						Response response = CommandProcess.process(inputCommand, folder,request);

						if (response != null) {
							OutputStream output = socket.getOutputStream();
							ObjectOutputStream objOutput = new ObjectOutputStream(output);
							objOutput.writeObject(response);
							
							if(response.isTerminate){
								try {
									inputStream.close();
									objOutput.close();
									socket.close();
									socket=null;// Even after closing the socket, socket is not NULL.
									request=null;
								} catch (Exception e) {
									System.out.println("Socket not closed Exeception");
								}
							}
						}

					} catch (IOException e) {
						System.out.println("Input Stream error");

					}
				}
			}

		}		
				
	}
	
	public synchronized void stop()
	{
		this.stop = true;
	}

}
