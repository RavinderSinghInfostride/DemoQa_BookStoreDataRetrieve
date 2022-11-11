package Pages;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

public class FetchBookData {
    WebDriver driver;

    public FetchBookData(WebDriver driver) {
        this.driver = driver;
    }

    public void BookData() {
        RequestSpecification request = RestAssured.given();
        Response endpoint = request.get("https://demoqa.com/BookStore/v1/Books");
        ResponseBody body = endpoint.getBody();
        String responseBody = body.asString();
        JsonPath jsnPath = endpoint.jsonPath();
        String s = jsnPath.get("books").toString();
        JsonElement fileElement = JsonParser.parseString(responseBody);
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfBooks = fileObject.get("books").getAsJsonArray();
        List<BooksPojo> books = new ArrayList<>();
        for (JsonElement booksElement : jsonArrayOfBooks.getAsJsonArray()) {
            JsonObject booksJsonObject = booksElement.getAsJsonObject();
            BooksPojo bookData = new BooksPojo(booksJsonObject.get("title").toString(), booksJsonObject.get("author").toString(), booksJsonObject.get("publisher").toString());
            books.add(bookData);
        }
        System.out.println(books);
    }
}