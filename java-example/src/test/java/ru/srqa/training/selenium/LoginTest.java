package ru.srqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.PropertyLoader;

public class LoginTest extends TestBase {

    @Test
    public void testLoginToAdminPanel() {
        driver.get(PropertyLoader.loadProperty("login.adminpage.url"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.logotype a img")));
    }
}
