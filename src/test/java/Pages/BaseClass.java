package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    public WebDriver driver;
    protected PageFactory pageFactory;

    @BeforeClass
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/");
        String homePageUrl = driver.getCurrentUrl();
        Assert.assertEquals(homePageUrl, "https://demoqa.com/");
        pageFactory = new PageFactory(driver);
    }
}