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

public class Topic_04_Textbox_TextArea {
    WebDriver driver;

    // khai bao cac bien
    String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, email, password, customerId;

    String editAddress, editCity, editState, editPin, editPhone, editEmail;

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
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://demo.guru99.com/v4");
	driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr184340");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("tUgepAp");
	driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class=\"heading3\" and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

	// khoi tao gia tri cho cac bien - form  Create New customer
	customerName = "SeleniumABC";
	gender = "female";
	dateOfBirth = "1990-01-01";
	address = "123 phoHue";
	city = "Hanoi";
	state = "Abc";
	pin = "171090";
	mobileNumber = "975650628";
	email = "autoHatest" + randomNumber()+ "@gmail.com";
	password = "ABC123";

	//khoi tao gia tri cho cac bien - form Edit customer
	
	editAddress = "456 hangBai";
	editCity = "HCM";
	editState = "Quan 1";
	editPin = "020388";
	editPhone = "979529083";
	editEmail = "editauto" + randomNumber() + "@gmail.com" ;
    }

    @Test
    public void TC_01_CreateNewCustomer() throws InterruptedException {

	driver.findElement(By.xpath("//a[contains(text(),\"New Customer\")]")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"heading3\" and text()=\"Add New Customer\"]")).isDisplayed());

	// input data for New customer
	driver.findElement(customerNameTextbox).sendKeys(customerName);
	driver.findElement(genderRadio).click();
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

	customerId = driver.findElement(By.xpath("//td[contains(text(),\"Customer ID\")]/following-sibling::td")).getText();
	
	System.out.println("CustomerID tai TC01 la: " + customerId);

    }

    @Test
    public void TC_02_EditCustomer() {

	driver.findElement(By.xpath("//a[text()=\"Edit Customer\"]")).click();

	driver.findElement(By.xpath("//input[@name=\"cusid\"]")).sendKeys(customerId);

	System.out.println("CustomerID tai TC01 la: " + customerId);
	driver.findElement(By.xpath("//input[@name= \"AccSubmit\"]")).click();
	
	//Input data for Edit customer form 		
	driver.findElement(addressTextArea).clear();
	driver.findElement(addressTextArea).sendKeys(editAddress);
	driver.findElement(cityTextbox).clear();
	driver.findElement(cityTextbox).sendKeys(editCity);
	driver.findElement(stateTextbox).clear();
	driver.findElement(stateTextbox).sendKeys(editState);
	driver.findElement(pinTextbox).clear();
	driver.findElement(pinTextbox).sendKeys(editPin);
	driver.findElement(mobileNumberTextbox).clear();
	driver.findElement(mobileNumberTextbox).sendKeys(editPhone);
	driver.findElement(emailTextbox).clear();
	driver.findElement(emailTextbox).sendKeys(editEmail);
	driver.findElement(submitButton).click();
	
	Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Customer details updated Successfully!!!\"]")).isDisplayed());
	
	//verify cac gia tri hien thi sau khi cap nhat thanh cong 
	
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText(), editAddress);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText(), editCity);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")).getText(), editState);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")).getText(), editPin);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")).getText(), editPhone);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")).getText(), editEmail);

	
	
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