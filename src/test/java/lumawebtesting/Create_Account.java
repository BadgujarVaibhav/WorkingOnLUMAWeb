package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadXLSData;

public class Create_Account 
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
	public void create_account(String Firstname, String Lastname, String email, String password, String confirm)
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
		
		// click on drop down arrow button
		
		dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();
		
		// click on sign out button from drop down menu
		
		dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a")).click();
		
	}
	
	@Test(priority=3)
	
	public void close()
	{
		dr.close();
		
	}
	
	

}
