package Test;

import Pages.BaseClass;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;

public class ToFetchBooksData extends BaseClass {
    @Test
    public void fetchBooksData() throws FileNotFoundException {
        pageFactory.getHomePage().navigateToBookStoreApplication();
        pageFactory.getFetchBookData().BookData();
    }
}