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

public class Topic_03_WebElement_WebBrowser_Structure {
	
   //Khai bao bien
	
	WebDriver driver; 
	
	@BeforeTest
	public void beforeTest() {
		// Khoi tao browser
		driver = new FirefoxDriver() ; 
	}
	
	@Test
	public void TC_01_Browser() {
		
		// nhung api cua WebBrowser - hay dung nhat
		
		//api mo 1 url len
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		//api tra ve url cua page hien tai
		String homepageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homepageUrl, "https://daominhdam.github.io/basic-form/index.html");
		
		//api tai ve source code cua page hien tai
		String homepageSource = driver.getPageSource();
		Assert.assertTrue(homepageSource.contains("Here are some examples of different JavaScript alerts which can be troublesome for automation"));
		
		//api tra ve title cua page hien tai
		String titlePage = driver.getTitle();
		Assert.assertEquals(titlePage, "SELENIUM WEBDRIVER FORM DEMO");
		
		//handle Windows/popup
		driver.getWindowHandle();
		driver.getWindowHandles(); 
		
		// api dong 1 tab trinh duyet
		driver.close();
		
		//api dong trinh duyet
		driver.quit();
		
		//handle WebDriverWait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
		
		//api back ve page truoc do
		
		driver.navigate().back();
		
		// api di den page tiep theo
		driver.navigate().forward();
		
		//api refresh
		driver.navigate().refresh();
		
		//api mo 1 url khac
		driver.navigate().to("https://www.lifehack.org/");
		
		//handle cho alert
		driver.switchTo().alert();
		
		//handle cho iframe
		driver.switchTo().frame("");
		
			
	}
	
	public void TC_02_WebElement() {
		
		//cac api tuong tac truc tiep
		driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Hama90 education");
		driver.findElement(By.xpath("//label[@for='over_18']")).click();
		
		//api tuong tac gian tiep --> qua nhieu buoc
		WebElement textaareaEducation = driver.findElement(By.xpath("//textarea[@id='edu']"));
		textaareaEducation.sendKeys("Hama90 edu2");
		
		//api xoa thao tac cu truoc khi thao tac de dam bao toan ven du lieu
		textaareaEducation.clear();
		textaareaEducation.sendKeys("NOT HAMA90");
		
		//get du lieu cua attribute ra
		WebElement Edutextarea = driver.findElement(By.id("edu"));
		String nameEdutextarea = Edutextarea.getAttribute("name");
		Assert.assertEquals(nameEdutextarea, "user_edu");
		
		//api tac dong vao 1 button/checkbox/radio button/link
		Edutextarea.click();
		
		//get ra gia tri cua CSS
		WebElement labelText0 = driver.findElement(By.xpath("(//legend/span[@class='number'])[1]")); 
		String getValueColor = labelText0.getCssValue("color");
		Assert.assertEquals(getValueColor, "#fff");
		
		//api get ra toa do
		Edutextarea.getLocation();
		
		//api get ra kich thuoc phan tu ben trong
		Edutextarea.getSize(); 
		
		//api lay ra ten the html
		Edutextarea.getTagName(); 
		
	}
    
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
    
    
}