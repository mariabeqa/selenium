package ru.srqa.training.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.PropertyLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTest extends TestBase {

    @Test
    public void testCountriesSorting() {
        loginToAdminPanel(PropertyLoader.loadProperty("admin.username"), PropertyLoader.loadProperty("admin.password"));
        driver.findElement(By.xpath("//li[@id='app-']//span[contains(text(),'Countries')]")).click();

        Assert.assertTrue(checkCountriesAreSorted());
    }

    @Test
    public void testGeoZonesSorting() {
        loginToAdminPanel(PropertyLoader.loadProperty("admin.username"), PropertyLoader.loadProperty("admin.password"));

        driver.findElement(By.xpath("//li[@id='app-']//span[contains(text(),'Geo Zones')]")).click();
        Assert.assertTrue(checkGeozonesAreSorted());
    }

    private boolean checkCountriesAreSorted() {
        List<WebElement> countries = driver.findElements(By.cssSelector("tr.row td:nth-child(5) a"));

        List<String> countriesNames = new ArrayList<>();
        for (WebElement element : countries) {
            String name = element.getText();
            countriesNames.add(name);
        }

        return countriesNames.stream().sorted().collect(Collectors.toList()).equals(countriesNames);
    }

    private boolean checkGeozonesAreSorted() {
        boolean zonesAreSorted = true;

        List<WebElement> countries = driver.findElements(By.cssSelector("tr.row td:nth-child(3) a"));

        for (int i=1; i < (countries.size() + 1); i++) {
            driver.findElement(By.xpath("(//tr[@class='row']/td[3]/a)[" + i + "]")).click();

            List<WebElement> zones = driver.findElements(By.cssSelector("table#table-zones td:nth-child(3) option[selected]"));

            List<String> zoneNames = new ArrayList<>();
            for (WebElement zone : zones) {
                String name = zone.getText();
                zoneNames.add(name);
                if (!zoneNames.stream().sorted().collect(Collectors.toList()).equals(zoneNames)) {
                    zonesAreSorted = false;
                    break;
                }
            }
            driver.navigate().back();
        }
        return zonesAreSorted;
    }
}
