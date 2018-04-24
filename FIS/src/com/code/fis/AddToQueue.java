package com.code.fis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class AddToQueue extends Thread{
	
	private ServerSocket serverSocket;
	private LinkedBlockingQueue<Socket> clientSocketQueue;
	
	private volatile boolean stop ;
	
	public AddToQueue(ServerSocket serverSocket,LinkedBlockingQueue<Socket> clientSocketQueue){
		this.serverSocket=serverSocket;
		this.clientSocketQueue=clientSocketQueue;
	}
	
	public void run(){
		
		System.out.println("Adding to queee...");
		
		 while(!stop)
		 {
			 try {
				 clientSocketQueue.offer(serverSocket.accept());
			} catch (IOException e) {
				System.out.println( "Something get wrong");
			}
		 }
		 System.out.println("Queue stopped");
		
	}
	
	 public synchronized void stopQueing()
	 {
		 this.stop = true;
		 try {
				serverSocket.close();
				System.out.println(" Closing server socket");
			} catch (IOException e) {
				System.out.println(" Failing closing socket");
			}
	 }
	
	
	

}
