package com.selenium.page;

import java.util.ArrayList;
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

public class AddAssetPage {
	
	public static final Logger LOG = LoggerFactory.getLogger(AddAssetPage.class);
	
	public static final String PAGENAME = "AddAssetPage";
	
	WebDriver driver;
	WebDriverWait dWait;
	
	@FindBy(xpath="//*[@id=\"addAssetForm\"]/input")
	WebElement submitButton;
	
	@FindBy(xpath="//*[@id=\"addAssetForm\"]/a")
	WebElement cancelButton;
	
	@FindBy(xpath="//*[@id=\"addAssetForm\"]/div/div/div[2]/table/tbody/tr[1]/td[2]/input")
	WebElement hostnameValue;
	
	@FindBy(xpath="//*[@id=\"addAssetForm\"]/div/div/div[2]/table/tbody/tr[2]/td[2]/input")
	WebElement processorValue;
	
	@FindBy(xpath="//*[@id=\"addAssetForm\"]/div/div/div[2]/table/tbody/tr[3]/td[2]/input")
	WebElement ipValue;
	
	@FindBy(xpath="//*[@id=\"addAssetForm\"]/div/div/div[2]/table/tbody/tr[4]/td[2]/input")
	WebElement creationDateValue;
	
	@FindBy(xpath="//*[@id=\"masthead\"]/div[2]/div/div/div/div/h3")
	WebElement addAsset;

	public AddAssetPage(WebDriver driver) {
		this.driver = driver;
		this.dWait = new WebDriverWait(driver, Constant.EXPLICIT_WAIT); // 30
		PageFactory.initElements(driver, this);
	}
	
	public void submitAssetInfo(List<String> list) {
		LOG.info("Fill in the form and submit");
		List<WebElement> elementList = new ArrayList<WebElement>();
		elementList.add(hostnameValue);
		elementList.add(processorValue);
		elementList.add(ipValue);
		elementList.add(creationDateValue);
		
		for(int i = 0; i < elementList.size(); i++) {
			dWait.until(ExpectedConditions.visibilityOf(elementList.get(i)));
			elementList.get(i).sendKeys(list.get(i));
		}
		
		dWait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		
	}
	
	public void clickCancelButton() {
		LOG.info("Clicking cancel button at page : {}", PAGENAME);
		dWait.until(ExpectedConditions.elementToBeClickable(cancelButton));
		cancelButton.click();
	}
	
	public void verifyHeader() {
		LOG.info("Verifying page header: {} at page : {}", Constant.ADD_ASSET, PAGENAME);
		dWait.until(ExpectedConditions.textToBePresentInElement(addAsset, Constant.ADD_ASSET)); 
	}
}
