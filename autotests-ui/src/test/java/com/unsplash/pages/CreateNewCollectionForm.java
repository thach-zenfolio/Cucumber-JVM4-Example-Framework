package com.unsplash.pages;

import com.unsplash.utils.ObjectMap;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class CreateNewCollectionForm extends BasePage {

    private ObjectMap locator = new ObjectMap(
            "./src/test/java/com/unsplash/locators/create_collection_form_page.properties");

    private void enterTitle(String title) {
        By TITLE = locator.getLocator("createCollectionForm.title");
        setValue(TITLE, title);
    }

    private void enterDescription(String desc) {
        By DESCRIPTION = locator.getLocator("createCollectionForm.description");
        setValue(DESCRIPTION, desc);
    }

    public void createNewCollection(String title, String desc, boolean privacy) {
        By PRIVACY = locator.getLocator("createCollectionForm.privacy");
        By CREATE_COLLECTION = locator.getLocator("createCollectionForm.createCollectionBtn");
        By NEW_CREATED_COLLECTION = locator.getLocatorAndFormats("createCollectionForm.newCreatedCollectionTxt", title);
        enterTitle(title);
        if (desc.isEmpty()) {
            enterDescription(desc);
        }
        if (privacy) {
            clickElement(PRIVACY);
        }
        clickElement(CREATE_COLLECTION);
        waitElementToBeDisappear(CREATE_COLLECTION);
        waitElementsToBeAppear(NEW_CREATED_COLLECTION);
    }
}


