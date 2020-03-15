package com.unsplash.stepdefs;

import org.springframework.stereotype.Component;
import com.unsplash.hooks.WebDriverFactory;
import com.unsplash.utils.ScenarioContext;

@Component
public class World {
    public ScenarioContext scenarioContext;
    public WebDriverFactory webDriverFactory;

}
