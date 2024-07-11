package com.godvindockerhub.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.godvindockerhub.util.Constants;

public class Listener implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		TakesScreenshot driver=(TakesScreenshot)result.getTestContext().getAttribute(Constants.DRIVER);
		String screenshot=driver.getScreenshotAs(OutputType.BASE64);
		String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
		String img=String.format(htmlImageFormat, screenshot);
		Reporter.log(img);
	}

}
