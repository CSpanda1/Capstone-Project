package com.hcl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectManagementToolNegativeTests
{


    @Test
    public void createProjectForm13()
    {
        //This method check to see if a duplicate unique id can be created
        System.out.println("Testing to see if a duplicate id can be created");

        //Web driver
        WebDriver driver = getWebDriver();

        //sleep
        sleep(2000);

        //Click create project
        WebElement clickCreateProject = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//a[@href='/addProject']"));
        clickCreateProject.click();

        //Create Project name
        WebElement projectName = driver.findElement(By.name("projectName"));
        projectName.sendKeys("capstone project23");

        //Create Unique Project ID
        WebElement uniqueProjectID = driver.findElement(By.name("projectIdentifier"));
        uniqueProjectID.sendKeys("test1");

        //Create Project Description
        WebElement projectDescription = driver.findElement(By.name("description"));
        projectDescription.sendKeys("To present and share our selenium knowledge.");

        //Start date of project

        WebElement startDate = driver.findElement(By.name("start_date"));
        startDate.sendKeys("2022-05-20");

        //End date of the project

        WebElement endDate = driver.findElement(By.name("end_date"));
        endDate.sendKeys("2022-05-21");

        //Submit Project

        WebElement submitButton = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//form/input[@type='submit']"));
        submitButton.click();

        //Sleep
        sleep(2000);

        //Verifications
        WebElement errorMessage = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//div[@class='project']//form//div[@class='invalid-feedback']"));
        String expectedErrorMessage = "Project ID 'TEST1' already exists";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Actual error message does not contain expected. " +
                "\nActual: " + actualErrorMessage + "\nExpected: " + expectedErrorMessage);


        //driver quit
        quitDriver(driver);
    }


    public void quitDriver(WebDriver driver) {
        //Quit
        driver.quit();
    }

    public WebDriver getWebDriver() {
        //Create driver
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        //Maximize browser window
        driver.manage().window().maximize();

        //Open test page
        driver.get(url());
        System.out.println("Page is opened");
        return driver;
    }

    public String url()
    {
        return "https://fierce-shelf-03672.herokuapp.com/";
    }

    private void sleep(long m)
    {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
