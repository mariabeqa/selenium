package ru.srqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import util.PropertyLoader;

import java.util.List;
import java.util.Set;

public class OpenLinksTest extends TestBase {
    public static final By LINKS_IN_COUNTRIES_MENU_ITEM = By.cssSelector("i.fa-external-link");

    @Test
    public void testOpeningLinkInCountriesMenuItem() {
        loginToAdminPanel(PropertyLoader.loadProperty("admin.username"), PropertyLoader.loadProperty("admin.password"));
        driver.findElement(By.xpath("//li[@id='app-']//span[contains(text(),'Countries')]")).click();
        driver.findElement(By.cssSelector("a.button")).click();

        openLinksInNewWindow(LINKS_IN_COUNTRIES_MENU_ITEM);
    }

    private void openLinksInNewWindow(By locator) {
        String originalWindow = driver.getWindowHandle();

        List<WebElement> links = driver.findElements((locator));

        for (WebElement link : links ) {
            Set<String> existingWindows = driver.getWindowHandles();
            link.click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return (WebDriver d) -> {
            Set<String> handles = d.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }
}

