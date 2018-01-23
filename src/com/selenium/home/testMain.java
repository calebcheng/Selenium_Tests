package com.selenium.home;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testMain {
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	public static final Logger LOG = LoggerFactory.getLogger(testMain.class);
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
//		File[] files = Utils.listAllFileInFolder("testdata/assetInfoValidation");
//		System.out.println(files);
		FileReader fr = new FileReader("testdata/assetInfoValidation/172.16.76.130.properties");
		Properties p = new Properties();
		p.load(fr);
		System.out.println("1");
		
		String consoleAddress = p.getProperty(Constant.PROPERTY_CONSOLE_ADDRESS);
		String hostname = p.getProperty(Constant.PROPERTY_HOSTNAME);
		String ip = p.getProperty(Constant.PROPERTY_IP);
		String processor = p.getProperty(Constant.PORPERTY_PROCESSOR);
		String creationDate = p.getProperty(Constant.PROPERTY_CREATION_DATE);
		
		
		SeleniumController sc = new SeleniumController(consoleAddress);
		System.out.println("2");
		
		sc.findElement(By.xpath("//*[@id='Asset']")).click();
		sc.findElement(By.xpath("//*[@id='" + p.getProperty(Constant.PROPERTY_IP) + "']")).click();
		
		

		System.out.println("3");
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList("Hostname", "Processor", "IP", "Creation Date"));
		ArrayList<String> actual = new ArrayList<String>();

		for(int i =1 ; i < 5; i++) {
			actual.add(sc.findElement(By.xpath("//*[@id=\"asset_header\"]/thead/tr/th[" + i + "]")).getText());
		}
		System.out.println("4");
		verifyContent(expected, actual);
		System.out.println("5");
		expected = new ArrayList<String>(Arrays.asList(hostname, processor, ip, creationDate));
		actual = new ArrayList<String>();
		for(int i =1 ; i < 5; i++) {
			actual.add(sc.findElement(By.xpath("//*[@id=\"asset_value\"]/tbody/tr[1]/td[" + i + "]")).getText());
		}
		System.out.println("6");
		verifyContent(expected, actual);
		System.out.println("7");
		LOG.info("finish test");
		sc.getDriver().close();

	}
	
	public static boolean verifyContent(ArrayList<String> expected, ArrayList<String> actual) {
		for(int i = 0; i < expected.size(); i++ ) {
			String expectedStr = expected.get(i);
			String actualStr = actual.get(i);
			
			if(!expectedStr.equals(actualStr)) {
				LOG.error("Not match!!. Expected value : {}, Actual value : {}", expectedStr, actualStr);
				return false;
			} else if(expected == null || actual == null){
				LOG.error("Null pointer detected!!");
				return false;
			} else {
				LOG.info("Value matched. Expected value : {}, Actual value : {}", expectedStr, actualStr);
			}
		}
		
		return true;
	}
	
	
}


