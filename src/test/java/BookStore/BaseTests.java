package BookStore;

import BookStore.helpers.Browser;
import BookStore.helpers.BrowserFactory;
import BookStore.helpers.ConfigurationReader;
import BookStore.helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import javax.swing.text.Position;

public class BaseTests {
    protected Browser browser;
    private static ConfigurationReader configuration;
    @BeforeAll
    public static void  loadConfiguration(){
        configuration = new ConfigurationReader();
    }

    @BeforeEach
    public void setup() {
        BrowserFactory browserFactory = new BrowserFactory();
        try {
            browser = browserFactory.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
        browser.driver.manage().window().maximize();
    }

    @AfterEach
    public void quitDriver(){
        browser.driver.quit();
    }
}
