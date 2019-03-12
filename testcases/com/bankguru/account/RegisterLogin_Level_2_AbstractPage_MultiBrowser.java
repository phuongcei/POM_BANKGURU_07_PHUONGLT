package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class RegisterLogin_Level_2_AbstractPage_MultiBrowser {

//	Multi-browser
	WebDriver driver;
	private String email, userID, password, loginURL;
	private AbstractPage abstractPage;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		abstractPage = new AbstractPage();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		email = "seleniumonline" + randomNumber() + "@gmail.com";
		System.out.println("Email: " + email);
	}

	@Test
	public void TC_01_Register() {
		
		abstractPage.openUrl(driver, "http://demo.guru99.com/v4/index.php");

		loginURL = abstractPage.getCurrentUrl(driver);

		abstractPage.clickToElement(driver, "//a[text()='here']");

		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//input[@id='emailid']"));
		
		abstractPage.sendkeyToElement(driver, "//input[@id='emailid'", email);
	
		abstractPage.clickToElement(driver, "//input[@name='btnLogin'");

		userID = abstractPage.getTextInElement(	driver,	"//td[text()='User ID :']/following-sibling::td");

		password = abstractPage.getTextInElement(driver, "//td[text()='Password :']/following-sibling::td");
		
	}

	@Test
	public void TC_02_LoginWithAboveInformation() {
		driver.get(loginURL);

		driver.findElement(By.xpath("//input[@name='uid'")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password'")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin'")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger ID : " + userID + "']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public static int randomNumber() {
		Random random = new Random();
		int random_num = random.nextInt(999);
		System.out.println("Random number is: " + random_num);
		return random_num;
	}

}
