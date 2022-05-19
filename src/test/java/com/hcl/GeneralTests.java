package com.hcl;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GeneralTests {
	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("firefox") String browser) {

		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/geckodriver.exe");
			driver = new ChromeDriver();
			break;

		default:
			System.out.println("Do not know how to start " + browser + ", starting firefox");
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}

		driver.manage().window().maximize();
	}
	
	@Parameters({ "testName", "name", "id", "description", "beginDate", "stopDate" })
	@Test(priority = 1, groups = { "formTests", "positiveTest" })
	public void formPositiveTest(String testName, String name, String id, String description, String beginDate, String stopDate) {
		System.out.println("Starting " + testName);


		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		System.out.println("Page is opened.");
		
		WebElement createProject = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProject.click();
		
		sleep(0500);
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys(name);
		
		WebElement uniqueId = driver.findElement(By.name("projectIdentifier"));
		uniqueId.sendKeys(id);
		
		WebElement projectDescription = driver.findElement(By.name("description"));
		projectDescription.sendKeys(description);
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys(beginDate);
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys(stopDate);
		
		WebElement submitProject = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitProject.click();
		
		Boolean invalidFeedbackPresent = driver.findElements(By.xpath("//div[@class='invalid-feedback']")).size() > 0;
		Assert.assertTrue(invalidFeedbackPresent==false, "Invalid Feedback is displayed");
		
		sleep(0750);
		
		WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject.click();

		sleep(0500);
		
		Alert alert = driver.switchTo().alert();

		sleep(0500);

		alert.accept();
		
		sleep(0500);
	}

	@Parameters({ "testName", "name", "id", "description", "beginDate", "stopDate" })
	@Test(priority = 2, groups = { "formTests", "negativeTest" })
	public void formNegativeTest(String testName, String name, String id, String description, String beginDate, String stopDate) {
		System.out.println("Starting " + testName);


		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		System.out.println("Page is opened.");
		
		WebElement createProject = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProject.click();
		
		sleep(0500);
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys(name);
		
		WebElement uniqueId = driver.findElement(By.name("projectIdentifier"));
		uniqueId.sendKeys(id);
		
		WebElement projectDescription = driver.findElement(By.name("description"));
		projectDescription.sendKeys(description);
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys(beginDate);
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys(stopDate);
		
		WebElement submitProject = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitProject.click();
		
		Boolean invalidFeedbackPresent = driver.findElements(By.xpath("//div[@class='invalid-feedback']")).size() > 0;
		try {
			Assert.assertTrue(invalidFeedbackPresent==true, "Invalid Feedback is not displayed");
		} catch (AssertionError e) {
			WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
			deleteProject.click();

			sleep(0500);
			
			Alert alert = driver.switchTo().alert();

			sleep(0500);

			alert.accept();
			
			sleep(0500);
			
			throw e;
		}
		
	}
	
	@Parameters({ "testName", "name", "id", "updateId", "description", "beginDate", "stopDate" })
	@Test(priority = 3, groups = { "formTests", "updateTest" })
	private void updateId(String testName, String name, String id, String updateId, String description, String beginDate, String stopDate) {
		System.out.println("Starting " + testName);


		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		System.out.println("Page is opened.");
		
		WebElement createProject = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProject.click();
		
		sleep(0500);
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys(name);
		
		WebElement uniqueId = driver.findElement(By.name("projectIdentifier"));
		uniqueId.sendKeys(id);
		
		WebElement projectDescription = driver.findElement(By.name("description"));
		projectDescription.sendKeys(description);
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys(beginDate);
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys(stopDate);
		
		WebElement submitProject = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitProject.click();
		
		sleep(0750);
		
		WebElement updateProject = driver.findElement(By.xpath("//a[@href='/updateProject/UPTST']"));
		updateProject.click();

		sleep(0750);
		
		try {
			WebElement updateProjectId = driver.findElement(By.name("projectIdentifier"));
			updateProjectId.sendKeys(updateId);
		} catch (ElementNotInteractableException e) {
			WebElement submitUpdate = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
			submitUpdate.click();
		
			WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
			deleteProject.click();

			sleep(0500);
		
			Alert alert = driver.switchTo().alert();

			sleep(0500);

			alert.accept();
		
			sleep(0500);
		}

	}
	
	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
