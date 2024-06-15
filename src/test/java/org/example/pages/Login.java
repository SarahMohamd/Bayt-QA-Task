package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.example.pages.BaseTest.driver;

public class Login extends BaseTest {
    HomePage home = new HomePage();

    By loginBtnMain = By.xpath("//a[@class='btn is-small is-inverse u-expanded-m ']");
    By loginEmail = By.id("LoginForm_username");
    By loginPassword = By.id("LoginForm_password");
    By loginBtn = By.id("login-button");

    By threeDots = By.xpath("//li[@class='popover-owner'][4]");

    @Test(priority = 1)
    public void login(){
        driver.findElement(loginBtnMain).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(loginEmail).sendKeys("saraabdelftahsos@gmail.com");
        driver.findElement(loginPassword).sendKeys("Sarah@123");
        driver.findElement(loginBtn).click();
        String afterLogin = driver.findElement(By.xpath("//section[@class='form-section m0t'][1]")).getText();
        Assert.assertTrue(afterLogin.contains("Tell us about yourself" ));

    }

    @Test (priority = 2)
    public void deleteAccount() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(threeDots).click();
        Thread.sleep(4000);
        driver.findElement(By.linkText("Account Settings")).click();
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[@data-cky-tag='accept-button']")).click();
        driver.findElement(By.linkText("Delete My Account")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.className("btn is-danger")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@class='btn u-expanded-m  is-danger non-aid']")).click();
        String webElement = driver.findElement(By.linkText("For Employers")).getText();
        Assert.assertTrue(webElement.equals("For Employers"));
    }

}