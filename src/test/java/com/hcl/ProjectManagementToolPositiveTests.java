package com.hcl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectManagementToolPositiveTests
{
    @Test(priority = 1)
    public void createProjectFormOne()
    {
        System.out.println("Starting Project Management tool");

        WebDriver driver = getWebDriver();

        // sleep of 1 second
        sleep(1000);

        //Click create project
        WebElement clickCreateProject = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//a[@href='/addProject']"));
        clickCreateProject.click();

        //Verifications
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/addProject";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");

        quitDriver(driver);
    }

    @Test(priority = 2)
    public void submitProject()
    {
        System.out.println("Submitting the project");

        WebDriver driver = getWebDriver();

        sleep(1000);

        //Click create project
        WebElement clickCreateProject = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//a[@href='/addProject']"));
        clickCreateProject.click();

        //Create Project name
        WebElement projectName = driver.findElement(By.name("projectName"));
        projectName.sendKeys("Capstone");

        //Create Unique Project ID
        WebElement uniqueProjectID = driver.findElement(By.name("projectIdentifier"));
        uniqueProjectID.sendKeys("test1");

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

        //Sleep
        sleep(1000);

        //Verification
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/dashboard";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");

        //Quit Driver
        quitDriver(driver);
    }

    @Test(priority = 3)
    public void signUp()
    {
        System.out.println("Attempting to click sign up page");

        WebDriver driver = getWebDriver();

        sleep(1000);

        //Click sign up
        WebElement clickCreateProject = driver.findElement(By.linkText("Sign Up"));
        clickCreateProject.click();


        //Verification
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/register.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");


        //Close browser
        quitDriver(driver);

    }

    @Test(priority = 4)
    public void loginPage()
    {
        System.out.println("Attempting to view login page ");

        WebDriver driver = getWebDriver();

        sleep(1000);

        //Click Login page
        WebElement clickCreateProject = driver.findElement(By.linkText("Login"));
        clickCreateProject.click();



        //Verification
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/login.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");

        //Close browser
        quitDriver(driver);

    }

    @Test(priority = 5)
    public void clickUpdateProject()
    {
        //To check if the updating project can be updated
        System.out.println("Updating project");

        WebDriver driver = getWebDriver();

        //sleep
        sleep(1000);

        //Click Update project
        WebElement clickCreateProject = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']/div[@class='projects']/div[@class='container']//ul[@class='list-group']/a[@href='/updateProject/TEST1']//i[@class='fa fa-edit pr-1']"));
        clickCreateProject.click();

        //Verification- Make sure you use the same project to update project
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/updateProject/TEST1";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");

        //quit driver
        quitDriver(driver);

    }

    @Test(priority = 6)
    public void clickProjectBoard()
    {
        //Project Board does not work
        System.out.println("Attempting to click project board");

        //Web driver
        WebDriver driver = getWebDriver();

        //sleep
        sleep(2000);

        //Click Project board
        WebElement clickCreateProject = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']/div[@class='projects']/div[@class='container']//ul[@class='list-group']/a[@href='#']//i[@class='fa fa-flag-checkered pr-1']"));
        clickCreateProject.click();

        //Verification- Make sure you use the same project to update project
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/#";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");

        //driver quit
        quitDriver(driver);
    }

    /**
     * This method will update project name
     */
    @Test(priority = 7)
    public void updateProjectName()
    {
        System.out.println("Updating project");

        //Web driver
        WebDriver driver = getWebDriver();

        //sleep
        sleep(2000);

        //Click Capstone project to update project name
        WebElement clickCreateProject = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']/div[@class='projects']//ul[@class='list-group']/a[@href='/updateProject/TEST1']/li[@class='list-group-item update']"));
        clickCreateProject.click();

        //Update Project name
        WebElement projectName = driver.findElement(By.name("projectName"));
        projectName.clear();
        projectName.sendKeys("Testing !@34");

        //Submit Project
        WebElement submitButton = driver.findElement(By.xpath("/html//div[@id='root']/div[@class='App']//form/input[@type='submit']"));
        submitButton.click();

        //Verification
        String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/dashboard";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "Actual page url is not the same as expected");

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
