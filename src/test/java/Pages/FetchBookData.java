package Pages;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class FetchBookData {
    WebDriver driver;
    By bookTitle = By.xpath("//a[contains(text(),'Git Pocket Guide')]");
    By bookAuthor = By.xpath("//div[contains(text(),'Richard E. Silverman')]");
    By bookPublisher = By.xpath("(//div[contains(text(),\"O'Reilly Media\")])[1]");

    public FetchBookData(WebDriver driver) {
        this.driver = driver;
    }

    public void BookData() {
        RequestSpecification request = RestAssured.given();
        Response endpoint = request.get("https://demoqa.com/BookStore/v1/Books");
        ResponseBody body = endpoint.getBody();
        String responseBody = body.asString();
        JsonElement fileElement = JsonParser.parseString(responseBody);
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfBooks = fileObject.get("books").getAsJsonArray();
        List<BooksPojo> booksData = new ArrayList<>();
        for (JsonElement booksElement : jsonArrayOfBooks.getAsJsonArray()) {
            JsonObject booksJsonObject = booksElement.getAsJsonObject();
            BooksPojo obj = new BooksPojo(booksJsonObject.get("isbn").toString(), booksJsonObject.get("title").toString(), booksJsonObject.get("subTitle").toString(), booksJsonObject.get("author").toString(), booksJsonObject.get("publish_date").toString(), booksJsonObject.get("publisher").toString(), booksJsonObject.get("pages").getAsInt(), booksJsonObject.get("description").toString(), booksJsonObject.get("website").toString());
            booksData.add(obj);
        }
        String expectedFirstBookTitle = booksData.get(0).getTitle();
        String expectedFirstBookAuthor = booksData.get(0).getAuthor();
        String expectedFirstBookPublisher = booksData.get(0).getPublisher();

        expectedFirstBookTitle = expectedFirstBookTitle.replace("\"", "");
        expectedFirstBookAuthor = expectedFirstBookAuthor.replace("\"", "");
        expectedFirstBookPublisher = expectedFirstBookPublisher.replace("\"", "");

        String fistBookTitleUi = driver.findElement(bookTitle).getText();
        String firstBookAuthorUi = driver.findElement(bookAuthor).getText();
        String firstBookPublisherUi = driver.findElement(bookPublisher).getText();

        Assert.assertEquals(fistBookTitleUi, expectedFirstBookTitle);
        Assert.assertEquals(firstBookAuthorUi, expectedFirstBookAuthor);
        Assert.assertEquals(firstBookPublisherUi, expectedFirstBookPublisher);
    }
}