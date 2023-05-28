import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import com.aventstack.extentreports.util.Assert;

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
		
		//Enter and invalid Project name and validate the text
       driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Invalid");
		
		String value = driver.findElement(By.tagName("h4")).getText();
		System.out.println(value);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name =\"search\"]")).sendKeys(Keys.ESCAPE);
		
		
		//driver.findElement(By.xpath("//input[@name='search']")).click();
		//driver.findElement(By.xpath("//input[@name='search']")).clear();
		Thread.sleep(5000);
		//Enter valid project Name
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("SDETTesting");
		
		driver.findElement(By.xpath("//span[text()='SDETtesting']")).click();
		
		//Collect the list of issues in the board
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
		
	        
	        String currentUrl = driver.getCurrentUrl();
	     
	        // Open a new tab and Create a new issues
			driver.switchTo().newWindow(Window.TAB);
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {

				driver.switchTo().window(handle);
			}
			driver.navigate().to(currentUrl);
	        
	     // Create issue
			driver.findElement(By.xpath("//button[@id=\"createGlobalItem\"]")).click();

			// Summary
			String created_Bug = "Test case - New tab";
			driver.findElement(By.xpath("//input[@name='summary']")).sendKeys(created_Bug);
			driver.findElement(By.xpath("//span[text() = 'Assign to me']")).click();
			driver.findElement(By.xpath("(//button/span[text() ='Create'])[2]")).click();
			System.out.println("Issue created");
			driver.close();

			// Refresh and validate does the board has newly created issue
			driver.switchTo().window(windowHandles.iterator().next());
			driver.navigate().refresh();
			Assert.assertFalse(task_List.contains(created_Bug));

			// Create a bug with blocked by newly created issue
			driver.findElement(By.xpath("//span[text()='Create']")).click();
			driver.findElement(By.xpath("//input[@name='summary']")).sendKeys("Test");
			driver.findElement(By.xpath("//*[text()='blocks']")).click();
			driver.findElement(By.xpath("//*[text()='is blocked by']")).click();
			driver.findElement(By.xpath("//*[text()='Select Issue']")).sendKeys("Test2");
			driver.findElement(By.xpath("(//span[text()='Create'])[2]")).click();
			driver.findElement(By.xpath("//*[text()='linkedIssue']")).click();
			driver.findElement(By.xpath("//*[text()='To Do']")).click();
			driver.findElement(By.xpath("//*[text()='Done']")).click();
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			if (driver.findElement(By.xpath("//span[text()='tes']/following::span[2]")).isDisplayed())
				System.out.println("Passed");

			driver.close();

		}
	}
			
		    
				
		
		
		
		
	}

}
