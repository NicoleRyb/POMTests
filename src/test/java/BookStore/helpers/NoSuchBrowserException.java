package BookStore.helpers;

public class NoSuchBrowserException extends Exception {
    NoSuchBrowserException(String browser){
        super("Browser not supported or the name of the browser is incorrect: " + browser);
    }
}
