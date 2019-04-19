package ru.srqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.PropertyLoader;

import java.util.List;

public class VisitEachMenuItemTest extends TestBase {

    @Test
    public void testEachMenuItem() {
        driver.get(PropertyLoader.loadProperty("login.adminpage.url"));
        driver.findElement(By.name("username")).sendKeys(PropertyLoader.loadProperty("admin.username"));
        driver.findElement(By.name("password")).sendKeys(PropertyLoader.loadProperty("admin.password"));
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.logotype a img")));

        Assert.assertTrue(checkAllMenuItemsHaveHeaderWhenOpened());
    }

    public boolean checkAllMenuItemsHaveHeaderWhenOpened() {
        boolean headerIsPresent = true;

        List<WebElement> menuItems = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-"));

        for (int i=1; i < menuItems.size() + 1; i++) {
            WebElement selectedMenuItem = driver.findElement(By.xpath("(//ul[@id='box-apps-menu']/li[@id='app-'])[" + i + "]"));
            selectedMenuItem.click();

            List<WebElement> innerMenuItems = driver.findElements(By.cssSelector("li#app-.selected ul li"));

            for (int j=1; j < innerMenuItems.size() + 1; j++) {
                driver.findElement(By.xpath("((//ul[@id='box-apps-menu']/li[@id='app-'])[" + i + "]/ul/li)[" + j + "]")).click();

                if(!areElementsPresent(driver, By.cssSelector("td#content h1"))) {
                    headerIsPresent = false;
                    break;
                }
            }
        }
        return headerIsPresent;
    }
}
