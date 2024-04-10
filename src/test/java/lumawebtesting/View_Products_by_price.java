package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class View_Products_by_price
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
	public void View_Products_price()
	{
		// select any category ex: men
		
		dr.findElement(By.xpath("//span[text()='Men']")).click();
		
		// verify user navigated to men's page and verify category's are visible on left side bar.
		
		// verify page navigated to men's page
				
		boolean men_page=dr.findElement(By.xpath("(//span[text()='Men'])[2]")).isDisplayed();
		assertTrue(men_page,"Men");
				
		//verify category's are visible on left side bar.
				
		boolean verify_cate= dr.findElement(By.xpath("//dt[text()='Category']")).isDisplayed();
		assertTrue(verify_cate,"Category");
				
		//click on any category under men's page for ex: Tops
		
		dr.findElement(By.xpath("//a[text()='Tops']")).click();
		
		//verify that category is displayed and confirmed test "Tops"
		
		boolean Tops= dr.findElement(By.xpath("(//span[text()='Tops'])[3]")).isDisplayed();
		assertTrue(Tops,"Tops");
		
		// click on the price from the left side bar and select any price range
				
		dr.findElement(By.xpath("(//div[@class='filter-options-title'])[4]")).click();
		dr.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[4]/div[2]/ol/li[2]/a")).click();
		
		// verify user is able to see selected price from the price section
		
		boolean price_verify= dr.findElement(By.xpath("//*[@id=\"layered-filter-block\"]/div[2]/div[1]/ol/li/span[2]")).isDisplayed();
		assertTrue(price_verify,"Price$20.00 - $29.99");
		
		
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
}
