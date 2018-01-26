package com.selenium.home;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.testcase.addAssetManually;

public class Utils {
	
	public static final Logger LOG = LoggerFactory.getLogger(Utils.class);

	public static ArrayList<String> listAllFileInFolder(String folderPath) {

		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
//				System.out.println("File " + fileName);
				if(fileName.endsWith(".properties")) {
					fileNames.add(fileName);
					LOG.info(fileName + " added to Array");
				}
			} else if (listOfFiles[i].isDirectory()) {
				LOG.info("Directory " + listOfFiles[i].getName());
			}
		}
		
		return fileNames;
	}
}
