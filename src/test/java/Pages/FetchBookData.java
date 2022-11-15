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
    By firstBookTitle = By.xpath("//a[contains(text(),'Git Pocket Guide')]");
    By firstBookAuthor = By.xpath("//div[contains(text(),'Richard E. Silverman')]");
    By firstBookPublisher = By.xpath("(//div[contains(text(),\"O'Reilly Media\")])[1]");

    public FetchBookData(WebDriver driver) {
        this.driver = driver;
    }

    public void BookData() {
        RestAssured.baseURI = "https://demoqa.com/";
        RequestSpecification request = RestAssured.given();
        Response endpoint = request.get("BookStore/v1/Books");
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
        String expectedFirstBookTitle = booksData.get(0).getTitle().replace("\"", "");
        Assert.assertEquals(driver.findElement(firstBookTitle).getText(), expectedFirstBookTitle);

        String expectedFirstBookAuthor = booksData.get(0).getAuthor().replace("\"", "");
        Assert.assertEquals(driver.findElement(firstBookAuthor).getText(), expectedFirstBookAuthor);

        String expectedFirstBookPublisher = booksData.get(0).getPublisher().replace("\"", "");
        Assert.assertEquals(driver.findElement(firstBookPublisher).getText(), expectedFirstBookPublisher);
    }
}