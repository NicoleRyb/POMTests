package BookStore;

import BookStore.POM.CartPage;
import BookStore.POM.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;

@DisplayName("Cart Tests")
public class CartTests extends BaseTests{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomySlug = "/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/";

    @Test
    @DisplayName("No product added to cart so the card should be empty")
    public void noProductAddedToCartShouldCartBeEmpty(){
        CartPage cartPage = new CartPage(browser).go();
        Assertions.assertEquals(0,
                cartPage.getNumberOfProducts(),
                "Product table was find in the cart while no product was added.");
    }

    @Test
    @DisplayName("One product added to cart so the card should have one product")
    public void productAddedToCartShouldCartHaveOneProduct(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage.go(calculusSlug).addToCart().goToCart();
        int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(1,
                numberOfProducts,
                "Expected number of products: 1" + "\nActual: " + numberOfProducts);
    }
    @Test
    @DisplayName("Two products added to cart so the card should have two products")
    public void twoProductsAddedToCartShouldCartHaveTwoProducts(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(calculusSlug).addToCart()
                .go(historyOfAstronomySlug).addToCart().goToCart();

        int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(2,
                numberOfProducts,
                "Expected number of products: 2" + "\nActual: " + numberOfProducts);
    }
    @Test
    @DisplayName("Changing quantity in cart should change total price")
    public void changingQuantityInCartShouldChangeTotalPrice() {
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(calculusSlug).addToCart().goToCart();
        cartPage.changeNumber("3");
        cartPage.updateCart();

        cartPage.waitToDisappear();

        Assertions.assertEquals("39,00 €",
                cartPage.getText(),
                "Total price after quantity update is not what expected.");
    }
    @Test
    @DisplayName("Changing quantity in cart to negative should not change total price")
    public void changingQuantityInCartToNegativeShouldNotChangeTotalPrice(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(calculusSlug).addToCart().goToCart().changeQuantity(-3);

        Assertions.assertEquals("13,00 €",
                cartPage.getText(),
                "Total price is not what expected.");
    }
    @Test
    @DisplayName("Cart has not changed so update button should be disabled")
    public void cartNotChangedShouldUpdateButtonBeDisabled(){
        ProductPage productPage = new ProductPage(browser);
        productPage.go(calculusSlug).addToCart();
        CartPage cartPage = new CartPage(browser);
        cartPage.go();
        WebElement updateCart = cartPage.findElement();

        Assertions.assertFalse(updateCart.isEnabled(),
                "Update button is enabled while it shouldn't. There were no changes.");
    }
    @Test
    @DisplayName("Added product to cart so the product price should be shown in the mini cart")
    public void addedProductToCartShouldShowProductPriceInMiniCart() {
        ProductPage productPage = new ProductPage(browser);
        productPage.go(historyOfAstronomySlug).addToCart().openMiniCart();

        Assertions.assertEquals("12,00 €",
                productPage.priceInMiniCart(5).getText(),
                "The price displayed in minicart is not correct.");
    }
    @Test
    @DisplayName("Added product to cart so the product price should be shown in the header")
    public void addedProductToCartShouldShowProductPriceInHeader() {
        ProductPage productPage = new ProductPage(browser);
        productPage.go(historyOfAstronomySlug).addToCart();

        Assertions.assertEquals("12,00 €",
                productPage.priceInHeader().getText(),
                "The price displayed in the header is not correct.");
    }
    @Test
    @DisplayName("Deleted one cookie so the number of cookies should be one less than before")
    public void deleteCookieTest(){
        ProductPage productPage = new ProductPage(browser);
        productPage.go(calculusSlug).addToCart();
        Cookie itemsInCartCookie = browser.driver.manage().getCookieNamed("woocommerce_items_in_cart");
        int size = browser.driver.manage().getCookies().size();
        browser.driver.manage().deleteCookie(itemsInCartCookie);
        Assertions.assertEquals(size - 1, browser.driver.manage().getCookies().size());
    }
}
