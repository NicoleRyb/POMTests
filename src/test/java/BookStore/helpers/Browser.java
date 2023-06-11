package BookStore.helpers;

import org.openqa.selenium.WebDriver;

public record Browser(WebDriver driver, String baseURL) {
}
