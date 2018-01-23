package com.selenium.home;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.selenium.testcase.assetInfoValidation;

public class TestngMain {
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}

	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { assetInfoValidation.class });
		testng.addListener(tla);
		testng.run();
	}

}
