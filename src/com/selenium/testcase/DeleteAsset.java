package com.selenium.testcase;

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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.selenium.home.Constant;
import com.selenium.home.SeleniumController;
import com.selenium.home.Utils;


public class DeleteAsset {
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	public static final Logger LOG = LoggerFactory.getLogger(DeleteAsset.class);
	
	
	private SeleniumController sc;
	private ArrayList<Properties> propertyFiles;
	private SoftAssert sa = new SoftAssert();
	
	@BeforeClass
	public void initClass() throws IOException {
		loadProperyFile();
		if(propertyFiles.size() > 0) {
			sc = new SeleniumController(propertyFiles.get(0).getProperty(Constant.PROPERTY_CONSOLE_ADDRESS));
		} else {
			Assert.assertTrue(false, "No property file found");
		}
		
	}
	
	
	private void loadProperyFile() throws IOException {
		
		propertyFiles = new ArrayList<Properties>();
		ArrayList<String> files = Utils.listAllFileInFolder(Constant.ASSET_INFO_VALIDATION_FOLDER_PATH);
		
		for(String file : files) {
			FileReader fr = new FileReader(Constant.ASSET_INFO_VALIDATION_FOLDER_PATH + "/" + file);
			Properties p = new Properties();
			p.load(fr);
			propertyFiles.add(p);
		}
	}

	@Test(priority=1)
	public void deleteAsset() {
		
		for(Properties p : propertyFiles) {
			LOG.info("Navigate to Asset tab");
			sc.findElement(By.xpath("//*[@id='Asset']")).click();
			sc.findElement(By.xpath("//*[@id='" + p.getProperty(Constant.PROPERTY_IP) + "']")).click();


			sc.findElement(By.xpath("/html/body/div[2]/div/div/div/div/a[1]")).click(); // Delete button
			
			if(sc.findElement(By.xpath("//*[@id='masthead']/div[2]/div/div/div/div/h3")) != null) { //Asset details String
				
					Boolean isPresent = sc.getDriver().findElements(By.xpath("//*[@id='" + p.getProperty(Constant.PROPERTY_IP) + "']")).size() > 0;
					sa.assertFalse(isPresent, "Check if element still exists");
			}
			
		}
		
		sa.assertAll();

		
	}
	
	private boolean verifyContent(ArrayList<String> expected, ArrayList<String> actual) {
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
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if(sc.getDriver() != null) {
			sc.getDriver().close();
		}
		
	}
	

}
