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

import com.selenium.home.Constant;
import com.selenium.home.SeleniumController;
import com.selenium.home.Utils;


public class assetInfoValidation {
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	public static final Logger LOG = LoggerFactory.getLogger(assetInfoValidation.class);
	
	
	private SeleniumController sc;
	private ArrayList<Properties> propertyFiles;
	private SoftAssert sa = new SoftAssert();
	
	@BeforeClass
	public void initClass() throws IOException {
		LOG.info("Start testing");
		loadProperyFile();
		if(propertyFiles.size() > 0) {
			sc = new SeleniumController(propertyFiles.get(0).getProperty(Constant.PROPERTY_CONSOLE_ADDRESS));
			LOG.info("Browser in opened");
		} else {
			Assert.assertTrue(false, "No property file found");
		}
		signIn();
	}
	
	private void signIn() {
		//*[@id="signIn_userName"]//*[@id="signIn_password"]
		sc.findElement(By.id("signIn_userName")).sendKeys("admin");;
		sc.findElement(By.id("signIn_password")).sendKeys("admin");;
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
	public void validateAssetDetails() {
		for(Properties p : propertyFiles) {
			
			sc.findElement(By.xpath("//*[@id='Asset']")).click();
			sc.findElement(By.xpath("//*[@id='" + p.getProperty(Constant.PROPERTY_IP) + "']")).click();
			ArrayList<String> expected = new ArrayList<String>(Arrays.asList("Hostname", "Processor", "IP", "Creation Date"));
			ArrayList<String> actual = new ArrayList<String>();

			for(int i =1 ; i < 5; i++) {
				actual.add(sc.findElement(By.xpath("//*[@id=\"asset_header\"]/thead/tr/th[" + i + "]")).getText());
			}
			verifyContent(expected, actual);
			
			expected = new ArrayList<String>(Arrays.asList(p.getProperty(Constant.PROPERTY_HOSTNAME), p.getProperty(Constant.PORPERTY_PROCESSOR), p.getProperty(Constant.PROPERTY_IP), p.getProperty(Constant.PROPERTY_CREATION_DATE)));
			actual = new ArrayList<String>();
			for(int i =1 ; i < 5; i++) {
				actual.add(sc.findElement(By.xpath("//*[@id=\"asset_value\"]/tbody/tr[1]/td[" + i + "]")).getText());
			}
			verifyContent(expected, actual);
			sc.findElement(By.xpath("/html/body/div[2]/div/div/div/div/a[2]")).click(); // Back button
		}
		sa.assertAll();

		
	}
	
	private void verifyContent(ArrayList<String> expected, ArrayList<String> actual) {
		LOG.info("Verify the content of the asset");
		for(int i = 0; i < expected.size(); i++ ) {
			String expectedStr = expected.get(i);
			String actualStr = actual.get(i);
			
			if(!expectedStr.equals(actualStr)) {
				LOG.error("Not match!!. Expected value : {}, Actual value : {}", expectedStr, actualStr);
				sa.assertTrue(false, "Acrual value : " + actualStr + " does not match with expected value : " + expectedStr);
				
			} else if(expected == null || actual == null){
				LOG.error("Null pointer detected!!");
				sa.assertTrue(false, "Null pointer detected!!");
				
			} else {
				LOG.info("Value matched. Expected value : {}, Actual value : {}", expectedStr, actualStr);
			}
		}
		
	
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if(sc.getDriver() != null) {
			sc.getDriver().close();
		}
		
	}
	

}
