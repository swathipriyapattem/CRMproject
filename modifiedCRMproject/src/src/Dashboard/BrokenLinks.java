package src.Dashboard;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenLinks {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\LT-012\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://bu2is.krify.com/native_talk_crm_web/admin/authentication");
		driver.findElement(By.xpath("//input[@placeholder=\"Email Address\"]")).sendKeys("swathi@infosys.com");
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys("Krify@1234");
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class=\"menu-text\"]")));
		//driver.switchTo().alert().accept();	
		List<WebElement> SideMenuList = driver.findElements(By.xpath("//span[@class=\"menu-text\"]"));
		for(WebElement SideMenuText : SideMenuList) {
			 System.out.println(SideMenuText.getText());	
	    	if(SideMenuText.getText().contains("Setup")) {
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click();", SideMenuText);
	    		//SideMenuText.click(); 
	    		   break;
	    	     }
		     }	
		Thread.sleep(2000);
		System.out.println("----------------------");
		
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class=\"menu-text\"]")));
		
		List<WebElement> setUpMenu = driver.findElements(By.xpath("//span[@class=\"menu-text\"]"));
		for(WebElement SetUpMenuText : setUpMenu) {
			 System.out.println(SetUpMenuText.getText());	
	    	if(SetUpMenuText.getText().contains("Email Templates")) {
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click();", SetUpMenuText);
	    		//SideMenuText.click(); 
	    		   break;
	    	     }
		     }	
		
		
		//get the list of all the links
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		System.out.println("\nSize of all links and img : " + links.size());
		List<String> urlList = new ArrayList<String>();
		for(WebElement e : links) {
			String url = e.getAttribute("href");
			   urlList.add(url);
			//checkBrokenLink(url);
		}
		urlList.parallelStream().forEach(e -> checkBrokenLink(e));
		driver.quit();
	}
  public static void checkBrokenLink(String linkUrl)
  {
      try
      {
          URL url = new URL(linkUrl);

          //Now we will be creating url connection and getting the response code
          HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
          httpURLConnect.setConnectTimeout(5000);
          httpURLConnect.connect();
          if(httpURLConnect.getResponseCode()>=400)
          {
          	System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage()+ " is a broken link");
          }    
     
          //Fetching and Printing the response code obtained
          else{
              System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
          }
      }catch (Exception e) {
    }
 }

}
