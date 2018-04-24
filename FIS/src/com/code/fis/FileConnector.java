package com.code.fis;

import java.io.File;

public class FileConnector {

	private static final String TYPE="Type";
	private static final String CREATED="CREATED";
	private static final String SIZE ="Size";
	private static final String DIRECTORY ="Directory";
	private static final String FILE ="File";
	private static final String NEWLINE ="\n";
	
	public static String listOfFiles(){
		
		File folder = new File("C:\\Users\\katyayani\\Desktop");
		File[] listOfFiles = folder.listFiles();
		StringBuilder s=new StringBuilder();
		for (File file : listOfFiles) {
		 // if (file.isFile()) {
				s.append(file.getName());
				s.append("\n");
		      //  System.out.println(file.getName());
		  //  }
		}
		return s.toString();
	}
	
	public static String listOfFiles(String folder) {

		StringBuilder s = new StringBuilder();

		if (folder != null) {
			
			File filefolder = new File(folder);
			File[] listOfFiles = filefolder.listFiles();
			
			if(listOfFiles!=null){
				for (File file : listOfFiles) {
					s.append(file.getName());
					s.append("\n");
				}
			}

			
		}

		return s.toString();

	}
	
	static String getFileInfo(String fileName,String folder){
		
		StringBuilder fileInfo=new StringBuilder();
		
		File filefolder = new File(folder);
		File[] listOfFiles = filefolder.listFiles();
		if(listOfFiles!=null){
			for (File file : listOfFiles) {
				   if ( file.getName().equalsIgnoreCase(fileName)) {	
					   fileInfo.append(TYPE+ ": ");
					   if(file.isDirectory()){
						   fileInfo.append(DIRECTORY);
						   fileInfo.append(NEWLINE);
					   }else{
						   fileInfo.append(FILE);
						   fileInfo.append(NEWLINE);
						   fileInfo.append(SIZE+ ": ");
						   fileInfo.append(file.length());
						   fileInfo.append(NEWLINE);
					   }
					   fileInfo.append(CREATED+": ");
					   fileInfo.append(file.lastModified());
					   break;
				    }
				}
		}
	
		
		return fileInfo.toString();
		
	}
	
	static boolean isDirectoryExists(String dir,String currentDir){
		
		File filefolder = new File(currentDir);
		File[] listOfFiles = filefolder.listFiles();
		
		if(listOfFiles!=null && listOfFiles.length>0){
			for (File file : listOfFiles) {
				   if ( file!=null && file.isDirectory() && file.getName().equalsIgnoreCase(dir)) {
					   return true;
				   }
			}
		}
		return false;
		
	}
	
	
}
