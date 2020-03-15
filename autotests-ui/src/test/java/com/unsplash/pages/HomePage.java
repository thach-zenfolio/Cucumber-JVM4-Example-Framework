package com.unsplash.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;
import com.unsplash.utils.ObjectMap;


@Component
public class HomePage extends BasePage {

    private ObjectMap homePageLocator = new ObjectMap("./src/test/java/com/unsplash/locators/home_page.properties");

    public void enterSearch(String keyword) {
        By searchBox = homePageLocator.getLocator("homePage.searchBox");
        setValue(searchBox, keyword);
        sendKeys(searchBox, Keys.ENTER);
    }
}