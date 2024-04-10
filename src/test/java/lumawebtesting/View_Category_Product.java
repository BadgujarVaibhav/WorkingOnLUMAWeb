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

public class View_Category_Product
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
	public void View_Cate_Product()
	{
		// select any category ex: women
		
		dr.findElement(By.xpath("//span[text()='Women']")).click();
		
		// verify user navigated to women's page and verify category's are visible on left side bar.
		
		// verify page navigated to women's page
		
		boolean women_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(women_page,"Women");
		
		//verify category's are visible on left side bar.
		
		boolean verify_cate= dr.findElement(By.xpath("//dt[text()='Category']")).isDisplayed();
		assertTrue(verify_cate,"Category");
		
		//click on any category under women's page for ex: Tops
		
		dr.findElement(By.xpath("//a[text()='Tops']")).click();
		
		//verify that category is displayed and confirmed test "Tops"
		
		boolean Tops= dr.findElement(By.xpath("(//span[text()='Tops'])[3]")).isDisplayed();
		assertTrue(Tops,"Tops");
		
		// select any of the category from the tops for ex: Jackets
		
		dr.findElement(By.xpath("(//div[@class='filter-options-title'])[1]")).click();
		dr.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[1]/div[2]/ol/li[1]/a")).click();
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,500)", "");
		
		// verify user navigated to category under jackets 
		
		boolean jackets= dr.findElement(By.xpath("(//span[text()='Jackets'])[3]")).isDisplayed();
		assertTrue(jackets,"Jackets");
		
		
		
		
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
}
