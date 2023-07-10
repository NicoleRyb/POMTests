package BookStore;

import BookStore.POM.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainPageTests extends BaseTests{
    @Test
    public void sortByPriceTest(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        mainPage.sortByPrice();
        Assertions.assertEquals("6,00 €", mainPage.getPrice(),"Price is not what expected - sorting doesn't work.");
    }
}
