package BookStore;

import BookStore.POM.MainPage;
import BookStore.POM.ProductPage;
import BookStore.POM.WishlistPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WishlistTests extends BaseTests{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
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
}
