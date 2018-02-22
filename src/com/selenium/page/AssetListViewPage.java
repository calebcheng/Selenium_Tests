package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.utils.Constant;

public class AssetListViewPage {
	
	public static final Logger LOG = LoggerFactory.getLogger(AssetListViewPage.class);
	
	public static final String PAGENAME = "AssetListViewPage";
	
	WebDriver driver;
	WebDriverWait dWait;
	
	@FindBy(id="addAssetBtn")
	WebElement addButton;
	
	@FindBy(xpath="//*[@id=\"masthead\"]/div[2]/div/div/div/div/h3")
	WebElement assetLists;
	
	
	public AssetListViewPage(WebDriver driver) {
		this.driver = driver;
		this.dWait = new WebDriverWait(driver, Constant.EXPLICIT_WAIT); // 30
		PageFactory.initElements(driver, this);
	}
	
	public void clickAsset(By by) {
		LOG.info("Clicking asset link at page : {}", PAGENAME);
		dWait.until(ExpectedConditions.elementToBeClickable(by)).click();
		
	}
	
	public void clickAddButton() {
		LOG.info("Clicking Add button at page : {}", PAGENAME);
		dWait.until(ExpectedConditions.elementToBeClickable(addButton));
		addButton.click();
	}
	
	public void verifyHeader() {
		LOG.info("Verifying page header: {}", Constant.ASSET_LISTS);
		dWait.until(ExpectedConditions.textToBePresentInElement(assetLists, Constant.ASSET_LISTS)); 
		LOG.info("Header is found");
	}
	
	
	
	

}
