package BookStore.POM;

import BookStore.helpers.Browser;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;

public class WishlistPage extends BasePage{
    private By productItems = By.cssSelector(".wishlist-items-wrapper tr td.product-remove");
    protected WishlistPage(Browser browser) {
        super(browser);
    }
    public int getNumberOfProducts() {
        return driver.findElements(productItems).size();
    }
}
