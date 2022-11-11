package Pages;

import Enums.HomePageEnums;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    String HomePageCards = "//h5[contains(text(),'%s')]";
//    @FindBy(xpath = "//div[contains(@class,'playgound-header')]")
//    WebElement verifyBookStorePage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToBookStoreApplication() {
        WebElement bookStoreApplication = driver.findElement(By.xpath(String.format(HomePageCards, HomePageEnums.BookStoreApplicationCard_Text.getResourcesName())));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bookStoreApplication);
//        wait.until(ExpectedConditions.visibilityOf(verifyBookStorePage));
//        String bookStorePageVerifyText = verifyBookStorePage.getText();
//        Assert.assertEquals(bookStorePageVerifyText, "Book Store");
    }
}