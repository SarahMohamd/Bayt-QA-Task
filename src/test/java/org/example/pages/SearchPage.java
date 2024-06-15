package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.example.pages.BaseTest.driver;

public class SearchPage {
    public static WebDriver driver;
    By searchTextBox= By.id("text_search");
    By  selectCountry = By.id("search_country__r");
    By searchCountry = By.xpath("//div[@class='list-menu-title ']/div[@class]/input");
    By selectFromDropDownMenu = By.xpath("//li[@class='is-active']/a");
    By searchBtn = By.xpath("//button[@type='submit']");
    By searchForJobs = By.xpath("//div[@class='list-menu-title m']/div/input");
    By selectJob = By.xpath("//li[@data-text='quality assurance engineer']/a/span/span");
    By acceptCookies = By.xpath("//div[@class='cky-notice-group']/div/button[@aria-label='Accept all cookies']");
    By easyApply = By.linkText("Easy apply");
    By firstName = By.id("JsApplicantRegisterForm_firstName");


    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--width=390");
        firefoxOptions.addArguments("--height=844");
        firefoxOptions.addArguments("--disable-notifications");
        //   firefoxOptions.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 10; Pixel 3) AppleWebKit/537.36 (KHTML, like Gecko) Firefox/83.0");
        driver = new FirefoxDriver(firefoxOptions);
        driver.navigate().to("https://www.bayt.com/en/egypt/");
    }
    @Test
    public void search() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(acceptCookies).click();
        driver.findElement(searchTextBox).click();
        driver.findElement(searchForJobs).sendKeys("Quality Assurance Engineer");
        Thread.sleep(4000);
        driver.findElement(selectJob).click();
        driver.findElement(selectCountry).click();
        driver.findElement(searchCountry).sendKeys("United Arab Emirates");
        Thread.sleep(4000);
        driver.findElement(selectFromDropDownMenu).click();
        driver.findElement(searchBtn).click();
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
        Assert.assertTrue(driver.findElement(firstName).isDisplayed());
    }


}