package selenium;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;


public class Topic_13_Wait_Part2 {
    WebDriver driver;
    WebDriverWait waitExplicit ; 
    By startBtn = By.xpath("//button[text()='Start']"); 
    By loadingIcon = By.xpath("//div[@id='loading']"); 
    By helloWorldTxt = By.xpath("//div[@id='finish']/h4"); 
    

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30); 
		
	}

	//@Test
	public void TC_01_AjaxLoadingWithOutExplicitWait(){
		
	    driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	   
	    //Wait cho den khi DateTime picker duoc hien thi
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contentWrapper']")).isDisplayed());	
	    
	    String beforeSelectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(); 
	    System.out.println("Ngay truoc khi chon: " + beforeSelectDate);
	    
	    driver.findElement(By.xpath("//a[text()='14']")).click();
	    
	    //Wait cho đến khi "loader ajax" không còn visible tuong ung vs viec
	    //Wait cho den khi hien thi hien thi "Sunday, April 14, 2019"
	    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(),'Sunday, April 14, 2019')]")).isDisplayed()); 
	    
	    //Wait cho den khi ngay 14 duoc hien thi 
	    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='14']/parent::td[@class='rcSelected']")).isDisplayed());
	    
	    
	    String afterSelectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
	    
	    System.out.println("Ngay sau khi chon: " + afterSelectDate);
	    
	    //validate  ngay da chon = Sunday, April 14, 2019
	    Assert.assertEquals(afterSelectDate, "Sunday, April 14, 2019");
	    
	}
	
	//@Test
	public void TC_02_AjaxLoadingWithExplicitWait(){
		
	    driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	   
	    //Wait cho den khi DateTime picker duoc hien thi	    
	    //Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contentWrapper']")).isDisplayed());	
	    waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
	    
	    String beforeSelectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(); 
	    System.out.println("Ngay truoc khi chon: " + beforeSelectDate);
	    
	    driver.findElement(By.xpath("//a[text()='14']")).click();
	    
	    //Wait cho đến khi "loader ajax" không còn visible tuong ung vs viec
	    //Wait cho den khi hien thi hien thi "Sunday, April 14, 2019"
	    //Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(),'Sunday, April 14, 2019')]")).isDisplayed()); 
	    	    
	    //Wait cho Ajax loading bien mat
	    waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']"))); 
	    
	    //Assert.assertTrue(driver.findElement(By.xpath("//a[text()=\"14\"]/parent::td[@class='rcSelected']")).isDisplayed());
	    waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='14']/parent::td[@class='rcSelected']"))); 
	    
	    String afterSelectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
	    
	    System.out.println("Ngay sau khi chon: " + afterSelectDate);
	    
	    Assert.assertEquals(afterSelectDate, "Sunday, April 14, 2019");
	    
	}
	
	@Test
	public void TC_03_FluentWait() {
	    
	    driver.get("https://daominhdam.github.io/fluent-wait/");
	    
	    WebElement countdount =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	    waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

	    // Khởi tạo Fluent wait
	    new FluentWait<WebElement>(countdount)
	               // Tổng time wait là 15s
	               .withTimeout(15, TimeUnit.SECONDS)
	                // Tần số mỗi 1s check 1 lần
	                .pollingEvery(1000, TimeUnit.MILLISECONDS)
	               // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
	                .ignoring(NoSuchElementException.class)
	                // Kiểm tra điều kiện
	                .until(new Function<WebElement, Boolean>() {
	                    public Boolean apply(WebElement element) {
	                               // Kiểm tra điều kiện countdount = 00
	                               boolean flag =  element.getText().endsWith("00");
	                               System.out.println("Time = " +  element.getText());
	                               // return giá trị cho function apply
	                               return flag;
	                    }
	               });
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