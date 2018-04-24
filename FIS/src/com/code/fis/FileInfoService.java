package com.code.fis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class FileInfoService {
	
	private ServerSocket serverSocket;
	
	private FIServerTask[] tasks=null ;
	
	private int numOfServerThreads;
	
	Scanner scanner;
	
	private LinkedBlockingQueue<Socket> clientSocketQueue=null; 

	public FileInfoService(int port,String serverFolder,int numOfServerThreads){
		
		this.numOfServerThreads=numOfServerThreads;
		tasks=new FIServerTask[numOfServerThreads];
		clientSocketQueue=new LinkedBlockingQueue<Socket>();
		startServer(port,serverFolder);
		
	}
	
	private void startServer(int port, String serverFolder) {

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e1) {
			System.err.println("Something went wrong. Server could not start. Try again");
			System.exit(1);
		}
		System.out.println("Server Started on the port  : " + port);
		ExecutorService exec = Executors.newFixedThreadPool(numOfServerThreads);

		for (int i = 0; i < numOfServerThreads; i++) {
			tasks[i] = new FIServerTask(serverFolder, clientSocketQueue); 
																			
		}
		AddToQueue que = new AddToQueue(serverSocket, clientSocketQueue); // Add the client requests to Queue
		que.start();

		try {

			for (int j = 0; j < tasks.length; j++) {
				exec.execute(tasks[j]);
			}
			exec.shutdown();

			String serverCommand = "";
			scanner = new Scanner(System.in);

			while (!serverCommand.equalsIgnoreCase("stop")) {// Stop server only when the "Stop" input comes.
				serverCommand = scanner.nextLine().toLowerCase();
			}
			que.stopQueing();
			for (int i = 0; i < tasks.length; i++) {
				tasks[i].stop();
			}

			try {
				if (serverSocket != null)
				serverSocket.close();
				System.out.println("closing server socket");
			} catch (IOException e) {
				System.out.println("exception server socket closing");
			}
			System.out.println("server socket closed");

			System.exit(0);

		} finally {
			try {
				if (serverSocket != null)
					serverSocket.close();
			} catch (IOException e) {
				System.out.println("exception server socket closing");
			}
		}

	}
	
	public static void main(String[] args) throws IOException {

		if (args.length == 3) {
			
			int port = ServerArgumentsValidator.getPort(args[0]);
			String folder = args[1];
			
			int numOfServerThreads=ServerArgumentsValidator.getPort(args[2]);
			
			if(numOfServerThreads==0){
				numOfServerThreads=10;
			}
		
			
			boolean isValidFolder = ServerArgumentsValidator.isValidFolder(folder);
			
			if (port > 0 && isValidFolder) {
				//Creating the server
				new FileInfoService(port, folder,numOfServerThreads);  
				
			} else {
				System.out.println("Please check the port number and remote server directory path. Port : " + args[0]
						+ "Folder : " + args[1]);
			}
		} else {
			System.out.println("Please enter Port and remote server directory path");
		}
	}
	
	
	
}
