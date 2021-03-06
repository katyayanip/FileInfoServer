package com.code.fis;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FileInfoClient {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
		InetAddress addr=getHost();
		if(addr==null){
			System.out.println("UnKnow host. Exiting");
			System.exit(1);
		}
		System.out.println("Addr: "+addr);
		int port=getPort(args);
		
		Socket socket=null;
		try {
			 socket= new Socket(addr,port);
			 System.out.println("socke: "+socket);
			 
			 	OutputStream outputStream = socket.getOutputStream();
				DataOutputStream dataOutput = new DataOutputStream(outputStream);
			 
			 
			Scanner input = new Scanner(System.in);
			 System.out.println("Input comnand :"); 
			 String command=null;
			 
			while (true) {
				command=input.nextLine();
				System.out.println("You entered " + command);
				dataOutput.writeUTF(command);
				
				InputStream inputStream = socket.getInputStream();
				 ObjectInputStream objectInput = new ObjectInputStream(inputStream);
				 
				
				Response res=new Response();
				
				res=(Response)objectInput.readObject();
				System.out.print(res.response);
				
				if(res.isTerminate){
					dataOutput.close();
					outputStream.close();
					objectInput.close();
					inputStream.close();
					socket.close();
					input.close();
					break;
				}
			}
			
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			System.out.println("Closing ...");
			if(socket!=null)
			socket.close();
		}
		
	}
	
	static InetAddress getHost(){
		InetAddress addr=null;
		try {
			addr = InetAddress.getByName(null);
		} catch (UnknownHostException e) {
			System.out.println("Host not found");
		}
		return addr;
	}
	
	static int getPort(String[] args){
		int port=6655;
		try {
			if(args.length==1){
				port=Integer.parseInt(args[0]);
			}
		} catch (NumberFormatException e) {
			System.out.println("PORt number error from input, default to 6655");
		}
		return port;
	}
	
	
}
