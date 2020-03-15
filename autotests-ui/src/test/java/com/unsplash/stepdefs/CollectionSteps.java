package com.unsplash.stepdefs;

import com.unsplash.hooks.cleanup.DataStorage;
import com.unsplash.pages.*;
import com.unsplash.utils.Context;
import com.unsplash.utils.Props;
import com.unsplash.utils.RandomUtils;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class CollectionSteps extends AbstractStep implements En {
    @Autowired
    private World world;
    @Autowired
    private HomePage homePage;
    @Autowired
    private SearchResultPage searchResultPage;
    @Autowired
    private CreateNewCollectionForm createNewCollectionForm;
    @Autowired
    private CollectionPage collectionPage;
    @Autowired
    private DataStorage dataStorage;

    public CollectionSteps() {

        When("^he searches pics with keyword \"(.*)\"", (String keyword) -> {
            homePage.enterSearch(keyword);
        });

        When("^he adds the first image into new collection name \"(.*)\"", (String collectionName) -> {
            searchResultPage.addFirstPicToCollection();
            searchResultPage.addToNewCollection();
            collectionName = collectionName + RandomUtils.getRandomNumber();
            createNewCollectionForm.createNewCollection(collectionName, "", false);
            world.scenarioContext.setContext(Context.COLLECTION_NAME, collectionName);
            dataStorage.setCollectionNames(collectionName);

        });

        When("^he navigates to his collection page$", () -> {
            collectionPage.open(Props.getProp("baseUrl") + "@" + Props.getProp("userProfile")+ "/collections");
        });

        When("^he can see the new collection is added in his collection", () -> {
            String collectionName = (String)world.scenarioContext.getContext(Context.COLLECTION_NAME);
            assertThat(collectionPage.getCollectionNameList()).contains(collectionName);
        });
    }
}
