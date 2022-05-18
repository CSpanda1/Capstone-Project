package com.hcl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectManagementToolTest
{
    @Test
    public void createProject()
    {
        System.out.println("Starting Project Management tool");

        //Create driver
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        //Maximize browser window
        driver.manage().window().maximize();

        //Open test page
        String url = "https://fierce-shelf-03672.herokuapp.com/";
        driver.get(url);
        System.out.println("Page is opened");

        // sleep of 2 seconds
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        //Click create project
        WebElement clickCreateProject = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//a[@href='/addProject']"));
        clickCreateProject.click();

        //Create Project name
        WebElement projectName = driver.findElement(By.name("projectName"));
        projectName.sendKeys("Capstone");

        //Create Unique Project ID
        WebElement uniqueProjectID = driver.findElement(By.name("projectIdentifier"));
        uniqueProjectID.sendKeys("10029");

        //Create Project Description
        WebElement projectDescription = driver.findElement(By.name("description"));
        projectDescription.sendKeys("To present and share our selenium knowledge.");

        //Start date of project

        WebElement startDate = driver.findElement(By.name("start_date"));
        startDate.sendKeys("2022-05-17");

        //End date of the project

        WebElement endDate = driver.findElement(By.name("end_date"));
        endDate.sendKeys("2022-05-20");

        //Submit Project

        WebElement submitButton = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//form/input[@type='submit']"));
        submitButton.click();


        // sleep of 2 seconds
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        //Verification
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/dashboard";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");

        //Close browser
        driver.quit();
    }
}
