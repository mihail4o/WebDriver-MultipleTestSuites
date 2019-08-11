package com.suite1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitToLoadDemo {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver",
                "C:\\Users\\mihovm.MASCORP\\Downloads\\selenium-java-3.141.59\\geckodriver.exe");
//                "/Users/balivo/Downloads/selenium/course/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.dealnews.com/");

        // Wait until page load!!!
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
        driver.findElement(By.linkText("Sign In")).click();

        // Short wait to load page version!!!
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

        driver.findElement(By.xpath("//div[text()='Sign In']")).click();
    }
}
