package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_WebElement_WebBrowser_Excercise {

	// Khai bao bien

	WebDriver driver;
	By emailTxtBox = By.xpath("//input[@id='mail']");
	By ageUnder18Radiobt = By.xpath("//input[@id='under_18']");
	By eduTxtArea = By.xpath("//textarea [@id='edu']");
	
	By jobRole01Select = By.xpath("//select[@id='job1']");
	By interestDevelopmentCheck = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnable = By.xpath("//button[@id='button-enabled']");
	By passwordTxtbox = By.xpath("//input[@id='password']");
	By ageRadioDisable = By.xpath("//input[@id='radio-disabled']");
	By biographyTxtArea = By.xpath("//textarea[@id='bio']"); 
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestCheckboxDisable = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisable = By.xpath("//button[@id='button-disabled']");
	
	
	
	@BeforeTest
	public void beforeTest() {
		// Khoi tao browser
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_CheckIsDisplayed() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		System.out.println("\nKet qua TC_01:");

		if (isElementDisplay(emailTxtBox)) {
			driver.findElement(emailTxtBox).sendKeys("Automation Testing");
		}

		if (isElementDisplay(ageUnder18Radiobt)) {
			driver.findElement(ageUnder18Radiobt).click();
		}

		if (isElementDisplay(eduTxtArea)) {
			driver.findElement(eduTxtArea).sendKeys("Automation Testing");
		}

	}

	
	@Test
	public void TC_02_CheckIsEnabled() {
		
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		System.out.println("\nKet qua TC_02: \n");
		
		isElementEnable(emailTxtBox);
		isElementEnable(ageUnder18Radiobt);
		isElementEnable(eduTxtArea);
		isElementEnable(jobRole01Select);
		isElementEnable(interestDevelopmentCheck);
		isElementEnable(slider01);
		isElementEnable(buttonEnable);
		isElementEnable(passwordTxtbox);
		isElementEnable(ageRadioDisable);
		isElementEnable(biographyTxtArea);
		isElementEnable(jobRole02);
		isElementEnable(interestCheckboxDisable);
		isElementEnable(slider02);
		isElementEnable(buttonDisable);
		
		
		
	}
	
	@Test
	public void TC_03_CheckIsSelected( ) {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		System.out.println("\n Ket qua TC_03:");
		
		checkToCheckbox(ageUnder18Radiobt);
		checkToCheckbox(interestDevelopmentCheck);
		Assert.assertTrue(isElementSelected(ageUnder18Radiobt));
		Assert.assertTrue(isElementSelected(interestDevelopmentCheck));
		UncheckToCheckbox(interestDevelopmentCheck);
		Assert.assertFalse(isElementSelected(interestDevelopmentCheck));
		
	}

	public boolean isElementEnable(By byValue) {
		if (driver.findElement(byValue).isEnabled())
		{
			System.out.println("Element [ " + byValue + "] is enabled");
			return true;
		} 
		else 
		{
			System.out.println("Element [" + byValue + "] is DISABLED");
			return false;
		}
	}
	
	public boolean isElementDisplay(By byValue) 
	{
		if (driver.findElement(byValue).isDisplayed()) {
			System.out.println("Element [" + byValue + "] is displayed");
			return true;
		}
		else
		{
			System.out.println("Element [" + byValue + "] is NOT displayed");
			return false;
		}

	}
	
	//Ham check 
	public boolean isElementSelected(By byValue) 
	{
		if (driver.findElement(byValue).isSelected()) {
			System.out.println("Element [" + byValue + "] is selected");
			return true;
		}
		else
		{
			System.out.println("Element [" + byValue + "] is NOT selected");
			return false;
		}

			}
	
	// Chonj 1 doi tuong
	public void checkToCheckbox(By byValue)
	{
		WebElement element = driver.findElement(byValue);
		if (!element.isSelected()) 
		{
			element.click();
			//System.out.println("Element [" + byValue + "] is selected");
			
		}
	}
	
	//Bo chon 1 doi tuong
	public void UncheckToCheckbox(By byValue)
	{
		WebElement element = driver.findElement(byValue);
		if (element.isSelected()) 
		{
			element.click();
	
			
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}