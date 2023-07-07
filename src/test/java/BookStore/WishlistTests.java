package BookStore;

import BookStore.POM.MainPage;
import BookStore.POM.ProductPage;
import BookStore.POM.WishlistPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class  WishlistTests extends BaseTests{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    String deleteItem = ".delete_item";
    String addToWishlist = ".add_to_wishlist";

    @Test
    public void productAddedToWishlistShouldWishlistHaveOneItem(){
        ProductPage productPage = new ProductPage(browser).go(calculusSlug);
        WishlistPage wishlistPage = productPage.addToWishlist().storeHeader.goToWishlist();

        Assertions.assertEquals(1,
                wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    public void noProductAddedToWishlistShouldWishlistBeEmpty(){
        MainPage mainPage = new MainPage(browser);
        WishlistPage wishlistPage = mainPage.go().storeHeader.goToWishlist();

        Assertions.assertEquals(0,
                wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    public void removeProductFromWishlist(){
        ProductPage productPage = new ProductPage(browser).go(calculusSlug).addToWishlist();
        //WebDriverWait wait = new WebDriverWait(browser.driver, Duration.ofSeconds(5), Duration.ofSeconds(1));
        Wait<WebDriver> wait = new FluentWait<WebDriver>(browser.driver)
                .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofSeconds(1))
                                .ignoring(NoSuchElementException.class);
        productPage.waitToBePresent(deleteItem,5);
        productPage.removeFromList().waitToBePresent(addToWishlist, 5);
        Assertions.assertDoesNotThrow(() -> productPage.waitToBePresent(addToWishlist,5),"Product has not been removed from wishlist");
    }
}
