package BookStore.POM;

import org.example.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
    public final StoreHeaderComponent storeHeader;
    public MainPage(WebDriver driver) {
        super(driver);
        storeHeader = new StoreHeaderComponent(driver);
    }

    public MainPage go() {
        driver.get(baseURL);
        return this;
    }

}
