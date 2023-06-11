package BookStore;

import BookStore.POM.MainPage;
import BookStore.POM.ProductPage;
import BookStore.POM.WishlistPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WishlistTests extends BaseTests{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    @Test
    public void productAddedToWishlistShouldWishlistHaveOneItem(){
        ProductPage productPage = new ProductPage(driver).go(calculusSlug);
        WishlistPage wishlistPage = productPage.addToWishlist().storeHeader.goToWishlist();

        Assertions.assertEquals(1,
                wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    public void noProductAddedToWishlistShouldWishlistBeEmpty(){
        MainPage mainPage = new MainPage(driver);
        WishlistPage wishlistPage = mainPage.go().storeHeader.goToWishlist();

    }
}
