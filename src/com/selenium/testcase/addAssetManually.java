package com.selenium.testcase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.selenium.utils.Constant;


public class addAssetManually extends ConsoleTestcase{
	
	public static final Logger LOG = LoggerFactory.getLogger(addAssetManually.class);
	
	@Test(priority=1)
	public void addAsset() throws InterruptedException {
		
		mainPage.clickAssetTab();
		assetListViewPage.verifyHeader();
		
		for(Properties p : propertyFiles) {
			
			assetListViewPage.clickAddButton();
			List<String> assetInfo = new ArrayList<String>(Arrays.asList(p.getProperty(Constant.PROPERTY_HOSTNAME), p.getProperty(Constant.PORPERTY_PROCESSOR), p.getProperty(Constant.PROPERTY_IP), p.getProperty(Constant.PROPERTY_CREATION_DATE)));
			addAssetpage.submitAssetInfo(assetInfo);

		}
		
	}

	@Test(priority=2)
	public void validateAssetDetails() throws InterruptedException {
		
		mainPage.clickAssetTab();
		assetListViewPage.verifyHeader();
		
		assetDetailVerification();
		sa.assertAll();

		
	}
}
