package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadCheckoutData;
import utilities.ReadXLSData;

public class Place_Order_After_Login 
{
public WebDriver dr;
	
	@BeforeClass
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		dr= new ChromeDriver(); // 1. Launch browser

		dr.manage().window().maximize();
		dr.get("https://magento.softwaretestingboard.com/"); // Navigate to url 'https://magento.softwaretestingboard.com/'

	}
	
	@Test(priority=1)
	public void HomePagetitleVerification()
	{
		String title= dr.getTitle(); // Verify that home page is visible successfully
		System.out.println(title);
		assertEquals(title,"Home Page","Incorrect Title");
		
	}
	
	@Test(priority=2,dataProviderClass=ReadXLSData.class, dataProvider="bvtdata")
	public void create_account(String Firstname, String Lastname, String email, String password, String confirm) throws InterruptedException
	{
		//click on create account
		dr.findElement(By.xpath("(//a[text()='Create an Account'])[1]")).click();
		
		//verify if page is redirected to create account page
		
		boolean create_account_text=	dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span")).isDisplayed();
		assertTrue(create_account_text,"Create New Customer Account");		
		
		// get data from website
		
		dr.findElement(By.id("firstname")).sendKeys(Firstname); // accept first name from the excel file.
		
		dr.findElement(By.id("lastname")).sendKeys(Lastname); // accept last name from the excel file.
		
		dr.findElement(By.id("email_address")).sendKeys(email); // accept email from the excel file.
		
		dr.findElement(By.id("password")).sendKeys(password);  // accept password from the excel file.
		
		dr.findElement(By.id("password-confirmation")).sendKeys(confirm); // accept confirm_password from the excel file.
		
		// click on create an account button
		
		dr.findElement(By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button")).click();
		
		
		// verify that account page is visible
		
		boolean account_page=dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")).isDisplayed();
		assertTrue(account_page,"My Account");
		
		// click on any category from page
		
		dr.findElement(By.xpath("//span[text()='Women']")).click();
				
		// verify page navigated to women's page
				
		boolean women_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(women_page,"Women");
				
		JavascriptExecutor js_dowm = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm.executeScript("window.scrollBy(0,2000)", "");
				
		// Hover on any product and click on it.
				
		dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[4]/div[1]/div[2]/div[3]/div/div/ol/li[1]/div/a/span/span/img")).click();
				
		// select and size, color and quantity and click "Add to cart"
				
		dr.findElement(By.id("option-label-size-143-item-167")).click();
		dr.findElement(By.id("option-label-color-93-item-56")).click();
		dr.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]")).click();
		Thread.sleep(3000);
				
		// click on proceed to checkout
				
		dr.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")).click();
		dr.findElement(By.xpath("//button[text()='Proceed to Checkout']")).click();
				
		
	}
	
	@Test(priority=3,dataProviderClass=ReadCheckoutData.class, dataProvider="data")
	public void data(String Email , String Firstname, String Lastname, String Company, String Address,
            String City, CharSequence[] Zipcode, CharSequence[] phone)
	{
		// get all data from excel sheet.
		
		dr.findElement(By.name("username")).sendKeys(Email);
		dr.findElement(By.name("firstname")).sendKeys(Firstname);
		dr.findElement(By.name("lastname")).sendKeys(Lastname);
		dr.findElement(By.name("company")).sendKeys(Company);
		dr.findElement(By.name("street[0]")).sendKeys(Address);
		dr.findElement(By.name("city")).sendKeys(City);
		WebElement  abc= dr.findElement(By.name("region_id"));
		Select state = new Select(abc);
		state.selectByValue("Arizona");
		dr.findElement(By.name("postcode")).sendKeys(Zipcode);
		
		WebElement  abc1= dr.findElement(By.name("country_id"));
		Select country = new Select(abc1);
		country.selectByValue("India");
		dr.findElement(By.name("telephone")).sendKeys(phone);
		
		// select any shopping method
		dr.findElement(By.name("ko_unique_5")).click();
		
		boolean verify_payment_page=dr.findElement(By.xpath("(//div[@class='step-title'])[3]")).isDisplayed();
		assertTrue(verify_payment_page,"Payment Method");
		
		//click on place order
		dr.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button")).click();
		
		//verify order placed successfully
		
	    boolean order= dr.findElement(By.xpath("//span[text()='Thank you for your purchase!']")).isDisplayed();
	    assertTrue(order,"Thank you for your purchase!");
	    
	    // click on drop down arrow button
		
	     dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();
	 		
	 	// click on sign out button from drop down menu
	 		
	 	dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a")).click();
	 		
			
	}
	
	@Test(priority=4)
	public void close()
	{
		dr.close();
	}

}
