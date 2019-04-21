package ru.srqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.PropertyLoader;
import java.util.HashMap;

public class ProductPageTest extends  TestBase {

    @Test
    public void testProductInfo() {
        driver.get(PropertyLoader.loadProperty("mainpage.url"));
        HashMap<String, String> productInfoOnMainPage = saveProductInfoOnMainPage();
        driver.findElement(By.cssSelector("div#box-campaigns li.product a.link")).click();
        HashMap<String, String> productInfoOnProductPage = saveProductInfoOnProductPage();

        Assert.assertEquals(productInfoOnMainPage.get("name"), productInfoOnProductPage.get("name"));
        Assert.assertEquals(productInfoOnMainPage.get("price"), productInfoOnProductPage.get("price"));
        Assert.assertEquals(productInfoOnMainPage.get("regularPriceStrikethrough"),
                productInfoOnProductPage.get("regularPriceStrikethrough"));

        String[] rgbRegularPriceMainPage = getRGBasArray(productInfoOnMainPage.get("regularPriceColor"));
        Assert.assertEquals(rgbRegularPriceMainPage[0], rgbRegularPriceMainPage[1]);
        Assert.assertEquals(rgbRegularPriceMainPage[1], rgbRegularPriceMainPage[2]);

        String[] rgbCampaignPriceMainPage = getRGBasArray(productInfoOnMainPage.get("campaignPriceColor"));
        Assert.assertTrue(rgbCampaignPriceMainPage[1].equals("0"));
        Assert.assertTrue(rgbCampaignPriceMainPage[2].equals("0"));

        String[] rgbRegularPriceProductPage = getRGBasArray(productInfoOnProductPage.get("regularPriceColor"));
        Assert.assertEquals(rgbRegularPriceProductPage[0], rgbRegularPriceProductPage[1]);
        Assert.assertEquals(rgbRegularPriceProductPage[1], rgbRegularPriceProductPage[2]);

        String[] rgbCampaignPriceProductPage = getRGBasArray(productInfoOnProductPage.get("campaignPriceColor"));
        Assert.assertTrue(rgbCampaignPriceProductPage[1].equals("0"));
        Assert.assertTrue(rgbCampaignPriceProductPage[2].equals("0"));

        double campaignPriceFontSizeMainPage = Double.parseDouble(productInfoOnMainPage.get("campaignPriceFontSize").replaceAll("px",""));
        double regularPriceFontSizeMainPage = Double.parseDouble(productInfoOnMainPage.get("regularPriceFontSize").replaceAll("px", ""));

        Assert.assertTrue(Double.compare(campaignPriceFontSizeMainPage, regularPriceFontSizeMainPage) == 1);

        double campaignPriceFontSizeProductPage = Double.parseDouble(productInfoOnProductPage.get("campaignPriceFontSize").replaceAll("px",""));
        double regularPriceFontSizeProductPage = Double.parseDouble(productInfoOnProductPage.get("regularPriceFontSize").replaceAll("px", ""));

        Assert.assertTrue(Double.compare(campaignPriceFontSizeProductPage, regularPriceFontSizeProductPage) == 1);
    }

    private String[] getRGBasArray(String rgbaValues) {
        String rgba = rgbaValues.substring(5);
        String rgb = rgba.substring(0, rgba.length() - 1);
        System.out.println(rgb);
        String[] array = rgb.split(", ");
        return array;
    }

    private HashMap<String,String> saveProductInfoOnMainPage() {
        HashMap<String, String> productInfo = new HashMap<>();
        WebElement product = driver.findElement(By.cssSelector("div#box-campaigns li.product"));
        productInfo.put("name", product.findElement(By.cssSelector("div.name")).getText());
        productInfo.put("regularPrice", product.findElement(By.cssSelector("s.regular-price")).getText());
        productInfo.put("campaignPrice", product.findElement(By.cssSelector("strong.campaign-price")).getText());
        productInfo.put("regularPriceColor", product.findElement(By.cssSelector("s.regular-price"))
            .getCssValue("color"));
        productInfo.put("campaignPriceColor", product.findElement(By.cssSelector("strong.campaign-price"))
            .getCssValue("color"));
        productInfo.put("regularPriceStrikethrough", product.findElement(By.cssSelector("s.regular-price"))
            .getCssValue("text-decoration-line"));
        productInfo.put("regularPriceFontSize",product.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("font-size"));
        productInfo.put("campaignPriceFontSize", product.findElement(By.cssSelector("strong.campaign-price"))
                .getCssValue("font-size"));
        return productInfo;
    }

    private HashMap<String,String> saveProductInfoOnProductPage() {
        HashMap<String, String> productInfo = new HashMap<>();
        productInfo.put("name", driver.findElement(By.cssSelector("h1.title")).getText());
        productInfo.put("regularPrice", driver.findElement(By.cssSelector("s.regular-price")).getText());
        productInfo.put("campaignPrice", driver.findElement(By.cssSelector("strong.campaign-price")).getText());
        productInfo.put("regularPriceColor", driver.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("color"));
        productInfo.put("campaignPriceColor", driver.findElement(By.cssSelector("strong.campaign-price"))
                .getCssValue("color"));
        productInfo.put("regularPriceStrikethrough", driver.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("text-decoration-line"));
        productInfo.put("regularPriceFontSize",driver.findElement(By.cssSelector("s.regular-price"))
                .getCssValue("font-size"));
        productInfo.put("campaignPriceFontSize", driver.findElement(By.cssSelector("strong.campaign-price"))
                .getCssValue("font-size"));
        return productInfo;
    }
}
