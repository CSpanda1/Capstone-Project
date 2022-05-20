package com.hcl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ProjectManagementToolBrowserTest {
	private WebDriver driver;

    @Test
    public void chromeTest()
    {
        System.out.println("Starting Test in Chrome");

        //Create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe" );
        driver = new ChromeDriver();

        //Maximize browser window

        driver.manage().window().maximize();

        //Open Test page
        String url = "https://fierce-shelf-03672.herokuapp.com";
        driver.get(url);

        // sleep of 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Page is open.");

        WebElement word = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//h1[@class='display-4 text-center']"));
        String expectedWord = "Project";
        String actualErrorMessage = word.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedWord), "Actual error message does not contain expected. " +
                "\nActual: " + actualErrorMessage + "\nExpected: " + expectedWord);
    }

    @Test
    public void firefoxTest()
    {
        System.out.println("Starting Test in FireFox");

        //Create driver
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe" );
        driver = new FirefoxDriver();

        //Maximize browser window

        driver.manage().window().maximize();

        //Open Test page
        String url = "https://fierce-shelf-03672.herokuapp.com";
        driver.get(url);

        System.out.println("Page is open.");

        WebElement word = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//h1[@class='display-4 text-center']"));
        String expectedWord = "Project";
        String actualErrorMessage = word.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedWord), "Actual error message does not contain expected. " +
                "\nActual: " + actualErrorMessage + "\nExpected: " + expectedWord);
    }

    @Test
    public void edgeTest()
    {
        System.out.println("Starting Test in Edge browser");

        //Create driver
        System.setProperty("webdriver.edge.driver","src/main/resources/msedgedriver.exe" );
        driver = new EdgeDriver();

        //Maximize browser window

        driver.manage().window().maximize();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Open Test page
        String url = "https://fierce-shelf-03672.herokuapp.com";
        driver.get(url);

        System.out.println("Page is open.");

        WebElement word = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//h1[@class='display-4 text-center']"));
        String expectedWord = "Project";
        String actualErrorMessage = word.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedWord), "Actual error message does not contain expected. " +
                "\nActual: " + actualErrorMessage + "\nExpected: " + expectedWord);
    }
    
    @AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}
    
}
