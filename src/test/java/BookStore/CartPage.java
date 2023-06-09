package BookStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    By productItem = By.cssSelector("tr.cart_item");
    By quantityField = By.cssSelector("input.qty");
    By updateCart = By.cssSelector("[name='update_cart']");
    By totalPrice = By.cssSelector("[data-title='Total']");


    private final WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    public void go() {
        String baseURL = "http://localhost:8080";
        driver.get(baseURL + "/cart/");
    }
    public int getNumberOfProducts() {
        return driver.findElements(productItem).size();
    }
    public void type(String text) {
        WebElement field = driver.findElement(quantityField);
        field.clear();
        field.sendKeys(text);
    }
    public void updateCart() {
        driver.findElement(updateCart).click();
    }

    public void waitToDisappear(String cssSelector, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssSelector), 0));
    }

    public String getText() {
        return driver.findElement(totalPrice).getText();
    }

    public WebElement findElement(){
        return driver.findElement(updateCart);
    }
}
