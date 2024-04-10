package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sort_Products_By_Prize 
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
	public void Sort_Product()
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
				
		//click on any category under men's page for ex: bottom
		
		dr.findElement(By.xpath("//a[text()='Bottoms']")).click();
		
		//verify that category is displayed and confirmed test "Bottom"
		
		boolean Bottom= dr.findElement(By.xpath("(//span[text()='Bottoms'])[3]")).isDisplayed();
		assertTrue(Bottom,"Bottoms");
		
		JavascriptExecutor js_dowm = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm.executeScript("window.scrollBy(0,500)", "");
		
		//1. Before Filter Capture the Price.	
		List <WebElement> BeforeFilterPrice= dr.findElements(By.xpath("//span[text()='$27.00']"));
		
		//2. remove the $ symbol from the price and convert the string into Double
		List<Double> BeforeFilterPriceList= new ArrayList<>();
		
		for(WebElement p : BeforeFilterPrice)
		{
			BeforeFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
		
		}
		
		//3. Filter the price from the Drop down.
		Select dropdown= new Select(dr.findElement(By.className("sorter-options")));
		dropdown.selectByValue("price");
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,500)", "");
		
			
		//4. after filter capture the price.
		
		List <WebElement> AfterFilterPrice= dr.findElements(By.xpath("//span[text()='$27.00']"));		
		
		//5. remove the $ symbol from the price and convert the String into Double.
		
		List<Double> AfterFilterPriceList= new ArrayList<>();
		
		for(WebElement p : AfterFilterPrice)
		{
			AfterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
		
		}
		
		//6. Compare the values/ Assert the values 
		
		Collections.sort(BeforeFilterPriceList); // List will get price sorted in ascending order.
		
		Collections.reverse(BeforeFilterPriceList);// List will get price sorted in Descending order.
		
		Assert.assertEquals(BeforeFilterPriceList, AfterFilterPriceList);	
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
}
