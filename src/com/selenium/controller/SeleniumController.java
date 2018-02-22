package com.selenium.controller;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.utils.Constant;

public class SeleniumController {

	public static final Logger LOG = LoggerFactory.getLogger(SeleniumController.class);

	private WebDriver driver;
	
	static {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
	}

	public SeleniumController(String url) {

		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(Constant.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(url);
		
		LOG.info("Staring URL : {}", url);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement findElement(By by) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Constant.FLUENT_WAIT, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver t) {
				return driver.findElement(by);
			}

		});
		
		if(element == null) {
			System.out.println("Count not find element");
			throw new NoSuchElementException(null);
		} 
		return element;

	}
}
