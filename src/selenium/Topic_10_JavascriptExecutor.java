package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_JavascriptExecutor {
    WebDriver driver;
    // khai bao cac bien
    String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, email, password;

    // Locate element

    By customerNameTextbox = By.xpath("//input[@name=\"name\"]");
    By genderRadio = By.xpath("//input[@value=\"f\"]");
    By dateOfBirthTextbox = By.xpath("//input[@id='dob']");
    By addressTextArea = By.xpath("//textarea[@name=\"addr\"]");
    By cityTextbox = By.xpath("//input[@name=\"city\"]");
    By stateTextbox = By.xpath("//input[@name=\"state\"]");
    By pinTextbox = By.xpath("//input[@name=\"pinno\"]");
    By mobileNumberTextbox = By.xpath("//input[@name=\"telephoneno\"]");
    By emailTextbox = By.xpath("//input[@name=\"emailid\"]");
    By passwordTextbox = By.xpath("//input[@name=\"password\"]");
    By submitButton = By.xpath("//input[@name=\"sub\"]");

    
    

	@BeforeTest
	public void beforeTest() {
	    
	    	System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe") ; 
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		// khoi tao gia tri cho cac bien - form  Create New customer
		customerName = "SeleniumABC";
		gender = "female";
		dateOfBirth = "1990-01-01";
		address = "123 phoHue";
		city = "Hanoi";
		state = "Abc";
		pin = "171091";
		mobileNumber = "975650628";
		email = "autoHatest" + randomNumber()+ "@gmail.com";
		password = "ABC123";

	}

	//@Test
	public void TC_01() throws Exception {
	    
	    navigateToUrlByJS("http://live.guru99.com/") ;
	    
	    String domainName = executeForBrowser("return document.domain") ;
	    Assert.assertEquals(domainName, "live.guru99.com");
		
	    String Url = executeForBrowser("return document.URL") ;
	    Assert.assertEquals(Url, "http://live.guru99.com/");
	    
	    WebElement mobilelink = driver.findElement(By.xpath("//a[text()='Mobile']")); 
	    highlightElement(mobilelink);
	    clickToElementByJS(mobilelink); 
	    
	    WebElement samsungCartButton = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']"));
	    highlightElement(samsungCartButton);
	    clickToElementByJS(samsungCartButton);
	    
	    String innerText= executeForBrowser("return document.documentElement.innerText") ;
	    
	    //kiem tra tuong doi
	    Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
	    
	    //kiem tra tuyet doi
	    Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Samsung Galaxy was added to your shopping cart.");
	    
	    WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	    highlightElement(privacyPolicyLink);
	    clickToElementByJS(privacyPolicyLink) ; 
	    
	    String titlePrivacyPage = executeForBrowser("return document.title") ;
	    Assert.assertEquals(titlePrivacyPage, "Privacy Policy");
	    
	    scrollToBottomPage(); 
	    
	    WebElement text1 = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']")); 
	    highlightElement(text1);
	    Assert.assertTrue(text1.isDisplayed());;
	    
	    navigateToUrlByJS("http://demo.guru99.com/v4/") ;
	    
	    String newDomainName = executeForBrowser("return document.domain;") ;
	    Assert.assertEquals(newDomainName, "demo.guru99.com");
	    
	    
	    
	    
	}
	
	//@Test
	public void TC_02() throws Exception {
	    driver.get("http://demo.guru99.com/v4");
	    
	   
	driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr184340");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("tUgepAp");
	driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class=\"heading3\" and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	 
	driver.findElement(By.xpath("//a[contains(text(),\"New Customer\")]")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"heading3\" and text()=\"Add New Customer\"]")).isDisplayed());

	// input data for New customer
	driver.findElement(customerNameTextbox).sendKeys(customerName);
	driver.findElement(genderRadio).click();
	
	WebElement dateOfBirthTxtbox = driver.findElement(By.xpath("//input[@id='dob']"));
	removeAttributeInDOM(dateOfBirthTxtbox, "type") ;
	driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
	
	
	driver.findElement(addressTextArea).sendKeys(address);
	driver.findElement(cityTextbox).sendKeys(city);
	driver.findElement(stateTextbox).sendKeys(state);
	driver.findElement(pinTextbox).sendKeys(pin);
	driver.findElement(mobileNumberTextbox).sendKeys(mobileNumber);
	driver.findElement(emailTextbox).sendKeys(email);
	driver.findElement(passwordTextbox).sendKeys(password);
	driver.findElement(submitButton).click();
	
	Thread.sleep(3000);
	

	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),\"Customer Registered Successfully!!!\")]")).isDisplayed());

// verify expected data = actual data
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Customer Name\"]/following-sibling::td")).getText(), customerName);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Gender\"]/following-sibling::td")).getText(), gender);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Birthdate\"]/following-sibling::td")).getText(), dateOfBirth);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText(), address);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText(), city);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")).getText(), state);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")).getText(), pin);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")).getText(), mobileNumber);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")).getText(), email);
	
	Thread.sleep(3000);

	}
	
	@Test
	public void TC_03() throws Exception {
	    driver.get("http://live.guru99.com/");
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    //Step 02
	    WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")); 
	    clickToElementByJS(myAccountLink); 
	    
	    //Step 03: 
	    WebElement createAnAccountLink = driver.findElement(By.xpath("//a[@title='Create an Account']")) ;
	    clickToElementByJS(createAnAccountLink);
	    
	    WebElement firstNametxtbox = driver.findElement(By.xpath("//input[@name='firstname']"));
	    WebElement midleNametxtbox = driver.findElement(By.xpath("//input[@name='middlename']"));
	    WebElement lastNametxtbox = driver.findElement(By.xpath("//input[@name='lastname']"));
	    WebElement emailAddresstxtbox = driver.findElement(By.xpath("//input[@name='email']"));
	    WebElement passwordtxtbox = driver.findElement(By.xpath("//input[@name='password']"));
	    WebElement confirmpasswordtxtbox = driver.findElement(By.xpath("//input[@name='confirmation']"));
	    WebElement buttonRegister = driver.findElement(By.xpath("//button[@title='Register']")); 
	    
	  //Khoi tao gia tri 
	    
	    String firstName = "Ha"; 
	    String middleName = "Thu";
	    String lastName = "Nguyen";
	    String password ="123456";
	    String confirmation = "123456";
	    String emailAddress = "autoHatest" + randomNumber()+ "@123.com";
	    
	    //Step 04: Sendkey cho cac DN 
	    sendkeyToElementByJS(firstNametxtbox, firstName);
	    Thread.sleep(2000);
	    sendkeyToElementByJS(midleNametxtbox, middleName);
	    Thread.sleep(2000);
	    sendkeyToElementByJS(lastNametxtbox, lastName);
	    Thread.sleep(2000);
	    sendkeyToElementByJS(emailAddresstxtbox, emailAddress) ;
	    Thread.sleep(2000);
	    sendkeyToElementByJS(passwordtxtbox, password) ;
	    Thread.sleep(2000);
	    sendkeyToElementByJS(confirmpasswordtxtbox, confirmation) ;
	    Thread.sleep(2000);
	    
	    //Step 05: click Register button
	    clickToElementByJS(buttonRegister) ;
	    Thread.sleep(2000);
	    
	    //Step 05: Verify dang ky thanh cong 
	  //span[text()='Thank you for registering with Main Website Store.']
	    
	    String verifyMessage =  js.executeScript("return document.documentElement.innerText;").toString();
	    Assert.assertTrue(verifyMessage.contains("Thank you for registering with Main Website Store."));
	    
	  //Step 06: click Logout
	    WebElement accountBtn = driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']"));
	    clickToElementByJS(accountBtn); 
	    Thread.sleep(2000);
	    	    
	    WebElement logOutBtn = driver.findElement(By.xpath("//a[text()='Log Out']")); 
	    clickToElementByJS(logOutBtn) ;
	    Thread.sleep(10000);
	    
	    //Step 07
	    String titleHomePage = executeForBrowser("return document.title") ;
	    Assert.assertEquals(titleHomePage, "Home page");
	    
	    
	    
	    
	    
	}

	
	 public void highlightElement(WebElement element) throws Exception {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].style.border='6px groove red'", element);
	        Thread.sleep(1000);
	    }

	    public String executeForBrowser(String javaSript) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return (String) js.executeScript(javaSript);
	    }

	    public Object clickToElementByJS(WebElement element) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].click();", element);
	    }

	    public Object sendkeyToElementByJS(WebElement element, String value) {
	           JavascriptExecutor js = (JavascriptExecutor) driver;
	           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	    }

	    public void removeAttributeInDOM(WebElement element, String attribute) throws Exception {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	            Thread.sleep(3000);
	    }

	    public Object scrollToBottomPage() {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	    }

	    public Object navigateToUrlByJS(String url) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("window.location = '" + url + "'");
	    }
	
	    public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(99999);
		System.out.println("Random numnber =" + number);
		return number;

	    }
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}