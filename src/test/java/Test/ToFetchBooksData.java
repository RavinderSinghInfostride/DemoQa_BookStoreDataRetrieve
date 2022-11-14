package Test;

import Pages.BaseClass;
import org.testng.annotations.Test;

public class ToFetchBooksData extends BaseClass {

    @Test
    public void fetchBooksData() {
        pageFactory.getHomePage().navigateToBookStoreApplication();
        pageFactory.getFetchBookData().BookData();
    }
}