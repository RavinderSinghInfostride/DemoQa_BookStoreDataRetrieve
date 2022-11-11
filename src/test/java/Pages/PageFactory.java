package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private HomePage homePage;
    private FetchBookData fetchBookData;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public FetchBookData getFetchBookData() {
        if (fetchBookData == null) {
            fetchBookData = new FetchBookData(driver);
        }
        return fetchBookData;
    }
}