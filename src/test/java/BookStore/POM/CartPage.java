package BookStore.POM;

import BookStore.helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage{
    private By productItem = By.cssSelector("tr.cart_item");
    private By quantityField = By.cssSelector("input.qty");
    private By updateCart = By.cssSelector("[name='update_cart']");
    private By totalPrice = By.cssSelector("[data-title='Total']");
    public CartPage(Browser browser){
        super(browser);
    }
    public CartPage go() {
        driver.get(baseURL + "/cart/");
        return this;
    }
    public int getNumberOfProducts() {
        return driver.findElements(productItem).size();
    }
    public void changeNumber(String text) {
        WebElement field = driver.findElement(quantityField);
        field.clear();
        field.sendKeys(text);
    }
    public void updateCart() {
        driver.findElement(updateCart).click();
    }

    public void waitToDisappear() {
        waitForLoadingIconDisappear();
    }

    public String getText() {
        return driver.findElement(totalPrice).getText();
    }

    public WebElement findElement(){
        return driver.findElement(updateCart);
    }

    public CartPage changeQuantity(int quantity) {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(String.valueOf(quantity));
        driver.findElement(updateCart).click();

        waitForLoadingIconDisappear();
        return this;
    }
}
