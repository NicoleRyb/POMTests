package BookStore;

import BookStore.helpers.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import javax.swing.text.Position;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTests {
    protected Browser browser;
    private static ConfigurationReader configuration;

    @RegisterExtension
    TestStatus status = new TestStatus();

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

//    @AfterEach
//    public void quitDriver(){
//        browser.driver.quit();
//    }
    @AfterEach
    public void afterEach(TestInfo info){
        if(status.isFailed){
            takeScreenshot(info.getDisplayName());
        }
        browser.driver.quit();
    }

    private void takeScreenshot(String testName){
        File screenshot = ((TakesScreenshot)browser.driver).getScreenshotAs(OutputType.FILE);
        String folderLocation = "./screenshots/";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH-mm-ss");
        String formattedNow = LocalDateTime.now().format(formatter);


        try{
            Files.createDirectories(Paths.get(folderLocation));
            Path destinationPath = Paths.get("./screenshots/" + testName +formattedNow + ".png");
            Files.copy(screenshot.toPath(), destinationPath);
            System.out.println("Screenshot saved at " + destinationPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
