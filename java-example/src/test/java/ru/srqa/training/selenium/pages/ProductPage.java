package ru.srqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[name='add_cart_product']")
    public WebElement addToCartButton;

    public void addProductsToCart(int amount) {
        for (int i = 1; i < amount + 1; i++) {
            driver.findElement(By.xpath("(//li[contains(@class,'product')])[" + i + "]")).click();
            addToCartButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(i)));
            driver.navigate().back();
        }
    }
}
