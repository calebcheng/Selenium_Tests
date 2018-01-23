package com.selenium.home;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.javascript.host.Element;

public class SeleniumController {

	public static final Logger LOG = LoggerFactory.getLogger(SeleniumController.class);

	private WebDriver driver;
	
//	static {
//		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
//	}

	public SeleniumController(String url) {

		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		driver = new ChromeDriver();
		
		driver.get(url);
		
		System.out.println("SHould open browser");
		LOG.info("Staring URL : {}", url);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement findElement(By by) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver t) {
				return driver.findElement(by);
			}

		});
		
		if(element == null) {
			System.out.println("Count not find element");
			throw new ElementNotFoundException(null, null, null);
		} 
		System.out.println("Found elelment" + element);
		return element;

	}
}
