package codeboxAutomation;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests {

	String Target_website ="https://codenboxautomationlab.com/practice/";
	WebDriver driver = new EdgeDriver();
	Random rand=new Random();
	
	
	@BeforeTest 
	public void setup() {
		driver.get(Target_website);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	
	
	@Test(enabled=false)
	public void RadioButton() {
		List<WebElement> Radio_Group = driver.findElements(By.className("radioButton"));
		int random_Button = rand.nextInt(Radio_Group.size());
	    Radio_Group.get(random_Button).click();
	    Boolean ActualResult=Radio_Group.get(random_Button).isSelected();
	    Boolean ExpectedResult=true;
	    Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	
	
	@Test(enabled=false)
	public void Dynamic_Dropdown() throws InterruptedException {
		
		WebElement text_box = driver.findElement(By.id("autocomplete"));
		String[] countries = {"USA","JO","SYR"};
		int rendom_country=rand.nextInt(countries.length);
		text_box.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		Thread.sleep(1000);
		text_box.sendKeys(countries[rendom_country]);
		Thread.sleep(2000);
		List<WebElement> suggestions = driver.findElements(By.className("ui-menu-item-wrapper"));
		int rendom_sug=rand.nextInt(suggestions.size());
		suggestions.get(rendom_sug).click();
		System.out.println(text_box.getText());
	}
	
	
	@Test(enabled=false)
	public void Static_Dropdown() throws InterruptedException {
		WebElement selecting=driver.findElement(By.id("dropdown-class-example"));
		Select Myselect = new Select(selecting);
		selecting.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		Myselect.selectByIndex(1);
		Thread.sleep(2000);
		Myselect.selectByValue("option2");
		Thread.sleep(2000);
		Myselect.selectByVisibleText("API");
		Thread.sleep(2000);
	}
	
	@Test (enabled=false)
	public void Checkbox() throws InterruptedException {
		WebElement checkbox= driver.findElement(By.id("checkbox-example"));
		List<WebElement> Allcheckboxes = checkbox.findElements(By.tagName("input"));
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",checkbox);
		
		Thread.sleep(1000);
		for(int i = 0 ; i <Allcheckboxes.size() ; i++ ){
			Allcheckboxes.get(i).click();
			Assert.assertEquals(Allcheckboxes.get(i).isSelected(), true);
		}
	}
		
	@Test(enabled=false)
	public void Switch_Window() throws InterruptedException {
		
		WebElement Switch_Window_button=driver.findElement(By.id("openwindow"));
		//scroll the page down using javaScript
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",Switch_Window_button);
		Thread.sleep(2000);

		Switch_Window_button.click();
		
		Set<String> handles =driver.getWindowHandles();
		List<String> AllWindows = new ArrayList<>(handles);
		
		driver.switchTo().window(AllWindows.get(0));
		System.out.println(driver.getTitle());
		
		driver.switchTo().window(AllWindows.get(1));
		System.out.println(driver.getTitle());
		
	}
	
	
	@Test (enabled=false)
	public void Switch_Tab() throws InterruptedException {
		WebElement Switch_Tab_button=driver.findElement(By.id("opentab"));
		
		//scroll the page down using JavaScript
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",Switch_Tab_button);
		Thread.sleep(1000);
		Switch_Tab_button.click();
		
		Set<String> handles = driver.getWindowHandles();
		List<String> AllTabs = new ArrayList<>(handles);
		Thread.sleep(2000);
		driver.switchTo().window(AllTabs.get(0));
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.switchTo().window(AllTabs.get(1));
		System.out.println(driver.getTitle());
	}
	
	@Test (enabled=false)
	public void Switch_To_Alert() throws InterruptedException {
		WebElement TEXT_BOX = driver.findElement(By.id("name"));
		WebElement alert_button = driver.findElement(By.id("alertbtn"));
		WebElement confirm_button = driver.findElement(By.id("confirmbtn"));
		String Name="sara";
		//scroll the page down using JavaScript
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",TEXT_BOX);
		Thread.sleep(1000);

		
		TEXT_BOX.sendKeys(Name);
		confirm_button.click();
		Thread.sleep(1000);

		System.out.println(driver.switchTo().alert().getText().contains(Name));
		Boolean ActualResult = driver.switchTo().alert().getText().contains(Name);
		Assert.assertEquals(true, ActualResult);
		
		//driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();

	}
	
	@Test (enabled=false)
	public void Web_Table() throws InterruptedException {
		

		WebElement Table = driver.findElement(By.id("product"));
		List<WebElement> All_Data = Table.findElements(By.tagName("td"));
		
		//scroll the page down using JavaScript
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",Table);
		Thread.sleep(1000);
		
		for(int i = 0 ; i <All_Data.size();i++)
		{
			System.out.println(All_Data.get(i).getText());
			
		}
		
		driver.findElement(By.id("name")).sendKeys(All_Data.get(0).getText());

	}
//"window.scrollBy(X,Y)"
		@Test (enabled=false)
		public void Hide_And_Show() throws InterruptedException {
			
			WebElement Hide_Button = driver.findElement(By.id("hide-textbox"));
			WebElement Show_Button = driver.findElement(By.id("show-textbox"));
			WebElement Text_Box = driver.findElement(By.id("displayed-text"));
			
			//scroll the page down using JavaScript
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",Hide_Button);
			Thread.sleep(1000);
			
			Show_Button.click();
			Thread.sleep(1000);

			boolean ActualResult_1 = Text_Box.isDisplayed();
			boolean ExpectedResult_1 = true;
			Assert.assertEquals(ActualResult_1, ExpectedResult_1);

			Hide_Button.click();
			boolean ActualResult_2 = Text_Box.isDisplayed();
			boolean ExpectedResult_2 = false;

			Assert.assertEquals(ActualResult_2, ExpectedResult_2);

		}
		
		@Test (enabled=false)
		public void Enabled_Disabled() throws InterruptedException{
			
		WebElement disable_Button = driver.findElement(By.id("disabled-button"));
		WebElement Enable_Button = driver.findElement(By.id("enabled-button"));
		WebElement Text_Box = driver.findElement(By.id("enabled-example-input"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",disable_Button);
		Thread.sleep(1000);
		
		Enable_Button.click();
		Boolean ActualResult_1 = Text_Box.isEnabled();
		Assert.assertEquals(true, ActualResult_1);
		Thread.sleep(1000);

		disable_Button.click();
		Thread.sleep(1000);
		Boolean ActualResult_2 = Text_Box.isEnabled();
		Assert.assertEquals(false, ActualResult_2);
		
		}
		
		
		
//		Actions is a Selenium class used for advanced user interactions, such as:
//		-	mouse hover
//		-	drag and drop
//		-	right click
//		-	double click
//		-	keyboard combinations	
		
		
//		moveToElement(mousehover) → move mouse over element
//		build() → create the action
//		perform() → execute the action
		
	   @Test(enabled=false)
	   public void Mouse_Hover() throws InterruptedException {
		   WebElement mousehover=driver.findElement(By.id("mousehover"));

		   JavascriptExecutor js = (JavascriptExecutor)driver;
		   js.executeScript("arguments[0].scrollIntoView(true);",mousehover);
		   Thread.sleep(1000);
		   
		   
		   Actions action = new Actions(driver);
		   action.moveToElement(mousehover).build().perform();
		   driver.findElement(By.linkText("Reload")).click();
	   }
		
	   
	   @Test (enabled=false)
	   public void calender() throws IOException , InterruptedException {
		   
		   WebElement Booking_Calendar_link = driver.findElement(By.linkText("Booking Calendar"));
		   
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",Booking_Calendar_link);
			Thread.sleep(2000);
			
			Booking_Calendar_link.click();
		   
			Date mydate = new Date();
		    String fileName =mydate.toString().replace(":","-");
		    
		    Set<String> handles = driver.getWindowHandles();
		    List<String> All_Tabs = new ArrayList<>(handles);
		    
		    driver.switchTo().window(All_Tabs.get(1));
		    Thread.sleep(7000);
		    TakesScreenshot ts = (TakesScreenshot)driver;
		    File file = ts.getScreenshotAs(OutputType.FILE);
		    
		    FileUtils.copyFile(file, new File("./Screenshot/"+" "+fileName+".jpg"));
		    
	   }
	   
	   
	   
	   
	   
	   @Test (enabled=false)
	   public void iFrame() throws InterruptedException {
		  WebElement frame = driver.findElement(By.id("courses-iframe"));
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  js.executeScript("arguments[0].scrollIntoView(true);", frame);
		  
		  
		  Thread.sleep(2000);
		  driver.switchTo().frame(frame);
		  Thread.sleep(2000);
		  driver.findElement(By.cssSelector(".ct-mobile-meta-item.btn-nav-mobile.open-menu")).click();
		   
	   }
}
