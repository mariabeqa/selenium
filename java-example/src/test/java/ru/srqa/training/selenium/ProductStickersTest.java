package ru.srqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.PropertyLoader;

import java.util.List;

public class ProductStickersTest extends TestBase {

    @Test
    public void testProductStickers() {
        driver.get(PropertyLoader.loadProperty("mainpage.url"));

        Assert.assertTrue(checkEachProductHaveOneSticker());
    }

    public boolean checkEachProductHaveOneSticker() {
        boolean stickerIsPresent = true;
        List<WebElement> productList = driver.findElements(By.cssSelector("li.product"));

        for (WebElement product : productList) {

            int amountOfStickersPerProduct = product.findElements(By.xpath(".//div[contains(@class,'sticker')]")).size();

            if (amountOfStickersPerProduct != 1) {
                stickerIsPresent = false;
                break;
            }
        }
        return stickerIsPresent;
    }
}
