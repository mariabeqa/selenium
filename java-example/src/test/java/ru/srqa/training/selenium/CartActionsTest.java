package ru.srqa.training.selenium;

import net.lightbody.bmp.core.har.Har;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.PropertyLoader;

public class CartActionsTest extends TestBase {
    public static final int AMOUNT_OF_PRODUCTS_TO_ADD = 3;
    public static final int AMOUNT_OF_PRODUCTS_TO_REMOVE = 3;

    @Test
    public void testAddingProductToCart() {
//        proxy.newHar();
        driver.get(PropertyLoader.loadProperty("mainpage.url"));

        addProductsToCart(AMOUNT_OF_PRODUCTS_TO_ADD);
        Assert.assertEquals(AMOUNT_OF_PRODUCTS_TO_ADD, getAmountOfProductsInCart());
        removeProductsFromCart(AMOUNT_OF_PRODUCTS_TO_REMOVE);
        Assert.assertEquals(AMOUNT_OF_PRODUCTS_TO_ADD - AMOUNT_OF_PRODUCTS_TO_REMOVE, getAmountOfProductsInCart());
//        Har har = proxy.endHar();
//        har.getLog().getEntries().forEach(l-> System.out.println(l.getResponse().getStatus() + ": " + l.getRequest().getUrl()));
    }

    public void addProductsToCart(int amount) {
        for (int i = 1; i < amount + 1; i++) {
            driver.findElement(By.xpath("(//li[contains(@class,'product')])[" + i + "]")).click();
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(i)));
            driver.navigate().back();
        }
    }

    private void removeProductsFromCart(int amount) {
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();

        for (int i = 1; i < amount + 1; i++) {
            driver.findElement(By.xpath("(//button[@name='remove_cart_item'])[1]")).click();

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tr td.item"), (amount - i)));
        }
        driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();
    }
    
    public int getAmountOfProductsInCart() {
        return  Integer.valueOf(driver.findElement(By.cssSelector("span.quantity")).getAttribute("innerText"));
    }
}
