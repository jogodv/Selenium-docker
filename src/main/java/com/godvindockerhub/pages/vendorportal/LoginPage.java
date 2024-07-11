package com.godvindockerhub.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.godvindockerhub.pages.AbstractPage;


public class LoginPage extends AbstractPage{
	
	private static final Logger log =LoggerFactory.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="username")
	private WebElement usernameInput;
	
	@FindBy(id="password")
	private WebElement passwordInput;
	
	@FindBy(id="login")
	private WebElement loginBtn;
	
	public void goTo(String url) {
		
		this.driver.get(url);
	}
	
	public void enterUserDetails(String username,String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		
	}
	
	public void clickLogin() {
		loginBtn.click();
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(loginBtn));
		return loginBtn.isDisplayed();
	}
	
	

}
