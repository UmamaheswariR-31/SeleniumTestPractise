import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClassProblem {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://id.atlassian.com/login");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("uma31be@gmail.com");
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Coffee@31");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
		
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//div[text()='Jira Software']")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Invalid");
		
		String value = driver.findElement(By.tagName("h4")).getText();
		System.out.println(value);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name =\"search\"]")).sendKeys(Keys.ESCAPE);
		
		
		//driver.findElement(By.xpath("//input[@name='search']")).click();
		//driver.findElement(By.xpath("//input[@name='search']")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("SDETTesting");
		
		driver.findElement(By.xpath("//span[text()='SDETtesting']")).click();
		
		List<WebElement> findElements= driver.findElements(By.xpath("//*[@alt='Task']"));
		 
		List<String> to_do_list = new ArrayList<String>();
	        for (int i = 1; i < (findElements.size() + 1); i++) {
	            WebElement iteam = driver.findElement(By.xpath("(//span[@class=\"sc-15cfu5s-1 fKpSAB\"])[" + i + "]"));
	            JavascriptExecutor je = (JavascriptExecutor) driver;
	            je.executeScript("arguments[0].scrollIntoView(true);", iteam);
	            String card_text = iteam.getText();
	            to_do_list.add(card_text);
	            System.out.println(card_text);
	        }
		
		
				
		
		
		
		
	}

}
