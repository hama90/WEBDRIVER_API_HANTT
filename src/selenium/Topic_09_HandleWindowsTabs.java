package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_09_HandleWindowsTabs {
    WebDriver driver;
    JavascriptExecutor javascript; 

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		javascript =  (JavascriptExecutor) driver; 
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_TestScript02() throws Exception {
	    driver.get("https://daominhdam.github.io/basic-form/index.html");
	    
	    //getUid cua parent window
	    String parentID = driver.getWindowHandle();
	    System.out.println("ParentID = " + parentID);
	    
	    //Click vao dong Click here link
	    driver.findElement(By.xpath("//a[text()='Click Here']")).click();;
	    
	    switchToChildWindow(parentID);
	    
	    String googleTitle = driver.getTitle() ; 
	    System.out.println(googleTitle);
	    
	     //Kiem tra title cua window moi
	    Assert.assertEquals(googleTitle, "Google");
	    
	    //Close window moi; switch ve parent window
	    Assert.assertTrue(closeAllWithoutParentWindows(parentID));
	    
	    //Kiem tra title cua parent window, ve dung trang mong muon chua? check title cua trang
	    Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	    
	    
	}
	
	@Test
	public void TC_02_TestScript03() throws Exception {
	    driver.get("http://www.hdfcbank.com/");
	    
	    String parentID = driver.getWindowHandle() ;
	    
	    //Handle close popup 
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    //Update
	    WebElement popupbanner = driver.findElement(By.xpath("//img[@class='popupbanner']"));
	    driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
	    
	    //Update when popup of page has been changed
	    /*
	    List <WebElement> notificationIframe = driver.findElements(By.xpath("//img[@class='popupbanner']"));
	    int notificationIframeSize = notificationIframe.size() ; 
	    System.out.println("Notification Iframe displayed = " + notificationIframeSize);
	     if (notificationIframeSize > 0 ) {
		   driver.switchTo().frame(notificationIframe.get(0));
		    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
		    javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='div-close']"))); 
		    System.out.println("Close popup successfully!");
		    driver.switchTo().defaultContent(); 
		     }
	    System.out.println("Pass handle popup!");
	    
	    */
	    
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
	    //Click Agri link
	    driver.findElement(By.xpath("//a[text()='Agri']")).click();
	    
	    
	    //Switch Agri page by title 
	    switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	    Thread.sleep(3000);
	    
	  //Click Account Details link
	    driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	    
	    //Switch Account Details page by title 
	    switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	    Thread.sleep(3000);
	    
	    //Switch qua frame chua Privacy Policy link
	    driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
	    
	    //Click Privacy Policy link
	    driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
	    
	    //Switch Privacy Policy Page by title 
	    switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	    Thread.sleep(3000);
	    
	  //Click CRS link
	    driver.findElement(By.xpath("//a[text()='CSR']")).click();
	    
	    //Dong tat ca cac window/tab ngoai tru parent window
	    Assert.assertTrue(closeAllWithoutParentWindows(parentID));
	    Thread.sleep(3000);
	}

	//@Test
	public void TC_03_TestScript04() throws Exception {
	    driver.get("http://live.guru99.com/index.php/");
	    
	    String parentId = driver.getWindowHandle();
	    
	    //Click Mobile tab
	    driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	    
	    //Switch qua page Mobile
	    switchToWindowByTitle("Mobile");
	    
	    String mobileId = driver.getWindowHandle(); 
	    
	    //Click Add to compare cua san pham Sony Xperia
	    driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul[@class='add-to-links']/li[2]/a[text()='Add to Compare']")).click();
	    
	    //Click Add to compare cua san pham Samsung Galaxy
	    driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul[@class='add-to-links']/li[2]/a[text()='Add to Compare']")).click();
	    
	    
	    //Click button Compare
	    driver.findElement(By.xpath("//button[@title='Compare']")).click();
	    
	    
	    //Switch qua Compare page
	    switchToWindowByTitle("Products Comparison List - Magento Commerce");
	    Thread.sleep(3000);
	    
	    //Verify title cua page moi
	    Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	    
	    //Switch ve parent window
	    closeAllWithoutParentWindows(parentId);
	    Thread.sleep(5000);
	    
	
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	//Switch if only have 2 windows/tabs
	 public void switchToChildWindow(String parent) {
	   //Lay ra ID cua tat ca cac cua so dang co
             Set<String> allWindows = driver.getWindowHandles();
             
           //Duyet qua tat ca cac cua so dang mo
             for (String runWindow : allWindows) {
        	 
        	 	System.out.println("Window ID = " + runWindow);
        	 	//Neu ID cua window dang mo KHAC voi parentID thi switch qua, thoat khoi vong lap
                         if (!runWindow.equals(parent)) {
                                     driver.switchTo().window(runWindow);
                                     break;
                         }
             }
 }
	//Switch if have >= 2 windows/tabs
	  public void switchToWindowByTitle(String title) {
	      //Lay ra ID cua tat ca cac cua so dang co
              Set<String> allWindows = driver.getWindowHandles();
              
              //Duyet qua tat ca cac cua so dang mo
              for (String runWindows : allWindows) {
        	  
        	  	//Switch vao cac window dang mo 
                          driver.switchTo().window(runWindows);
                          
                          //Lay title cua window 
                          String currentWin = driver.getTitle();
                          
                          //neu title cua window khop voi TITLE can lay --> thoat khoi vong lap
                          if (currentWin.equals(title)) {
                                      break;
                          }
              }
  }
	  
	 //Close without parents window/tab
	  public boolean closeAllWithoutParentWindows(String parentWindow) {
	      
	      //Lay ra ID cua tat ca cac window
              Set<String> allWindows = driver.getWindowHandles();
              
              //Duyet
              for (String runWindows : allWindows) {
        	  
        	  	//Neu ID cua window dang xet KHAC voi parentID
                          if (!runWindows.equals(parentWindow)) {
                              
                              		//Switch sang window do va dong cua so
                                      driver.switchTo().window(runWindows);
                                      driver.close();
                          }
              }
              
              //Switch toi parent window
              driver.switchTo().window(parentWindow);
              
              //Check neu size cua window = 1 thi tra ve true va nguoc lai
              if (driver.getWindowHandles().size() == 1)
                         return true;
              else
                         return false;
  }
	  
}