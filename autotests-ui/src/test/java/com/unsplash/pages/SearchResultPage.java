package com.unsplash.pages;

import com.unsplash.utils.ObjectMap;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class SearchResultPage extends BasePage {

    private ObjectMap homePageLocator = new ObjectMap("./src/test/java/com/unsplash/locators/search_result_page.properties");

    public void addFirstPicToCollection() {
        By firstMatchedItem = homePageLocator.getLocator("searchResultPage.firstMatchedItem");
        By addCollectionBtn = homePageLocator.getLocator("searchResultPage.firstMatchAddCollectionBtn");
        hoverOnElement(firstMatchedItem);
        clickElement(addCollectionBtn);
    }

    public void addToNewCollection() {
        By newCollectionName = homePageLocator.getLocator("searchResultPage.newCollectionName");
        clickElement(newCollectionName);
    }

}