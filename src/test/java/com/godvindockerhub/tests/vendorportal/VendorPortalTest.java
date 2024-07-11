package com.godvindockerhub.tests.vendorportal;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.godvindockerhub.pages.vendorportal.DashboardPage;
import com.godvindockerhub.pages.vendorportal.LoginPage;
import com.godvindockerhub.tests.BaseTest;
import com.godvindockerhub.tests.vendorportal.modal.VendorPortalTestData;
import com.godvindockerhub.util.Config;
import com.godvindockerhub.util.Constants;
import com.godvindockerhub.util.JsonUtil;
import com.godvindockerhub.util.ResourceLoader;

public class VendorPortalTest extends BaseTest{
	
	private static final Logger log =LoggerFactory.getLogger(VendorPortalTest.class);

	
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private VendorPortalTestData testData;
	
	@BeforeTest(dependsOnMethods="setDriver")
	@Parameters("testDataPath")
	public  void setpageObjects(String testDataPath) throws IOException {
		loginPage=new LoginPage(driver);
		dashboardPage=new DashboardPage(driver);
		this.testData=JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
		
	}
	
	
	
	@Test
	public void logintest() {
		
		loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
		
		loginPage.isAt();
		loginPage.enterUserDetails(testData.username(), testData.password());
		loginPage.clickLogin();
	}
	
	@Test(dependsOnMethods="logintest")
	public void dashboardTest() {
		Assert.assertTrue(dashboardPage.isAt());
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
		Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
		Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
		Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
		
		dashboardPage.searchOrderHistory(testData.searchKeyword());
		Assert.assertEquals(dashboardPage.getResultsCount(), testData.searchCount());
		//dashboardPage.logout();
	}
	
	@Test(dependsOnMethods="dashboardTest")
	public void logoutTest() {
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}
	
	
	
}
