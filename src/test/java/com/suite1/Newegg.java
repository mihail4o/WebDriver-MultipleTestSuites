package com.suite1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Newegg {
    static {
        System.setProperty("webdriver.gecko.driver",
//                "C:\\Users\\mihovm.MASCORP\\Downloads\\selenium-java-3.141.59\\geckodriver.exe");
                "/Users/balivo/Downloads/selenium/course/geckodriver");

    }

    WebDriver driver = new FirefoxDriver();
    String username = "";
    String password = "";

    // This method is to navigate Newegg URL
    @BeforeClass
    public void init() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.navigate().to("https://www.newegg.com");
    }

    // To log in Newegg
    @Test (enabled = true, priority = 1)
    public void login() {
        driver.findElement(By.linkText("Log in or Register")).click();
        driver.findElement(By.id("UserName")).sendKeys(username);
        driver.findElement(By.id("UserPwd")).sendKeys(password);
        driver.findElement(By.id("submitLogin")).click();
    }

    // Search For product
    @Test (enabled = true, priority = 2)
    public void searchAndSelectProduct() {
        driver.findElement(By.id("haQuickSearchBox")).sendKeys("pixel");
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-mini search-bar-btn']")).click();

        // select the first item in the search results
        driver.findElement(By.xpath("//div[contains(@class,'list-wrap')]//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//button[1]")).click();
    }

    @Test (enabled = true, priority = 3)
    public void buyAndRemoveFromCart() {
        driver.findElement(By.xpath("//div[contains(@class,'call-to-action call-to-action-main-product')]//button[@type='button']")).click();
        driver.findElement(By.xpath("/html/body/div[15]/div/div[3]/div/div/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[10]/form[1]/table[2]/tbody/tr/td[3]/div/button[2]")).click();
    }

    @Test (enabled = true, priority = 4)
    public void logout() throws InterruptedException {
        Actions s = new Actions(driver);
        s.moveToElement(driver.findElement(By.xpath("/html/body/header/div/nav/ul/li[1]/a")));
        s.build().perform();

        // Sign Out click!
        Thread.sleep(8000);
        driver.findElement(By.xpath("/html/body/header/div/nav/ul/li[1]/div/div/ul/li[4]/a")).click();
    }

    @AfterClass (enabled = true)
    public void quit() {
        driver.close();
    }
}
