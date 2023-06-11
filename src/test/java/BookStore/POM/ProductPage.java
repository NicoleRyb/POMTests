package BookStore.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage{
    private By addToCart = By.cssSelector("[name=add-to-cart]");
    private By goToCart = By.cssSelector(".woocommerce-message>.button");
    private By miniCart = By.cssSelector("[class='wc-block-mini-cart__button']");
    private By totalPriceInMiniCart = By.cssSelector(".wc-block-components-totals-item__value");
    private By addToWishlist = By.cssSelector("a.add_to_wishlist");
    public final StoreHeaderComponent storeHeader;

    public ProductPage(WebDriver driver) {
        super(driver);
        storeHeader = new StoreHeaderComponent(driver);
    }

    public ProductPage go(String calculusSlug) {
        driver.get(baseURL + "/product" + calculusSlug);
        return this;
    }

    public ProductPage addToCart() {
        driver.findElement(addToCart).click();
        return this;
    }

    public CartPage goToCart() {
        driver.findElement(goToCart).click();
        return new CartPage(driver);
    }

    public void openMiniCart() {
        driver.findElement(miniCart).click();
    }

    public void waitToBePresent(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(totalPriceInMiniCart));
    }

    public WebElement findElement(){
        return driver.findElement(totalPriceInMiniCart);
    }

    public ProductPage addToWishlist() {
        driver.findElement(addToWishlist).click();
        waitForLoadingIconDisappear();
        return this;
    }

}
