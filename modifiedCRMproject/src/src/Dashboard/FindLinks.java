package src.Dashboard;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FindLinks {
   public  static WebDriver driver;
	public static void main(String[] args) throws IOException, InterruptedException {
	System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\LT-012\\Downloads\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();	
	driver.manage().deleteAllCookies();
//	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("https://bu2is.krify.com/native_talk_crm_web/admin/authentication");
	driver.findElement(By.xpath("//input[@placeholder=\"Email Address\"]")).sendKeys("swathi@infosys.com");
	driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys("Krify@1234");
	driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
     verifyLinks();
	}
	public static void verifyLinks() throws  IOException {
	//get the list of all the links
	List<WebElement> linkslist = driver.findElements(By.tagName("a"));
	//linkslist.addAll( driver.findElements(By.tagName("img")));
//	for(WebElement singleLink:linkslist) {if(singleLink.getAttribute("href")!=null){
//		
//
//		}
//	}

	System.out.println("Size of all links : " + linkslist.size());
	
	List<WebElement> activeLinks = new ArrayList<WebElement>();
	
	//Iterate linkslist : Excludde all links which doesn't have href attribute
	for(int i=0; i<linkslist.size(); i++) {
		if(linkslist.get(i).getAttribute("href") != null && (!linkslist.get(i).getAttribute("href").contains("javascript"))) {
			activeLinks.add(linkslist.get(i));
		}
	}
	System.out.println("Size of active links : " + activeLinks.size());
	
	//check the href url with http connection api
	//200 - ok
	//404 - not found
	//500 - internal
	//400 - bad request
	
	for(int j=0; j<activeLinks.size(); j++) {
		HttpURLConnection connection = (HttpURLConnection)new URL(activeLinks.get(j).getAttribute("href")).openConnection();
		connection.connect();
		String response = connection.getResponseMessage();
		connection.disconnect(); 
		//String TotalLinks = activeLinks.get(j).getAttribute("href");
	System.out.println(activeLinks.get(j).getAttribute("href")+ "----" + response );
	}
	
   }
}
		

