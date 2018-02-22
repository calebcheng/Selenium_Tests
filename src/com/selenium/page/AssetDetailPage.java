package com.selenium.page;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.utils.Constant;

public class AssetDetailPage {
	
	public static final Logger LOG = LoggerFactory.getLogger(AssetDetailPage.class);
	
	public static final String PAGENAME = "AssetDetailPage";
	
	public static final List<String> sysHeaders = Arrays.asList("Hostname", "Processor", "IP", "Creation Date");
	
	WebDriver driver;
	WebDriverWait dWait;
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/a[2]")
	WebElement backButton;
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/a[1]")
	WebElement deleteButton;
	
	@FindBy(xpath="//*[@id=\"asset_header\"]/thead/tr/th[1]")
	WebElement hostnameHeader;
	
	@FindBy(xpath="//*[@id=\"asset_header\"]/thead/tr/th[2]")
	WebElement processorHeader;
	
	@FindBy(xpath="//*[@id=\"asset_header\"]/thead/tr/th[3]")
	WebElement ipHeader;
	
	@FindBy(xpath="//*[@id=\"asset_header\"]/thead/tr/th[4]")
	WebElement creationDateHeader;
	
	@FindBy(xpath="//*[@id=\"asset_value\"]/tbody/tr[1]/td[1]")
	WebElement hostnameValue;
	
	@FindBy(xpath="//*[@id=\"asset_value\"]/tbody/tr[1]/td[2]")
	WebElement processorValue;
	
	@FindBy(xpath="//*[@id=\"asset_value\"]/tbody/tr[1]/td[3]")
	WebElement ipValue;
	
	@FindBy(xpath="//*[@id=\"asset_value\"]/tbody/tr[1]/td[4]")
	WebElement creationDateValue;
	
	@FindBy(xpath="//*[@id=\"masthead\"]/div[2]/div/div/div/div/h3")
	WebElement assetDetails;
	
	public AssetDetailPage(WebDriver driver) {
		this.driver = driver;
		this.dWait = new WebDriverWait(driver, Constant.EXPLICIT_WAIT); // 30
		PageFactory.initElements(driver, this);
	}
	
	public void verifyHeader() {
		LOG.info("Verifying page header: {} at page : {}", Constant.ASSET_DETAILS, PAGENAME);
		dWait.until(ExpectedConditions.textToBePresentInElement(assetDetails, Constant.ASSET_DETAILS));  
	}
	
	public void clickBackButton() {
		LOG.info("Clicking Back button at page : {}", PAGENAME);
		dWait.until(ExpectedConditions.elementToBeClickable(backButton));
		backButton.click();
	}
	
	public void clickDeleteButton() {
		LOG.info("Clicking Delete button at page : {}", PAGENAME);
		dWait.until(ExpectedConditions.elementToBeClickable(backButton));
		backButton.click();
	}
	
	

}
