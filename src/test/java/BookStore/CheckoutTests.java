package BookStore;

import BookStore.POM.CheckoutPage;
import BookStore.POM.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTests extends BaseTests {
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";

    @Test
    public void validCheckoutDataShouldOrderBePlaced() throws InterruptedException {

        ProductPage productPage = new ProductPage(browser);
        productPage.go(calculusSlug).addToCart();
        CheckoutPage checkoutPage = new CheckoutPage(browser).go();

        checkoutPage
                .setFirstName("Test")
                .setLastName("Testowy")
                .setAddress("ul. Testowa 1/2")
                .setPostcode("22-333")
                .setCity("Testowo")
                .setPhone("777888999")
                .setEmail("test@test.pl");

        checkoutPage
                .switchToFrameStripe()
                .setCardNumber("4242424242424242")
                .switchToDefault();

        checkoutPage.switchToFrameExp()
                .setExpDate("229")
                .switchToDefault();

        checkoutPage.switchToFrameCvc()
                .setCvc("123")
                .switchToDefault();


        checkoutPage.waitForLoadingIconDisappear();
        checkoutPage.placeOrder();
        checkoutPage.waitForLoadingIconDisappear();

        Assertions.assertEquals("Order received", checkoutPage.getTitle().getText(), "\"Order received\" text is not found in the header. Order was probably not successful.");
    }
}
