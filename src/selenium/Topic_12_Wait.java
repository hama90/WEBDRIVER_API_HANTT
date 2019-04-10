package selenium;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.jmx.snmp.Timestamp;

public class Topic_12_Wait {
    WebDriver driver;
    WebDriverWait waitExplicit ; 
    By startBtn = By.xpath("//button[text()='Start']"); 
    By loadingIcon = By.xpath("//div[@id='loading']"); 
    By helloWorldTxt = By.xpath("//div[@id='finish']/h4"); 
    

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		
	}

	//@Test
	public void TC_01_ImplicitWait_2S(){
		
	    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    
	    driver.findElement(startBtn).click();
	    
	    
	    Assert.assertTrue(driver.findElement(helloWorldTxt).isDisplayed());
	    
	    
	}
	
	//@Test
	public void TC_02_ImplicitWait_5S(){
		
	    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    
	    driver.findElement(startBtn).click();
	    
	    
	    Assert.assertTrue(driver.findElement(helloWorldTxt).isDisplayed());
	    
	    
	}
	
	@Test
	public void TC_03_InvisibleLoadingIcon() {
	    
	    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    
	    waitExplicit = new WebDriverWait(driver, 2); 
	    
	    driver.findElement(startBtn).click();
	    
	    waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon)); 
	    
	    Assert.assertTrue(driver.findElement(helloWorldTxt).isDisplayed());
	}
	
	//@Test
	public void TC_04_HelloWorldTextVisibleFailed() {
	    
	    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    waitExplicit = new WebDriverWait(driver, 2);	     
	   
	    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	    driver.findElement(startBtn).click();
	    
	    waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloWorldTxt));
	    Assert.assertTrue(driver.findElement(helloWorldTxt).isDisplayed());
	}
	
	//@Test
	public void TC_05_HelloWorldText_LoadingIcon_NoLongerInDOM() {
	    
	    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	    waitExplicit = new WebDriverWait(driver, 5);	     
	   
	    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	    
	    //INVISIBLE + KHONG co trong DOM	   
	    System.out.println("Start time: = " + getDateTimeSecond());
	    waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloWorldTxt)); 
	    System.out.println("End time: = " + getDateTimeSecond());
	    
	    //INVISIBLE + KHONG co trong DOM	   
	    System.out.println("Start time: = " + getDateTimeSecond());
	    waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon)); 
	    System.out.println("End time: = " + getDateTimeSecond());
	    	   	   
	    System.out.println("Start time: = " + getDateTimeSecond());
	    driver.findElement(startBtn).click();
	    System.out.println("End time: = " + getDateTimeSecond());
	    
	    //KHONG visible + CO trong DOM	   
	    System.out.println("Start time: = " + getDateTimeSecond());
	    waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon)); 
	    System.out.println("End time: = " + getDateTimeSecond());
	    
	    //KHONG visible + CO trong DOM	   
	    System.out.println("Start time: = " + getDateTimeSecond());
	    waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startBtn)); 
	    System.out.println("End time: = " + getDateTimeSecond());	    
	    
	   
	}
	
	public Date getDateTimeSecond() {
	    Date  date = new Date(); 
	    date = new java.sql.Timestamp(date.getTime());
	    return date;
	    
	   
	}
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}