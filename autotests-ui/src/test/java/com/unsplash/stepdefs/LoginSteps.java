package com.unsplash.stepdefs;

import com.unsplash.apihelper.CollectionHelper;
import com.unsplash.pages.*;
import com.unsplash.testdata.CommonData;
import com.unsplash.utils.Props;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;


public class LoginSteps extends AbstractStep implements En {
    @Autowired
    private MainPage mainPage;
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private CollectionHelper collectionHelper;

    public LoginSteps() {
        When("^the user is on the main page$", () -> {
            mainPage.open(Props.getProp("baseUrl"));
        });

        When("^the user goes to Login Page$", () -> {
            mainPage.clickOnLoginLink();
        });

        When("^the user logs in \"(.*)\" credential", (String userName) -> {
            String[] userInfo = CommonData.accountInfo.get(userName);
            loginPage.enterCredential(userInfo[2], userInfo[1]);
            loginPage.clickOnLoginButton();
        });
    }
}
