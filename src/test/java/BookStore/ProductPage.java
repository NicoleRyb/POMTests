package BookStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    By addToCartLocator= By.cssSelector("[name=add-to-cart]");
    By goToCartLocator = By.cssSelector(".woocommerce-message>.button");
    By miniCartLocator= By.cssSelector("[class='wc-block-mini-cart__button']");
    By totalPriceInMiniCartLocator = By.cssSelector(".wc-block-components-totals-item__value");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage go(String calculusSlug) {
        String baseURL = "http://localhost:8080";
        driver.get(baseURL + "/product" + calculusSlug);
        return this;
    }

    public ProductPage addToCart() {
        driver.findElement(addToCartLocator).click();
        return this;
    }

    public CartPage goToCart() {
        driver.findElement(goToCartLocator).click();
        return new CartPage(driver);
    }

    public void openMiniCart() {
        driver.findElement(miniCartLocator).click();
    }

    public void waitToBePresent(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(totalPriceInMiniCartLocator));
    }
    public WebElement findElement(){
        return driver.findElement(totalPriceInMiniCartLocator);
    }
}
