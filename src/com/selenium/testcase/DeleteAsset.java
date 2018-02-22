package com.selenium.testcase;

import java.util.Properties;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.selenium.utils.Constant;

public class DeleteAsset extends ConsoleTestcase {

	public static final Logger LOG = LoggerFactory.getLogger(DeleteAsset.class);

	@Test(priority = 1)
	public void deleteAsset() {
		LOG.info("Deleting Assets");
		mainPage.clickAssetTab();

		for (Properties p : propertyFiles) {

			assetListViewPage.verifyHeader();

			assetListViewPage.clickAsset(By.xpath("//*[@id='" + p.getProperty(Constant.PROPERTY_IP) + "']"));

			assetDetailPage.clickDeleteButton();
			
			assetListViewPage.verifyHeader();
																									
			Boolean isPresent = driver.findElements(By.xpath("//*[@id='" + p.getProperty(Constant.PROPERTY_IP) + "']")).size() > 0;
			if (isPresent) {
				LOG.error("Element with IP address : {} still exists. Test fails",
						p.getProperty(Constant.PROPERTY_IP));
			} else {
				LOG.info("Element with IP address : {} does not exist. Test passes",
						p.getProperty(Constant.PROPERTY_IP));
			}

			sa.assertFalse(isPresent, "Asset IP should not exist");
		}
		sa.assertAll();
	}
}
