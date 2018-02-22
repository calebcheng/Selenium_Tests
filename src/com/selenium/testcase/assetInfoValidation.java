package com.selenium.testcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class assetInfoValidation extends ConsoleTestcase{
	
	public static final Logger LOG = LoggerFactory.getLogger(assetInfoValidation.class);
	
	@Test(priority=1)
	public void validateAssetDetails() {
		
		mainPage.clickAssetTab();
		assetListViewPage.verifyHeader();
		
		assetDetailVerification();

		sa.assertAll();
	}
}
