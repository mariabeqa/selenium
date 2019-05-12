package ru.srqa.training.selenium.app;

import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.srqa.training.selenium.model.UserData;
import ru.srqa.training.selenium.pages.CartPage;
import ru.srqa.training.selenium.pages.MainPage;
import ru.srqa.training.selenium.pages.ProductPage;
import util.PropertyLoader;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;


public class Application {
    private EventFiringWebDriver driver;
    private WebDriverWait wait;

    public BrowserMobProxy proxy;
    public Proxy proxyCharles;

    public MainPage mainPage;
    public ProductPage productPage;
    public CartPage cartPage;

    public static class myListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }
    }

    public Application() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");

//        proxyCharles = new Proxy();
//        proxyCharles.setHttpProxy("127.0.0.1:8888");

//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, Level.ALL);
//        proxy = new BrowserMobProxyServer();
//        proxy.start(0);
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

//        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//        chromeOptions.setCapability(CapabilityType.PROXY, proxyCharles);
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        wait = new WebDriverWait(driver, 10);
//        driver.register(new myListener());
//        driver = new SafariDriver();
//        driver = new FirefoxDriver();
        //without geckodriver
//        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
//        driver = new FirefoxDriver(options);
    }

    public void quit() {
        driver.quit();
    }

    public boolean isElementPresent (WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex) {
            throw ex;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void loginToAdminPanel(String user, String pw) {
        driver.get(PropertyLoader.loadProperty("login.adminpage.url"));
        driver.findElement(By.name("username")).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(pw);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.logotype a img")));
    }

    public void loginToUserProfile(String email, String pw) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(pw);
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector("div.notice.success i"));
    }

    public void signUp(UserData userData) {
        driver.findElement(By.xpath("//a[contains(text(),'New customers')]")).click();
        driver.findElement(By.name("firstname")).sendKeys(userData.getFirstName());
        driver.findElement(By.name("lastname")).sendKeys(userData.getLastName());
        driver.findElement(By.name("address1")).sendKeys(userData.getAddress());
        driver.findElement(By.name("postcode")).sendKeys(userData.getPostcode());
        driver.findElement(By.name("city")).sendKeys(userData.getCity());
        new Select(driver.findElement(By.name("country_code")))
                .selectByValue(userData.getCountry());
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name='zone_code']")));
        new Select(driver.findElement(By.cssSelector("select[name='zone_code']")))
                .selectByValue(userData.getState());
        driver.findElement(By.name("email")).sendKeys(userData.getEmail());
        driver.findElement(By.name("phone")).sendKeys(Keys.HOME + userData.getPhone());
        driver.findElement(By.name("password")).sendKeys(PropertyLoader.loadProperty("user.password"));
        driver.findElement(By.name("confirmed_password")).sendKeys(PropertyLoader.loadProperty("user.password"));

        driver.findElement(By.cssSelector("button[name='create_account']")).click();
    }

    public void logout() {
        driver.findElement(By.xpath("(//a[contains(text(),'Logout')])[1]")).click();
    }
}
