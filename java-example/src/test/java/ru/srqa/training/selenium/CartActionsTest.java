package ru.srqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;

public class CartActionsTest extends TestBase {
    public static final int AMOUNT_OF_PRODUCTS_TO_ADD = 3;
    public static final int AMOUNT_OF_PRODUCTS_TO_REMOVE = 3;

    @Test
    public void testAddingProductToCart() {
//        proxy.newHar();
        app.mainPage.open();
        app.productPage.addProductsToCart(AMOUNT_OF_PRODUCTS_TO_ADD);
        Assert.assertEquals(AMOUNT_OF_PRODUCTS_TO_ADD, app.cartPage.getAmountOfProductsInCart());
        app.cartPage.removeProductsFromCart(AMOUNT_OF_PRODUCTS_TO_REMOVE);
        Assert.assertEquals(AMOUNT_OF_PRODUCTS_TO_ADD - AMOUNT_OF_PRODUCTS_TO_REMOVE, app.cartPage.getAmountOfProductsInCart());
//        Har har = proxy.endHar();
//        har.getLog().getEntries().forEach(l-> System.out.println(l.getResponse().getStatus() + ": " + l.getRequest().getUrl()));
    }
}
