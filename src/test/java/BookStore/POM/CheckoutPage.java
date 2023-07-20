package BookStore.POM;

import BookStore.helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage{
    By placeOrder = By.cssSelector("#place_order");
    By firstNameLocator = By.cssSelector("#billing_first_name");
    By lastNameLocator = By.cssSelector("#billing_last_name");
    By addressLocator = By.cssSelector("#billing_address_1");
    By postcodeLocator = By.cssSelector("#billing_postcode");
    By cityLocator = By.cssSelector("#billing_city");
    By phoneLocator = By.cssSelector("#billing_phone");
    By emailLocator = By.cssSelector("#billing_email");
    By stripeCard = By.cssSelector("#stripe-card-element iframe");
    By stripeExp = By.cssSelector("#stripe-exp-element iframe");
    By stripeCVC = By.cssSelector("#stripe-cvc-element iframe");
    By cardNumberLocator = By.cssSelector("[name='cardnumber']");
    By expDateLocator = By.cssSelector("[name='exp-date']");
    By cvcLocator = By.cssSelector("[name='cvc']");

    public CheckoutPage(Browser browser) {
        super(browser);
    }
    public CheckoutPage go() {
        driver.get(baseURL + "/checkout/");
        return this;
    }
    public CheckoutPage setFirstName(String firstName) {
        driver.findElement(firstNameLocator).sendKeys(firstName);
        return this;
    }
    public CheckoutPage setLastName(String lastName) {
        driver.findElement(lastNameLocator).sendKeys(lastName);
        return this;
    }
    public CheckoutPage setAddress(String address) {
        driver.findElement(addressLocator).sendKeys(address);
        return this;
    }
    public CheckoutPage setPostcode(String postcode) {
        driver.findElement(postcodeLocator).sendKeys(postcode);
        return this;
    }
    public CheckoutPage setCity(String city) {
        driver.findElement(cityLocator).sendKeys(city);
        return this;
    }
    public CheckoutPage setPhone(String phone) {
        driver.findElement(phoneLocator).sendKeys(phone);
        return this;
    }
    public CheckoutPage setEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
        return this;
    }
    public CheckoutPage setCardNumber(String cardNumber) {
        driver.findElement(cardNumberLocator).sendKeys(cardNumber);
        return this;
    }
    public CheckoutPage setExpDate(String expDate) {
        driver.findElement(expDateLocator).sendKeys(expDate);
        return this;
    }
    public CheckoutPage setCvc(String cvc) {
        driver.findElement(cvcLocator).sendKeys(cvc);
        return this;
    }
    public CheckoutPage switchToFrameStripe() {
        browser.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stripeCard));
        return this;
    }
    public CheckoutPage switchToFrameExp() {
        browser.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stripeExp));
        return this;
    }
    public CheckoutPage switchToFrameCvc() {
        browser.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stripeCVC));
        return this;
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
