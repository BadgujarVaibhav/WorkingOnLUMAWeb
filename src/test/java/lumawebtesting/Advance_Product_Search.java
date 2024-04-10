package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Advance_Product_Search
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
	public void Advance_Search()
	{
		JavascriptExecutor down = (JavascriptExecutor) dr; // scroll down
		down.executeScript("window.scrollBy(0,2500)", "");
		
		//click on advance search
		dr.findElement(By.xpath("//a[text()='Advanced Search']")).click();
		
		// verify user navigated to advanced search product
		
		boolean advance_se=dr.findElement(By.xpath("//span[text()='Advanced Search']")).isDisplayed();
		assertTrue(advance_se,"Advanced Search");
		
		// Enter Product details to search particular Product
		
		dr.findElement(By.name("name")).sendKeys("Deirdre Relaxed-Fit Capri");
		dr.findElement(By.name("sku")).sendKeys("WP12");
		dr.findElement(By.name("description")).sendKeys("yoga practice");
		dr.findElement(By.name("short_description")).sendKeys("Comfortable");
		dr.findElement(By.name("price[from]")).sendKeys("50");
		dr.findElement(By.name("price[to]")).sendKeys("70");
		
		//click on search Button
		
		dr.findElement(By.xpath("//*[@id=\"form-validate\"]/div/div/button")).click();
		
		//verify user navigated to catalog advanced search
		
		boolean adv_search= dr.findElement(By.xpath("//span[text()='Catalog Advanced Search']")).isDisplayed();
		assertTrue(adv_search,"Catalog Advanced Search");
		
		JavascriptExecutor down1 = (JavascriptExecutor) dr; // scroll down to see searched product
		down1.executeScript("window.scrollBy(0,500)", "");
		
	}
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
	
}
