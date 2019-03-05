package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_XPATH_CSS {
    WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	
	@Test
	public void TC_01_LoginWithEmailAndPasswordEmpty() {
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[@class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']/div/ul/li[@class='first']/a")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailRequired = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailRequired, "This is a required field.");
		
		String passwordRequired = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passwordRequired, "This is a required field.");
		
		
	}
	
	@Test
	public void TC_02_LoginWithEmailInvalid() {
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[@class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']/div/ul/li[@class='first']/a")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailInvalid = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
		
				
	}
	
	@Test
	public void TC_03_LoginWithShortPassword() {
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[@class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']/div/ul/li[@class='first']/a")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
				
		String passwordInvalid = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordInvalid, "Please enter 6 or more characters without leading or trailing spaces.");
		
		
	}
	
	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[@class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']/div/ul/li[@class='first']/a")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
				
		String message= driver.findElement(By.xpath("//li[@class='error-msg']/ul/li/span")).getText();
		Assert.assertEquals(message, "Invalid login or password.");
	
		
	}
	

	@Test
	public void TC_05_CreateAnAccount() {
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);  
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[@class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']/div/ul/li[@class='first']/a")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']/span/span")).click();
		
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Ha");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Thu");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Nguyen");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("usernameNHama"+ randomInt +"@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");	
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");	
		
		driver.findElement(By.xpath("//button[@title='Register']/span/span")).click();
		
		String Successfullmessage= driver.findElement(By.xpath("//li[@class='success-msg']/ul/li/span")).getText();
		Assert.assertEquals(Successfullmessage, "Thank you for registering with Main Website Store.");
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a/span[@class='label']")).click();	
		String checkCBHP= driver.findElement(By.xpath("//a[@title='Log In']")).getText();
		Assert.assertEquals(checkCBHP, "Log In");
	}
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}