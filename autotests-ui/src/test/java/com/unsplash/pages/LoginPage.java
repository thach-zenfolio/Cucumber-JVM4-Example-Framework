package com.unsplash.pages;

import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.unsplash.utils.ObjectMap;

@Component
public class LoginPage extends BasePage {

    @Autowired
    private ObjectMap objectmap = new ObjectMap("./src/test/java/com/unsplash/locators/login_page.properties");

    public void enterCredential(String usernameEmail, String pwd) {
        By usernameField = objectmap.getLocator("loginPage.emailField");
        setValue(usernameField, usernameEmail);
        By passwordField = objectmap.getLocator("loginPage.passwordField");
        setValue(passwordField, pwd);
    }

    public void clickOnLoginButton() {
        By loginButton = objectmap.getLocator("loginPage.loginBtn");
        clickElement(loginButton);
    }
}
