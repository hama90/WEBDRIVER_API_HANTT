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

public class Topic_08_Popup_Frame_Iframe {
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
	public void TC_01_() {
	    driver.get("http://www.hdfcbank.com/");
	    /*
	     * Vừa handle popup - vừa handle iframe 
	     * Case 01: Nếu popup có xuất hiện --> thì nó phải được check isDisplayed ---> thực hiện close --> qua bước tiếp theo
	     * --> đang chạy testscript ---> refresh web 1 vài lần --> sẽ hiện popup 
	     * Case 2: Nếu popup không xuất hiện  --> nhảy qua bước tiếp theo luôn. 
	     * ==> chỉ chờ trong khoảng thời gian ngắm thôi, chứ ko chờ hết timeout 30s của hàm implicitlyWait ở trên. 
	     * 
	     */
	    /*
	     * findElement (WebElement):
 		* Nếu nó đi find element mà ko tìm thấy thằng nào hết: đánh fail testcase và bắn lỗi NoSuchElementException
		* Nếu nó tìm thấy duy nhất 1 element: thao tác vs element này -> click/ displayed/ sendkey/ getText/...
		* Nếu nó tìm thấy >1 element: nó sẽ luôn lấy thằng đầu tiên để thao tác
		
	     * findElements (List WebElement):
                * Ko tìm thấy thằng nào hết: trả về 1 list rỗng và KHÔNG đánh fail testcase
                * Nếu nó tìm thấy duy nhất 1 element: return lại 1 list chứa element này
                * Nếu nó tìm thấy nhiều hơn 1 element: return lại 1 list chứa những element này

	     */
	    /*
	     * Issues with Iframe
	     * Issue 01: Unable to locate element: driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"))  
	     * Issue 02: Unable to locate element: driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']") 
	     * --> do ở 2 iframe khác nhau 
	     * Issue 03: Cần switchTo.defaultContent() --> về lại TopWindow , trước khi chuyển sang 1 iframe khác --> đối với 1 web có nhiều iframe
	     */
		
	    //Khai báo notification  iframe
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	    int notificationIframeSize = notificationIframe.size() ; 
	    System.out.println("Notification Iframe displayed = " + notificationIframeSize);
	    
	    if (notificationIframeSize > 0 ) {
		
		//Switch qua iframe 
		    driver.switchTo().frame(notificationIframe.get(0));
		    
		    //Verify popup xuất hiện trên iframe
		    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
		    
		    //click icon x để đóng popup 
		    //driver.findElement(By.xpath("//div[@id='div-close']")).click(); 
		    // sử dụng click của javascript thay the ham click của Selenium
		    javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='div-close']"))); 
		    
		    System.out.println("Close popup successfully!");
		    
		    //Chuyen ve TopWindow
		    driver.switchTo().defaultContent(); 
		    
	    }
	    
	    System.out.println("Pass handle popup!");
	    
	    //Set timeout default
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    
	    //Switch qua Iframe chua text : "What are you looking for?" 
	   WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe")); 
	   driver.switchTo().frame(lookingForIframe) ;
	    
	    //Verify text xuat hien
	    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
	    
	    //Switch  ve TopWindow
	    driver.switchTo().defaultContent(); 
	    
	    //Switch qua iframe cho 6 anh
	    WebElement slidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	    driver.switchTo().frame(slidingBannerIframe) ;
	    
	    //Verify banner images
	   List <WebElement> bannerImages = driver.findElements(By.xpath("//img[@class='bannerimage']"));
	    System.out.println("So luong image o sliding banner la: " + bannerImages.size());
	    
	    Assert.assertEquals(bannerImages.size(), 6);
	    
	  //check element = real image 
	    for (WebElement image : bannerImages) {
		Assert.assertTrue(isImageLoadedSuccess(image));
	    }
	    
	    //Switch  ve TopWindow
	    driver.switchTo().defaultContent(); 
	      
	    //Verify flipbanner has 8 images
	    List <WebElement> flipBannerImages = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	    System.out.println("So luong img o flipBanner la: " + flipBannerImages.size());
	    Assert.assertEquals(flipBannerImages.size(), 8);
	    
	    //check element = real image 
	    for (WebElement flipImage : flipBannerImages) {
		Assert.assertTrue(isImageLoadedSuccess(flipImage));
	    // check image displayed
		Assert.assertTrue(flipImage.isDisplayed());
	    }
	
	}
	  public boolean isImageLoadedSuccess(WebElement imageElement) {
		return (boolean) javascript.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
		
	    }
	
	//@Test
	public void TC_02_() {
		String  homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
	}

	@Test
	public void TC_00_Sipmle () throws Exception {
	    driver.get("https://daominhdam.github.io/basic-form/index.html");
	    String parentId = driver.getWindowHandle(); 
	    System.out.println("ParentID : = " + parentId );
	    
	    //Result: ParentID : = {6c71756b-9f3d-4e95-904e-2c0235f5aa0c}
	    
	    //Click: Click here link 
	    driver.findElement(By.xpath("//a[@target='_blank' and text()='Click Here']")).click(); 
	    Thread.sleep(3000);
	    
	    // Get ra tat ca cac ID cua cac cua so dang co
	    Set<String> allWindows = driver.getWindowHandles() ; 
	    
	    //Switch sang 1 window khac voi parent window
	    for(String childID : allWindows) {
		System.out.println("Id window : " + childID);
		 if (!childID.equals(parentId) ) {
		     driver.switchTo().window(childID); 
		     Thread.sleep(3000);
		     break;
		 }
	    }
	    
	    //Verify title cua window hien tai
	    Assert.assertEquals(driver.getTitle(), "Google");
	    
	    //Switch tu window phu ve parent chinh
	    for (String childId :allWindows) {
		if (childId.equals(parentId))
		{
		    driver.switchTo().window(childId);
		    Thread.sleep(3000);
		    break;
		}
	    }
	    
	    //Verify
	    Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}