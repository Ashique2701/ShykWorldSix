package steepdefination;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SteppDepffin {
	WebDriver driver;
	String parentwindow;

	@Given("^user launch Chrome browser & user opens URL$")
	public void user_launch_Chrome_browser_user_opens_URL() throws Throwable {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ashique\\eclipse\\java-2021-03\\eclipse\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.bankofamerica.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Then("^navigates to Home Loans$")
	public void navigates_to_Home_Loans() throws Throwable {

		String parentwindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id=\"navHomeLoans\"]/span[3]")).click();
	}

	@Then("^search for finding a home$")
	public void search_for_finding_a_home() throws Throwable {
		driver.findElement(By.id("findAHome")).click();

	}

	@Then("^open child window for searching new construction$")
	public void open_child_window_for_searching_new_construction() throws Throwable {
		driver.findElement(By.name("home_38")).click();
		Set<String> allwindow = driver.getWindowHandles();
		int count = allwindow.size();
		System.out.println(count);
		for (String child : allwindow) {
			if (!parentwindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				Thread.sleep(1000);

				boolean result = driver
						.findElement(By.xpath("//h2[text()='Find New Home Communities in these states']"))
						.isDisplayed();
				Assert.assertTrue(result);
				System.out.println("We are in childwindow ");
			}
		}
	}

	@Then("^close child window$")
	public void close_child_window() throws Throwable {
		driver.close();
	}

	@Then("^browser quit$")
	public void browser_quit() throws Throwable {
		driver.switchTo().window(parentwindow);
		System.out.println("We are parentwindow");
		driver.quit();
	}

}
