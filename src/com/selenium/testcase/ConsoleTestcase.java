package com.selenium.testcase;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.selenium.controller.SeleniumController;
import com.selenium.page.AddAssetPage;
import com.selenium.page.AssetDetailPage;
import com.selenium.page.AssetListViewPage;
import com.selenium.page.MainPage;
import com.selenium.utils.Constant;
import com.selenium.utils.Utils;

abstract class ConsoleTestcase {
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	public static final Logger LOG = LoggerFactory.getLogger(ConsoleTestcase.class);

	protected SeleniumController sc;
	protected WebDriver driver;
	protected ArrayList<Properties> propertyFiles;
	protected SoftAssert sa = new SoftAssert();
	
	protected MainPage mainPage;
	protected AddAssetPage addAssetpage;
	protected AssetDetailPage assetDetailPage;
	protected AssetListViewPage assetListViewPage;
	
	@BeforeClass
	protected void initClass() throws IOException {
		
		LOG.info("Starting Before Class");
		loadProperyFile();
		if(propertyFiles.size() > 0) {
			sc = new SeleniumController(propertyFiles.get(0).getProperty(Constant.PROPERTY_CONSOLE_ADDRESS));
			driver = sc.getDriver();
		} else {
			Assert.assertTrue(false, "No property file found");
		}
		
		mainPage = new MainPage(driver);
		addAssetpage = new AddAssetPage(driver);
		assetDetailPage = new AssetDetailPage(driver);
		assetListViewPage = new AssetListViewPage(driver);
		
		mainPage.submitSignInForm("admin", "admin");
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
	
	protected void verifyContent(List<String> expected, List<String> actual) {
		LOG.info("Verifying the content of the asset");
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
	
	protected void assetDetailVerification() {
		for(Properties p : propertyFiles) {
			
			assetListViewPage.clickAsset((By.xpath("//*[@id='" + p.getProperty(Constant.PROPERTY_IP) + "']")));  // go to AssetDetailPage
			assetDetailPage.verifyHeader();
			
			LOG.info("Verifying Asset Headers");
			List<String> expected = AssetDetailPage.sysHeaders;
			List<String> actual = new ArrayList<String>();
			for(int i = 1 ; i < expected.size() + 1 ; i++) {  //xpath starts with 1
				actual.add(sc.findElement(By.xpath("//*[@id=\"asset_header\"]/thead/tr/th[" + i + "]")).getText());
			}
			verifyContent(expected, actual);
			
			LOG.info("Verifying Asset value");
			expected = new ArrayList<String>(Arrays.asList(p.getProperty(Constant.PROPERTY_HOSTNAME), p.getProperty(Constant.PORPERTY_PROCESSOR), p.getProperty(Constant.PROPERTY_IP), p.getProperty(Constant.PROPERTY_CREATION_DATE)));
			actual = new ArrayList<String>();
			for(int i = 1 ; i < expected.size() + 1; i++) {
				actual.add(sc.findElement(By.xpath("//*[@id=\"asset_value\"]/tbody/tr[1]/td[" + i + "]")).getText());
			}
			verifyContent(expected, actual);
			
			assetDetailPage.clickBackButton();
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if(driver != null) {
			driver.close();
		}
		
	}
}
