package com.unsplash.pages;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import com.unsplash.utils.ObjectMap;

@Component
public class MainPage extends BasePage {

    private ObjectMap objectmap = new ObjectMap("./src/test/java/com/unsplash/locators/main_page.properties");

    public void clickOnLoginLink() {
        By loginBtn = objectmap.getLocator("mainPage.loginLink");
        clickElement(loginBtn);
    }

    public void clickJoinFreeLink() {
        By signUpLink = objectmap.getLocator("mainPage.signUpLink");
        clickElement(signUpLink);
    }

}
