package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_UserInteraction {
    WebDriver driver;
    Actions action; 
    JavascriptExecutor jsExecutor ;
    

	@BeforeTest
	public void beforeTest() {
		//driver = new FirefoxDriver();
	    System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	    	driver = new ChromeDriver();
		action = new Actions(driver); 
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_MoveMouseToElement() throws Exception {
	    driver.get("http://www.myntra.com/");
	    WebElement profileIcon = driver.findElement(By.xpath("//span[contains(@class,'desktop-iconUser')]")); 
	    
	    //Hover to profile Icon
	    action.moveToElement(profileIcon).perform();
	    
	    Thread.sleep(3000);
	    
	    //Click to button Login
	    WebElement logInBtn = driver.findElement(By.xpath("//a[@class='desktop-linkButton'and text()='log in']")); 
	    
	    action.click(logInBtn).perform();
	    
	    Thread.sleep(2000);
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	    
	}
	
	//@Test
	public void TC_02_ClickAndHold() throws Exception {
	    
	    driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	    
	    List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	    System.out.println("So luong element cua bang: " + numberItems.size());
	    
	    //lua chon cac gia tri 1 den 4 tuong ung numberItems.get[0] den numberItems.get[3]
	    action.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release().perform();
	    	  
	    List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")); 
	    System.out.println("So luong element sau khi chon la: " + numberItemsSelected.size());
	    
	    Thread.sleep(3000);
	    //verify da chon du 4 gia tri
	    Assert.assertEquals(numberItemsSelected.size(), 4);
	    	    
	}
	
	//@Test
	public void TC_03_ClickAndHold() throws Exception {
	    
	    driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	    
	    List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	   
	    action.keyDown(Keys.CONTROL).perform();
	    numberItems.get(0).click();
	    numberItems.get(2).click();
	    numberItems.get(4).click();
	    numberItems.get(6).click();
	    action.keyUp(Keys.CONTROL).perform();
	        
	    Thread.sleep(3000);
	    
	    List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")); 
	    System.out.println("So luong element  sau khi chon la: " + numberItemsSelected.size());
	    
	    //verify da chon du 4 gia tri
	    Assert.assertEquals(numberItemsSelected.size(), 4);
	     	    
	}
	
	//@Test
	public void TC_03_DoubleClick() throws Exception {
	    
	    driver.get("http://www.seleniumlearn.com/double-click");
	    
	    WebElement doubleClickBtn = driver.findElement(By.xpath("//button[text()='Double-Click Me!']")); 
	    action.doubleClick(doubleClickBtn).perform();
	    
	    Thread.sleep(3000);
	    
	    Alert alert= driver.switchTo().alert();
	    Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
	    
	    //driver.switchTo().alert().accept();
	    alert.accept();
	    	    
	}
	//@Test
	public void TC_04_RightClick() throws Exception {
	    
	    driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	    
	    WebElement rightClickMeBtn = driver.findElement(By.xpath("//span[text()='right click me']")); 
	    action.contextClick(rightClickMeBtn).perform();
	    
	    WebElement quitContext = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")) ; 
	    action.moveToElement(quitContext).perform();	     
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class, 'context-menu-visible') and contains(@class,'context-menu-hover')]")).isDisplayed());

	    quitContext.click();
	    
	    Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
	    driver.switchTo().alert().accept();
	    
	    Thread.sleep(3000);
	    
	    Assert.assertFalse(quitContext.isDisplayed());
	}
	
	@Test
	public void TC_05_DragAndDropElement() {
	    driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular"); 
	    
	    WebElement smallCicrle = driver.findElement(By.xpath("//div[@id='draggable']"));
	    WebElement bigCirle = driver.findElement(By.xpath("//div[@id='droptarget']")); 
	    
	    action.dragAndDrop(smallCicrle, bigCirle).perform(); 
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}