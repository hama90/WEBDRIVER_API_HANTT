package selenium;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_05_DropdownList {
    WebDriver driver;
    WebDriverWait waitExplicit;
    JavascriptExecutor javascriptExecutor;

    @BeforeTest
    public void beforeTest() {
	driver = new FirefoxDriver();
	waitExplicit = new WebDriverWait(driver, 30);
	javascriptExecutor = (JavascriptExecutor) driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();

    }

   // @Test
    public void TC_01_DefaultDropdown() {
	driver.get("https://daominhdam.github.io/basic-form/index.html");

	/*
	 * Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html 
	 * Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
	 *  Step 03 - Chọn giá trị
	 * Automation Tester trong dropdown bằng phương thức selectVisible 
	 * Step 04 - Kiểm tra giá trị đã được chọn thành công 
	 * Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức
	 * selectValue Step 06 - Kiểm tra giá trị đã được chọn thành công 
	 * Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex Step 08 - Kiểm tra giá trị đã được chọn thành
	 * công Step 09 - Kiểm tra dropdown có đủ 5 giá trị
	 */
	WebElement jobRole01 = driver.findElement(By.xpath("//select[@id=\"job1\"]"));
	Select jobRoleSelect = new Select(jobRole01);

	// Step02
	Assert.assertFalse(jobRoleSelect.isMultiple());

	// Step03
	jobRoleSelect.selectByVisibleText("Automation Tester");

	// Step04
	Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Automation Tester");

	// Step05
	jobRoleSelect.selectByValue("manual");

	// Step06
	Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Manual Tester");

	// Step07
	jobRoleSelect.selectByIndex(3);

	// Step08
	Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Mobile Tester");

	// Step09
	Assert.assertEquals(jobRoleSelect.getOptions().size(), 5);

	/*
	 * Ly thuyet
	 * 
	 * //Get ra gia tri vua duoc duoc chon ; gán biến để lấy giá trị jobRoleSelect.getFirstSelectedOption().getText();
	 * 
	 * // Get ra tất cả các số lượng item trong box; gán biến để lấy giá trị jobRoleSelect.getOptions().size();
	 * 
	 * // Kiểm tra dropdown có hỗ trợ thuộc tính isMultiple ; dùng Assert để kiểm tra Assert.assertTrue(jobRoleSelect.isMultiple());
	 * 
	 * // select giá trị trong dropdown theo chỉ số; bắt đầu từ chỉ số 0 đến n-1 jobRoleSelect.selectByIndex(0);
	 * 
	 * // select giá trị trong dropdown theo @value trong HTML code jobRoleSelect.selectByValue("abc");
	 * 
	 * // select giá trị trong dropdown theo Text- giá trị hiển thị cho người dùng chọn jobRoleSelect.selectByVisibleText("");
	 * 
	 */

    }

   // @Test
    public void TC_02_CustomDropdownList_Jquery() throws Exception {
	driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	
	selectItemInCustomDropdown("//span[@id=\"number-button\"]", "//ul[@id=\"number-menu\"]//li[@class=\"ui-menu-item\"]", "19");

	// bước 5: kiểm tra giá trị đã được chọn thành công
	Assert.assertTrue(isElementIsDisplayed("//span[@id=\"number-button\"]//span[@class=\"ui-selectmenu-text\" and text()=\"19\"]"));
	;

	selectItemInCustomDropdown("//span[@id=\"number-button\"]", "//ul[@id=\"number-menu\"]//li[@class=\"ui-menu-item\"]", "1");
	Assert.assertTrue(isElementIsDisplayed("//span[@id=\"number-button\"]//span[@class=\"ui-selectmenu-text\" and text()=\"1\"]"));
	;

	selectItemInCustomDropdown("//span[@id=\"number-button\"]", "//ul[@id=\"number-menu\"]//li[@class=\"ui-menu-item\"]", "13");
	Assert.assertTrue(isElementIsDisplayed("//span[@id=\"number-button\"]//span[@class=\"ui-selectmenu-text\" and text()=\"13\"]"));
	;
	
	selectItemInCustomDropdown("//span[@id=\"number-button\"]", "//ul[@id=\"number-menu\"]//li[@class=\"ui-menu-item\"]", "2");
	Assert.assertTrue(isElementIsDisplayed("//span[@id=\"number-button\"]//span[@class=\"ui-selectmenu-text\" and text()=\"2\"]"));
	;
    }
    //@Test
    public void TC_03_CustomDropDownList_Angular() throws Exception {
	driver.get("https://material.angular.io/components/select/examples");
	
	selectItemInCustomDropdown("//mat-select[@placeholder='State']", "//mat-option/span", "Florida");
	Assert.assertTrue(isElementIsDisplayed("//mat-select[@placeholder='State']//span[text()='Florida']"));
	
	selectItemInCustomDropdown("//mat-select[@placeholder='State']", "//mat-option/span", "Delaware");
	Assert.assertTrue(isElementIsDisplayed("//mat-select[@placeholder='State']//span[text()='Delaware']"));
	
	selectItemInCustomDropdown("//mat-select[@placeholder='State']", "//mat-option/span", "Wisconsin");
	Assert.assertTrue(isElementIsDisplayed("//mat-select[@placeholder='State']//span[text()='Wisconsin']"));
	
	selectItemInCustomDropdown("//mat-select[@placeholder='State']", "//mat-option/span", "Alaska");
	Assert.assertTrue(isElementIsDisplayed("//mat-select[@placeholder='State']//span[text()='Alaska\']"));
	
    }
    
    //@Test
    public void TC_04_CustomDropDownList_Telerik() throws Exception {
	driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
	
	selectItemInCustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li", "Orange");
	Assert.assertTrue(isElementIsDisplayed("//span[@aria-owns='color_listbox']//span[text()='Orange']"));
	
	selectItemInCustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li", "Grey");
	Assert.assertTrue(isElementIsDisplayed("//span[@aria-owns='color_listbox']//span[text()='Grey']"));
	
	selectItemInCustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li", "Black");
	Assert.assertTrue(isElementIsDisplayed("//span[@aria-owns='color_listbox']//span[text()='Black']"));
    }
    
    //@Test
    public void TC_05_CustomDropDownList_VueJs() throws Exception {
	driver.get("https://mikerodham.github.io/vue-dropdowns/");
	
	selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
	Assert.assertTrue(isElementIsDisplayed("//div[@class='btn-group']/li[@class='dropdown-toggle' and contains(text(),'First Option')]"));
	
	selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
	Assert.assertTrue(isElementIsDisplayed("//div[@class='btn-group']/li[@class='dropdown-toggle' and contains(text(),'Second Option')]"));
	
	selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
	Assert.assertTrue(isElementIsDisplayed("//div[@class='btn-group']/li[@class='dropdown-toggle' and contains(text(),'Third Option')]"));
	
    }
    @Test
    public void TC_06_CustomDropdownList_MultipleItems() throws Exception {
	driver.get("http://multiple-select.wenzhixin.net.cn/examples/#basic.html ");
	By contentIframeXpath = By.xpath("//div[@class='content']//iframe") ;
	
	String[] items = { "January", "April" , "July"};
	String[] newItems = {"January", "April" , "July","October", "December"} ;
	
	WebElement contentIframe = driver.findElement(contentIframeXpath) ;
	driver.switchTo().frame(contentIframe) ;
	
	//ham selectMultipleItemInDropDown: chon 3 thang
	selectMultipleItemsInCustomDropdownList("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//span", items);
	Assert.assertTrue(checkItemSelected(items));
	
	//refresh web	
	driver.navigate().refresh();
	WebElement contentIframeRefresh = driver.findElement(contentIframeXpath); 
	driver.switchTo().frame(contentIframeRefresh); 
	
	//ham selectMultipleInDropDown: chon nhieu thang ~ 5 thang
	
	selectMultipleItemsInCustomDropdownList("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//span", newItems);
	Assert.assertTrue(checkItemSelected(newItems));
    }
    public void selectMultipleItemsInCustomDropdownList(String xpathParent, String allItemXpath, String[] expectedValueItem ) throws Exception {
	// Buoc 1: Click vao Dropdown 
		WebElement parentDropdown = driver.findElement(By.xpath(xpathParent));
		javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
	
	// Buoc 2: Chờ tất cả các giá trị trong dropdown được load thành công.
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allItemXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		System.out.println("Tat ca cac phan tu trong dropdown:" + allItems.size());
		
	// Buoc 3: Duyet qua het tat ca cac phan tu cho den khi thoa man dieu kien
		for (WebElement childElement : allItems) {
		    // { "January", "April" , "July"}
		    for (String item : expectedValueItem) {
			if (childElement.getText().equals(item)) {
			    //scroll den item can chon, neu item da isDisplayed thi khong can scroll 
			    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement); 
			    Thread.sleep(1500);
			    
			    //Buoc 4: Click vao item can chon
			    javascriptExecutor.executeScript("arguments[0].click();", childElement); 
			    Thread.sleep(2000);
			    
			    List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//span")); 
			    System.out.println("Item is selected = " + itemSelected.size());
			    if(expectedValueItem.length == itemSelected.size()) {
				break;
			    }
			}
			
		    }
		}
		    
    }
    
    public boolean checkItemSelected(String[] itemSelectedText) {
	List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//span")) ;
	int numberItemSelected = itemSelected.size(); 
	
	String allItemsSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span")).getText() ;
		System.out.println("Text da chon: " + allItemsSelectedText);
		
		if (numberItemSelected <= 3 && numberItemSelected > 0 ) {
		    for (String item : itemSelectedText) {
			if (allItemsSelectedText.contains(item)) {
			    break;
			}
			
		    }
		    return true; 
		}
		else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']//span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed() ;
			
		    }
    }
    
    public void selectItemInCustomDropdown(String xpathParent, String allItemXpath, String expectedItem) throws Exception  {

	// xpathParent = //span[@id="speed-button"]
	// allItemXpath = //ul[@id="number-menu"]//li[@class="ui-menu-item"]
	// Buoc 1: Click vao Dropdown 
	WebElement parentDropdown = driver.findElement(By.xpath(xpathParent));
	javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
	

	// Buoc 2: Chờ tất cả các giá trị trong dropdown được load thành công.
	waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allItemXpath)));

	List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	System.out.println("Tat ca cac phan tu trong dropdown:" + allItems.size());
	
	// dùng vòng lặp for
	// duyệt qua hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
	
	 for (int i=0; i< allItems.size() ; i++ )
	 { 
	     System.out.println("Gia tri moi lan get: " + allItems.get(i).getText());
	     
	     //allItems.get(0) = WebElement = click/sendKey/getText()/ ... --> các api hay dùng 
	     
	     if (allItems.get(i).getText().contains(expectedItem))
	     {
		 	 
	  //Bước 3: scroll đến item cần chọn
	  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", allItems.get(i));
	 
	 	Thread.sleep(2000);
	 	
	 //Bước 4: click vào item cần chọn 
	 	if (allItems.get(i).isDisplayed()) {
	 	    //Selenium click
	 	   allItems.get(i).click(); 
		 		}
	 	else {
	 	    //JS click
	 	    javascriptExecutor.executeScript("arguments[0].click();", allItems.get(i));
	 	}
	 	Thread.sleep(1500);
	 	break; 
	 } 
	 
	 }
    }

	// cách khác, dùng for-each
	/* for (WebElement childElement : allItems) {
	    if (childElement.getText().equals(expectedItem)) {
		System.out.println("Gia tri moi lan get la:" + childElement.getText());
		// scroll
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
		Thread.sleep(2000);
		// click vao item can chon
		childElement.click();
		break;
	    }
	}	 */
  
   
    
    public boolean isElementIsDisplayed(String xpathValue) {
	WebElement element = driver.findElement(By.xpath(xpathValue));
	if (element.isDisplayed()) {
	    System.out.println("Element is Displayed");
	    return true;
	} else {
	    System.out.println("Element is NOT displayed");
	    return false;
	}

    }

    @AfterTest
    public void afterTest() {
	driver.quit();
    }

    public int randomNumber() {
	Random random = new Random();
	int number = random.nextInt(99999);
	System.out.println("Random numnber =" + number);
	return number;

    }

}