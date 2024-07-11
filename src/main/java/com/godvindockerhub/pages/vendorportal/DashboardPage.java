package com.godvindockerhub.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.godvindockerhub.pages.AbstractPage;

public class DashboardPage extends AbstractPage{
	
	private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);
	
	@FindBy(id="monthly-earning")
	private WebElement monthlyEarning;
	


	@FindBy(id="annual-earning")
	private WebElement annualEarning;
	
	@FindBy(id="profit-margin")
	private WebElement profitMargin;
	
	@FindBy(id="available-inventory")
	private WebElement availableInventory;
	
	@FindBy(css="#dataTable_filter input")
	private WebElement searchInput;
	
	@FindBy(id="dataTable_info")
	private WebElement EntrycountText;
	
	@FindBy(css=".img-profile")
	private WebElement profileImg;
	
	@FindBy(css="a[data-target='#logoutModal']")
	private WebElement logoutOption;
	
	@FindBy(css=".modal-content a")
	private WebElement logoutBtn;
	
	
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(monthlyEarning));
		return monthlyEarning.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		return monthlyEarning.getText();
	}

	public String getAnnualEarning() {
		return annualEarning.getText();
	}

	public String getProfitMargin() {
		return profitMargin.getText();
	}

	public String getAvailableInventory() {
		return availableInventory.getText();
	}
	public void searchOrderHistory(String keyword) {
		this.searchInput.sendKeys(keyword); 
	}

	public int getResultsCount() {
		int num= Integer.parseInt(EntrycountText.getText().split(" ")[5].trim());
		log.info("Results count : {}",num);
		return num;
		
	}
	
	public void logout() {
		this.profileImg.click();
		this.logoutOption.click();
		this.wait.until(ExpectedConditions.visibilityOf(logoutBtn));
		this.logoutBtn.click();
	}
	
	

}
