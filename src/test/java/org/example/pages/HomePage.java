package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.example.pages.BaseTest.driver;

public class HomePage extends BaseTest{
    By aboutUs = By.linkText("About Us");
    By jobsApply = By.linkText("Jobs in UAE");
    By easyApply = By.linkText("Easy apply");
    By firstName = By.id("JsApplicantRegisterForm_firstName");
    By lastName = By.id("JsApplicantRegisterForm_lastName");
    By email = By.id("JsApplicantRegisterForm_email");
    By password = By.id("JsApplicantRegisterForm_password");
    By phone = By.id("JsApplicantRegisterForm_mobPhone");
    By registerBtn = By.id("register");
    public void wait(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    @Test(priority = 1)
    public void clickAboutUsBtn() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,4500)");
        Thread.sleep(5000);
        driver.findElement(aboutUs).click();
    }

    @Test(priority = 2)
    public void applyJob() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,2500)");
        Thread.sleep(5000);
        driver.findElement(jobsApply).click();
    }

    @Test(priority = 3)
    public void clickEasyApply() {
        int i;
        List<WebElement> easyapplyBtn = driver.findElements(easyApply);
        for (i = 0; i < easyapplyBtn.size(); i++) {
            easyapplyBtn.get(i).click();
            if (i == 1) {
                break;
            }
            break;
        }
    }

    @Test(priority = 4)
    public void filljobForm() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,2000)");

        driver.findElement(firstName).sendKeys("Sarah ");
        driver.findElement(lastName).sendKeys("Mohamed");
        driver.findElement(email).sendKeys("saraabdelftahsos@gmail.com");
        driver.findElement(password).sendKeys("Sarah@123");
        driver.findElement(phone).sendKeys("1012194330");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(registerBtn).click();
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("window.scrollBy(0,500)");
        wait(driver.findElement(By.id("JsApplicantRegisterForm_email_em_")));
        String errorMsgAlreadyRegistered = driver.findElement(By.id("JsMiniRegistrationForm_email_em_")).getText();
        Assert.assertEquals("This email is already registered. ", errorMsgAlreadyRegistered);

    }
}
