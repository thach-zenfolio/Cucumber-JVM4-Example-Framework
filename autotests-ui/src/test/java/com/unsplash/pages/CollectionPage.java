package com.unsplash.pages;

import com.unsplash.utils.ObjectMap;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectionPage extends BasePage {

    private ObjectMap locator = new ObjectMap(
            "./src/test/java/com/unsplash/locators/user_collection_page.properties");

    public List<String> getCollectionNameList() {
        By COLLECTION_NAMES = locator.getLocator("userCollectionPage.collectionName");
        return getTexts(COLLECTION_NAMES);
    }

}
