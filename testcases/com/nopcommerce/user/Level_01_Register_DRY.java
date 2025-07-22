package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register_DRY {
	WebDriver driver;
	JavascriptExecutor js;
	Actions actions;
	String countryName, phoneNumber, emailAddress;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		countryName = "Vietnam";
		phoneNumber = "0" + generateFakeNumber();
		emailAddress = "van.doan" + generateFakeNumber() + "@yopmail.com";
		driver.get("https://sourcevietnam.com/Register?ReturnUrl=%2F");
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(By.xpath("//button[@id='btnSignUpGTM']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='Input_Country_Error']")).getText(),
				"Please select country");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='Input_Email_Error']")).getText(),
				"Please fill in this field");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='Input_Password_Error']")).getText(),
				"Please fill in this field");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='Input_ConfirmPassword_Error']")).getText(),
				"Please fill in this field");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='Input_PhoneNumber_Error']")).getText(),
				"Please fill phone number");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='Input_IsCheckedAgree_Error']")).getText(),
				"Please check the agreement");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		driver.findElement(By.xpath("//span[@id='select2-Input_Nationality-container']")).click();
		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(countryName);
		driver.findElement(By.xpath("//ul[@id='select2-Input_Nationality-results']/li[text()='" + countryName + "']"))
				.click();
		driver.findElement(By.id("Input_Email")).sendKeys("abc#$@sourcevn.com");
		driver.findElement(By.id("Input_Password")).sendKeys("Test@123");
		driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("Test@123");
		driver.findElement(By.id("Input_PhoneNumber")).sendKeys("0899469883");
		driver.findElement(By.xpath("//input[@id='Input_IsCheckedAgree']")).click();
		driver.findElement(By.xpath("//button[@id='btnSignUpGTM']")).click();
		Assert.assertEquals(driver.findElement(By.id("Input_Email_Error")).getText(), "Invalid email format");
	}

	@Test
	public void TC_03_Register_Success() throws InterruptedException {
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		driver.findElement(By.xpath("//span[@id='select2-Input_Nationality-container']")).click();
		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(countryName);
		driver.findElement(By.xpath("//ul[@id='select2-Input_Nationality-results']/li[text()='" + countryName + "']"))
				.click();
		driver.findElement(By.id("Input_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Input_Password")).sendKeys("Test@123");
		driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("Test@123");
		driver.findElement(By.id("Input_PhoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@id='Input_IsCheckedAgree']")).click();
		driver.findElement(By.xpath("//button[@id='btnSignUpGTM']")).click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.className("signup__message__title")).getText(),
				"Verify your email address");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='signup__message__send']/p")).getText(),
				"A verification email has been sent to this email address " + emailAddress
						+ " . Please verify this email address.");
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		driver.findElement(By.xpath("//span[@id='select2-Input_Nationality-container']")).click();
		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(countryName);
		driver.findElement(By.xpath("//ul[@id='select2-Input_Nationality-results']/li[text()='" + countryName + "']"))
				.click();
		driver.findElement(By.id("Input_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Input_Password")).sendKeys("Test@123");
		driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("Test@123");
		driver.findElement(By.id("Input_PhoneNumber")).sendKeys("0899469883");
		driver.findElement(By.xpath("//input[@id='Input_IsCheckedAgree']")).click();
		driver.findElement(By.xpath("//button[@id='btnSignUpGTM']")).click();
		Assert.assertEquals(driver.findElement(By.id("Input_Email_Error")).getText(), "Email already exists");
	}

	@Test
	public void TC_05_Register_Password_Invalid_Password() {
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
		driver.findElement(By.id("Input_Password")).sendKeys("testers");
		Assert.assertEquals(driver.findElement(By.id("Input_Password_Error")).getText(), "Invalid password format");
		driver.findElement(By.id("Input_Password")).sendKeys("@");
		Assert.assertEquals(driver.findElement(By.id("Input_Password_Error")).getText(), "Invalid password format");
		driver.findElement(By.id("Input_Password")).sendKeys("T");
		Assert.assertEquals(driver.findElement(By.id("Input_Password_Error")).getText(), "Invalid password format");
		driver.findElement(By.id("Input_Password")).sendKeys("1");
		Assert.assertEquals(driver.findElement(By.id("Input_Password_Error")).getText(), "");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Email() {
		driver.findElement(By.id("Input_Password")).sendKeys("Test@123");
		driver.findElement(By.id("Input_ConfirmPassword")).sendKeys("Test@1234");
		Assert.assertEquals(driver.findElement(By.id("Input_ConfirmPassword_Error")).getText(),
				"The two passwords do not match. Please enter them again");
	}

	@AfterClass
	public void afterClass() {
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
