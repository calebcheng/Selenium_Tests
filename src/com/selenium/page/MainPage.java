package com.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.utils.Constant;



public class MainPage{
	
	public static final Logger LOG = LoggerFactory.getLogger(MainPage.class);
	
	public static final String PAGENAME = "MainPage";
	
	WebDriver driver;
	WebDriverWait dWait;
	
	@FindBy(xpath="/html/body/header/div/div/a")
	WebElement homeTab;
	
	@FindBy(id="Asset")
	WebElement assetTab;
	
	@FindBy(id="signIn_userName")
	WebElement signInName;
	
	@FindBy(id="signIn_password")
	WebElement signInPassword;
	
	@FindBy(xpath="//*[@id=\"signInForm\"]/input[3]")
	WebElement signInSubmit;
	
	@FindBy(id="signUp_userName")
	WebElement signUpName;
	
	@FindBy(id="signUp_password")
	WebElement signUpPassword;
	
	@FindBy(xpath="//*[@id=\"signUpForm\"]/input[3]")
	WebElement signUpSubmit;
	
	
	
	public MainPage(WebDriver driver){
		this.driver = driver;
		this.dWait = new WebDriverWait(driver, Constant.EXPLICIT_WAIT); // 30
		PageFactory.initElements(driver, this);
	}
	
	public void clickHomeTab() {
		dWait.until(ExpectedConditions.elementToBeClickable(homeTab));
		homeTab.click();
	}
	
	public void clickAssetTab() {
		dWait.until(ExpectedConditions.elementToBeClickable(assetTab));
		assetTab.click();
	}
	
	
	public void submitSignInForm(String name, String password) {
		
		dWait.until(ExpectedConditions.visibilityOf(signInName));
		dWait.until(ExpectedConditions.visibilityOf(signInPassword));
		dWait.until(ExpectedConditions.elementToBeClickable(signInSubmit));
		
		signInName.sendKeys(name);
		signInPassword.sendKeys(password);
		signInSubmit.submit();

	}
		
	public void submitSignUpForm(String name, String password) {
		
		dWait.until(ExpectedConditions.visibilityOf(signUpName));
		dWait.until(ExpectedConditions.visibilityOf(signUpPassword));
		dWait.until(ExpectedConditions.elementToBeClickable(signUpSubmit));
		
		signUpName.sendKeys(name);
		signUpPassword.sendKeys(password);
		signUpSubmit.submit();

	}
}
