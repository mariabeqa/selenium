package ru.srqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.PropertyLoader;

import java.util.List;

public class BrowserLogsTest extends TestBase {
    @Test
    public void testBrowserLogs() {
        loginToAdminPanel(PropertyLoader.loadProperty("admin.username"), PropertyLoader.loadProperty("admin.password"));

        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Rubber Ducks')]")).click();

        checkBroserLogsWhenOpenProductPage();
    }

    private void checkBroserLogsWhenOpenProductPage() {List<WebElement> products = driver.findElements(By.xpath("//input[contains(@name,'products')]/../../td[3]/a"));

        for(int i = 1; i < (products.size() + 1); i++) {
            driver.findElement(By.xpath("(//input[contains(@name,'products')]/../../td[3]/a)[" + i + "]")).click();
            driver.manage().logs().get("browser").forEach(l-> System.out.println(l));
            driver.findElement(By.cssSelector("button[name='cancel']")).click();
        }
    }
}
