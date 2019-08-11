package com.suite2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DealNews {
    static {
        System.setProperty("webdriver.gecko.driver",
//                "C:\\Users\\mihovm.MASCORP\\Downloads\\selenium-java-3.141.59\\geckodriver.exe");
                "/Users/balivo/Downloads/selenium/course/geckodriver");

    }

    WebDriver driver = new FirefoxDriver();
    String username = "";
    String password = "";

    // This method is to navigate DealNews URL
    @BeforeClass
    public void init() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.navigate().to("https://www.dealnews.com");
    }

    // To log in DealNews
    @Test (enabled = true, priority = 0)
    public void login() {
        driver.navigate().to("https://www.dealnews.com/mydealnews/signin/");
        driver.findElement(By.xpath("//input[@class='form-field login-username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Sign In']")).submit();
    }

    // Search For product
    @Test (enabled = true, priority = 1)
    public void searchAndSelectProduct() throws InterruptedException {
        Thread.sleep(10000);
//        new WebDriverWait(driver, 30).until(
//                webDriver -> ((JavascriptExecutor) webDriver)
//                        .executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.xpath("//div[@id='search-icon']")).click();
        driver.findElement(By.xpath("//input[@id='search-text']"))
                .sendKeys("iphone");

        // select the first item in the search results
        driver.findElement(By.xpath("//a[@class='search-link first-row']"))
                .click();
    }

    @Test (enabled = true, priority = 2)
    public void buyAndRemoveFromCart() {
        driver.findElement(By.linkText("Home")).click();
    }

    @Test (enabled = true, priority = 3)
    public void logout() {
        driver.findElement(By.linkText("balivo@yahoo.com")).click();
        driver.findElement(By.xpath("//a[@href='/mydealnews/signout/']")).click();
    }

    @AfterClass
    public void quit() {
        driver.close();
    }
}
