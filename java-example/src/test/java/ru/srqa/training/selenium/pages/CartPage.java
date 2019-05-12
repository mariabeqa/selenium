package ru.srqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    public CartPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span.quantity")
    public WebElement productsInCartCounter;

    @FindBy(xpath = "//a[contains(text(),'Checkout')]")
    public WebElement checkoutButton;

    @FindBy(xpath = "(//button[@name='remove_cart_item'])[1]")
    public WebElement removeProductButton;

    @FindBy(xpath = "//a[contains(text(),'Back')]")
    public WebElement backButton;

    public int getAmountOfProductsInCart() {
        return  Integer.valueOf(productsInCartCounter.getAttribute("innerText"));
    }

    public void removeProductsFromCart(int amount) {
        checkoutButton.click();

        for (int i = 1; i < amount + 1; i++) {
            removeProductButton.click();

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tr td.item"), (amount - i)));
        }
        backButton.click();
    }
}
