package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_06_Button_Checkbox_Alert {
    WebDriver driver;
    JavascriptExecutor jsExecutor ;
    

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver; 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Button_SeleniumClick() {
	    driver.get("http://live.guru99.com/");
	    
	    WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	    myAccountLink.click();
	    
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	    
	    WebElement createAnAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	    createAnAccountLink.click();
	
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	    
	}
	
	//@Test
	public void TC_02_Button_JsClick() {
	    driver.get("http://live.guru99.com/");
	    
	    WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	    jsExecutor.executeScript("arguments[0].click();", myAccountLink) ;
	    
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	    
	    WebElement createAnAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	    jsExecutor.executeScript("arguments[0].click();", createAnAccountLink) ;
		
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

	//@Test
	public void TC_03_CustomCheckbox() throws Exception {
	    driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	    
	    WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	    
	    //Buoc 1: Click to check
	    //dualZoneCheckbox.click();
	    //thẻ label không sử dụng được hàm click()
	    //thẻ input thì bị ẩn --> không dùng click của Selenium --> dùng của javascript
	    // Với customCheckbox --> dùng hàm javascirpt để click 
	    // Với defaultCheckbox --> dùng click của Selenium. 
	    checkToCheckbox(dualZoneCheckbox);
	    
	    Thread.sleep(1500);
	    
	    //Buoc 2: Verify
	    Assert.assertTrue(dualZoneCheckbox.isSelected());
	    
	    //Buoc 3: Uncheck 
	   // dualZoneCheckbox.click();
	    unCheckToCheckbox(dualZoneCheckbox);
	    
	    Thread.sleep(1500);
	    
	    //Buoc 4: Verify bo chon:
	    Assert.assertFalse(dualZoneCheckbox.isSelected());
	    
	    
	    
	}
	
	public void TC_04_DefaultCheckbox() {
	    driver.get("https://daominhdam.github.io/basic-form/index.html");
	    
	    WebElement developmentCheckbox = driver.findElement(By.xpath("//label[text()='Development']/preceding-sibling::input"));
	    
	    //Buoc 1: Check
	    checkToCheckbox(developmentCheckbox);
	    
	    //Buoc 2: Verify
	    Assert.assertTrue(developmentCheckbox.isSelected());
	    
	    //Buoc 3: Uncheck
	    unCheckToCheckbox(developmentCheckbox);
	    
	    //Buoc 4: Verify
	    Assert.assertFalse(developmentCheckbox.isSelected());
	    
	}
	
	
	@Test
	public void TC_05_RadioButton() throws Exception {
	    driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
	    WebElement radiobutton27Petrol = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
	    
	    //click
	    checkToCheckbox(radiobutton27Petrol);
	    
	    	   Thread.sleep(3000);
	    	   
	    //verify
	    	   Assert.assertTrue(radiobutton27Petrol.isSelected());
	    
	    
	}
	
	//@Test
	public void TC_06_Alert() throws Exception {
	    driver.get("https://daominhdam.github.io/basic-form/index.html");
	    
	    //JS accept 
	    WebElement jsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
	    jsAlert.click(); 
	    
	    Alert acceptAlert = driver.switchTo().alert() ;
	    
	    //verify
	    Assert.assertEquals(acceptAlert.getText(), "I am a JS Alert");
	    
	    acceptAlert.accept();
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and contains(text(), 'You clicked an alert successfully ')]")).isDisplayed());
	    Thread.sleep(2000);
	    
	    
	    //JS confirm alert 
	    driver.navigate().refresh();
	    driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	    Alert confirmAlert = driver.switchTo().alert(); 
	    
	    //verify
	    Assert.assertEquals(confirmAlert.getText(), "I am a JS Confirm");
	    
	    confirmAlert.dismiss();
	    
	   Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
	    Thread.sleep(2000);
	    
	    //JS prompt alert 
	    driver.navigate().refresh();
	    String inputText = "hanguyen" ; 
	    driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	    Alert promptAlert = driver.switchTo().alert();
	    
	    //verify
	    Assert.assertEquals(promptAlert.getText(), "I am a JS prompt");
	    
	    promptAlert.sendKeys(inputText);
	    promptAlert.accept();
	    
	    	    
	    Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: " + inputText + "']")).isDisplayed());
	    Thread.sleep(2000);
	    
	    
	    
	}
	
	
	//@Test
	public void TC_07_AuthenticationAlert() {
	    driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public void checkToCheckbox(WebElement element) {
	  	   
		   if(!element.isSelected()) {
		       if (element.isDisplayed() ) {
			   System.out.println("Click by Selenium");
			   element.click();
		       }
		       else {
			   System.out.println("Click by javascript");
			   jsExecutor.executeScript("arguments[0].click();", element) ;
		       }
		   }
		   	}
		
	public void unCheckToCheckbox (WebElement element) {
		   	    
		    if (element.isSelected()) {
			if (element.isDisplayed()) {
			    System.out.println("Click by Selenium");
			    element.click();
			}
			else {
			    System.out.println("Click by javascript");
			    jsExecutor.executeScript("arguments[0].click();", element) ;
			}
			
		    }
		}
	
	@AfterTest
	

	
	public void afterTest() {
		driver.quit();
	}

}