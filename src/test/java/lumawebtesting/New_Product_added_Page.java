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

public class New_Product_added_Page
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
	public void check_new_products()
	{
		// click on what's new page
		
		dr.findElement(By.xpath("(//nav[@class='navigation']//ul//li//a//span)[1]")).click();
		
		// verify user is navigated to what's new page
		
		boolean whats_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(whats_page,"What's New");
		
		// verify all new products are visible
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,1300)", "");
		
		// click on anyone product
		
		dr.findElement(By.xpath("//a[@title='Phoebe Zipper Sweatshirt']")).click();
		
		// verify clicked on product and all details related to products
		
		boolean verify_clicked_product= dr.findElement(By.xpath("//span[text()='Phoebe Zipper Sweatshirt']")).isDisplayed();
		assertTrue(verify_clicked_product,"Phoebe Zipper Sweatshirt");
		
	}
	
	@Test(priority=3)
	
	public void close()
	{
		dr.close();
		
	}
}
