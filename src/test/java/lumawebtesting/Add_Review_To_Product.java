package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Add_Review_To_Product 
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
	
	@Test(priority=2)
	public void Add_Review_to_product()
	{
		// select all product from page ex: women page
		
		dr.findElement(By.xpath("//span[text()='Women']")).click();
				
		// verify page navigated to women's page
				
		boolean women_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(women_page,"Women");
				
		JavascriptExecutor js_dowm = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm.executeScript("window.scrollBy(0,2000)", "");
		
		// click on any product
		
		dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[4]/div[1]/div[2]/div[3]/div/div/ol/li[2]/div/a/span/span/img")).click();
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,2000)", "");
		
		// click on review button
		
		dr.findElement(By.xpath("//*[@id=\"tab-label-reviews-title\"]")).click();
		
		JavascriptExecutor js_dowm2 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm2.executeScript("window.scrollBy(0,2000)", "");
		
		
		// click on rating starts
		
		dr.findElement(By.xpath("(//div[@class='control review-control-vote']//label)[1]")).click();
		
		// Enter other details to give rating to product
		
		dr.findElement(By.name("nickname")).sendKeys("Vaibhav B");
		
		dr.findElement(By.id("summary_field")).sendKeys("Nice Product!!!");
		
		dr.findElement(By.id("review_field")).sendKeys("This is a Very nice product and super comfortable!!!");
		
		// click on button to submit the review
		
		dr.findElement(By.xpath("//*[@id=\"review-form\"]/div/div/button")).click();
		
		// verify review submitted successfully.
		
		boolean msg= dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div")).isDisplayed();
		assertTrue(msg,"You submitted your review for moderation.");
			
		
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}

}
