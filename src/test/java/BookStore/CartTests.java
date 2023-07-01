package BookStore;

import BookStore.POM.CartPage;
import BookStore.POM.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class CartTests extends BaseTests{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomySlug = "/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/";

    @Test
    public void noProductAddedToCartShouldCartBeEmpty(){
        CartPage cartPage = new CartPage(browser).go();
        Assertions.assertEquals(0,
                cartPage.getNumberOfProducts(),
                "Product table was find in the cart while no product was added.");
    }
    @Test
    public void productAddedToCartShouldCartHaveOneProducts(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage.go(calculusSlug).addToCart().goToCart();
        int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(1,
                numberOfProducts,
                "Expected number of products: 1" + "\nActual: " + numberOfProducts);
    }
    @Test
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
    public void changingQuantityInCartToNegativeShouldNotChangeTotalPrice(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(calculusSlug).addToCart().goToCart().changeQuantity(-3);

        Assertions.assertEquals("13,00 €",
                cartPage.getText(),
                "Total price is not what expected.");
    }
    @Test
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
    public void addProductToCartShouldShowProductPriceInMiniCart() {
        ProductPage productPage = new ProductPage(browser);
        productPage.go(historyOfAstronomySlug).addToCart().openMiniCart();
        productPage.waitToBePresent(5);

        Assertions.assertEquals("12,00 €",
                productPage.priceInMiniCart().getText(),
                "The price displayed in minicart is not correct.");
    }
    @Test
    public void addProductToCartShouldShowProductPriceInHeader() {
        ProductPage productPage = new ProductPage(browser);
        productPage.go(historyOfAstronomySlug).addToCart();

        Assertions.assertEquals("12,00 €",
                productPage.priceInHeader().getText(),
                "The price displayed in the header is not correct.");
    }
}
