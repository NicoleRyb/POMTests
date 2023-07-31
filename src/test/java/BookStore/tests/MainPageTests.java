package BookStore.tests;

import BookStore.POM.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Main Page Tests")
public class MainPageTests extends BaseTests{

    @Test
    @DisplayName("Sorting by price ascending so first one should have the lowest price")
    public void sortByPriceShouldFirstOneBeTheLowest(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        mainPage.sortByPrice();
        Assertions.assertEquals("6,00 €", mainPage.getPrice(),"Price is not what expected - sorting doesn't work.");
    }
}
