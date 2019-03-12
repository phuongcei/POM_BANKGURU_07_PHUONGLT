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

public class RegisterLogin_Level_1_Step_By_Step {

	WebDriver driver;
	private String email, userID, password, loginURL;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		email = "seleniumonline" + randomNumber() + "@gmail.com";
		System.out.println("Email: " + email);
	}

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/index.php");

		loginURL = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailid'")).isDisplayed());

		driver.findElement(By.xpath("//input[@id='emailid'")).sendKeys(email);

		driver.findElement(By.xpath("//input[@name='btnLogin'")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();

		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
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
