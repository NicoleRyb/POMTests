package BookStore.POM;

import BookStore.helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage{
    By placeOrder = By.cssSelector("#place_order");

    public CheckoutPage(Browser browser) {
        super(browser);
    }
    public CheckoutPage go() {
        driver.get(baseURL + "/checkout/");
        return this;
    }
    public void injectData(By selector, String text) {
        WebElement field = driver.findElement(selector);
        field.sendKeys(text);
    }
    public void switchToFrame(By stripeSelector) {
        browser.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stripeSelector));
    }
    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    public void placeOrder() {
        driver.findElement(placeOrder).click();
    }
    public WebElement checkTitle() {
        return driver.findElement(By.cssSelector(".entry-title"));
    }
}
