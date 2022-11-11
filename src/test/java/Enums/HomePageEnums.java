package Enums;

public enum HomePageEnums {
    BookStoreApplicationCard_Text("Book Store Application");
    private String name;

    private HomePageEnums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}