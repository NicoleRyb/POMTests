package BookStore.POM;

import BookStore.helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MainPage extends BasePage{
    public final StoreHeaderComponent storeHeader;
    private By orderBy = By.cssSelector(".orderby");
    private By actualPrice = By.cssSelector("ins>span:nth-child(1)[class='woocommerce-Price-amount amount']");
    public MainPage(Browser browser) {
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
    }
    public MainPage go() {
        driver.get(baseURL);
        return this;
    }

    public void sortByPrice() {
        WebElement selectElement = driver.findElement(orderBy);
        Select select = new Select(selectElement);
        select.selectByValue("price");
    }

    public String getPrice() {
        return driver.findElement(actualPrice).getText();
    }
}
