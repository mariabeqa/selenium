package ru.srqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.srqa.training.selenium.model.ProductData;
import util.PropertyLoader;

import java.io.*;

public class AddingProductTest extends TestBase {
    public static final String PATH_TO_PRODUCT_IMAGE = "./src/test/resources/golden_duck.jpg";
    public static final String PRODUCT_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.";

    @Test
    public void testAddingProduct() throws IOException {
        loginToAdminPanel(PropertyLoader.loadProperty("admin.username"), PropertyLoader.loadProperty("admin.password"));

        BufferedReader reader = new BufferedReader(new FileReader(new File("./src/test/resources/product_description.txt")));

        ProductData newProduct = addNewProduct(new ProductData().withName("Golden Duck").withCode("rd007").withCategory("Rubber Ducks")
                .withQuantity("30.00").withImage(new File(PATH_TO_PRODUCT_IMAGE))
                .withDateFrom("04/20/2019").withDateTo("05/20/2019").withManufacturer("ACME Corp.")
                .withShortDescription(PRODUCT_DESCRIPTION).withDescription(reader.readLine())
                .withPurchasePrice("15.00").withPrice("25.00"));

        Assert.assertTrue(driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", newProduct.getName()))).isDisplayed());
    }

    private ProductData addNewProduct(ProductData productData) throws IOException {
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();

        //General
        driver.findElement(By.xpath("//label[contains(text(),'Enabled')]")).click();
        driver.findElement(By.name("name[en]")).sendKeys(productData.getName());
        driver.findElement(By.name("code")).sendKeys(productData.getCode());
        driver.findElement(By.cssSelector("input[data-name='Root']")).click();
        driver.findElement(By.cssSelector(String.format("input[data-name='%s']", productData.getCategory()))).click();
        driver.findElement(By.name("quantity")).sendKeys(productData.getQuantity());
        driver.findElement(By.name("new_images[]")).sendKeys(productData.getPhoto());
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript(String.format("document.querySelector(\"input[name='date_valid_from']\").value=\"%s\"", productData.getDateFrom()));
        je.executeScript(String.format("document.querySelector(\"input[name='date_valid_to']\").value=\"%s\"", productData.getDateTo()));

        //Information
        driver.findElement(By.xpath("//a[contains(text(),'Information')]")).click();
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name='manufacturer_id']")));
        new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")))
                .selectByVisibleText(productData.getManufacturer());
        driver.findElement(By.name("short_description[en]")).sendKeys(productData.getShortDescription());
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys(productData.getDescription());
        WebElement descriptionTextArea = driver.findElement(By.cssSelector("div.trumbowyg-editor"));
        wait.until(ExpectedConditions.attributeContains(descriptionTextArea, "innerText", productData.getDescription()));

        //Prices
        driver.findElement(By.xpath("//a[contains(text(),'Prices')]")).click();
        wait.until((WebDriver d) -> d.findElement(By.name("purchase_price")));
        driver.findElement(By.name("purchase_price")).sendKeys(productData.getPurchasePrice());
        driver.findElement(By.name("prices[USD]")).sendKeys(productData.getPrice());

        driver.findElement(By.name("save")).click();
        wait.until((WebDriver d) -> d.findElement(By.xpath("//h1[contains(text(),'Catalog')]")));
        return productData;
    }
}
