package Pages;

import Enums.HomePageEnums;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    By bookStoreApplicationPageVerify = By.xpath("//div[@class='main-header']");
    String HomePageCards = "//h5[contains(text(),'%s')]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToBookStoreApplication() {
        WebElement bookStoreApplication = driver.findElement(By.xpath(String.format(HomePageCards, HomePageEnums.BookStoreApplicationCard_Text.getResourcesName())));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bookStoreApplication);
        Assert.assertEquals(driver.findElement(bookStoreApplicationPageVerify).getText(), "Book Store");
    }
}