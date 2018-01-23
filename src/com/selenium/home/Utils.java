package com.selenium.home;

import java.io.File;
import java.util.ArrayList;

public class Utils {

	public static ArrayList<String> listAllFileInFolder(String folderPath) {

		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				System.out.println("File " + fileName);
				if(fileName.endsWith(".properties")) {
					fileNames.add(fileName);
					System.out.println(fileName + " added to Array");
				}
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		
		return fileNames;
	}
}
