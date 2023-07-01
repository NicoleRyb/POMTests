package BookStore;

import BookStore.POM.CheckoutPage;
import BookStore.POM.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CheckoutTests extends BaseTests {
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";

    @Test
    public void test() throws InterruptedException {
        By firstName = By.cssSelector("#billing_first_name");
        By lastName = By.cssSelector("#billing_last_name");
        By address = By.cssSelector("#billing_address_1");
        By postcode = By.cssSelector("#billing_postcode");
        By city = By.cssSelector("#billing_city");
        By phone = By.cssSelector("#billing_phone");
        By email = By.cssSelector("#billing_email");
        By cartNumber = By.cssSelector("[name='cardnumber']");
        By expDate = By.cssSelector("[name='exp-date']");
        By cvc = By.cssSelector("[name='cvc']");

        By stripeCard = By.cssSelector("#stripe-card-element iframe");
        By stripeExp = By.cssSelector("#stripe-exp-element iframe");
        By stripeCVC = By.cssSelector("#stripe-cvc-element iframe");

        ProductPage productPage = new ProductPage(browser);
        productPage.go(calculusSlug).addToCart();
        CheckoutPage checkoutPage = new CheckoutPage(browser).go();

        checkoutPage.injectData(firstName, "Test");
        checkoutPage.injectData(lastName, "Testowy");
        checkoutPage.injectData(address, "ul. Testowa 1/2");
        checkoutPage.injectData(postcode, "22-333");
        checkoutPage.injectData(city, "Testowo");
        checkoutPage.injectData(phone, "777888999");
        checkoutPage.injectData(email, "test@test.pl");

        checkoutPage.switchToFrame(stripeCard);
        checkoutPage.injectData(cartNumber,"4242424242424242");
        checkoutPage.switchToDefault();

        checkoutPage.switchToFrame(stripeExp);
        checkoutPage.injectData(expDate,"229");
        checkoutPage.switchToDefault();

        checkoutPage.switchToFrame(stripeCVC);
        checkoutPage.injectData(cvc,"123");
        checkoutPage.switchToDefault();

        checkoutPage.waitForLoadingIconDisappear();
        checkoutPage.placeOrder();
        checkoutPage.waitForLoadingIconDisappear();

        Assertions.assertEquals("Order received", checkoutPage.checkTitle().getText(), "\"Order received\" text is not found in the header. Order was probably not successful.");
    }
}
