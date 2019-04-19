package ru.srqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void start() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        driver = new ChromeDriver(chromeOptions);

//        driver = new SafariDriver();

//        driver = new FirefoxDriver();

        //without geckodriver
//        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
//        driver = new FirefoxDriver(options);

        wait = new WebDriverWait(driver, 10);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
