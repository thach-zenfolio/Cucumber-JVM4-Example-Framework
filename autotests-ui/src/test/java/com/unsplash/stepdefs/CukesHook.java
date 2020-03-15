package com.unsplash.stepdefs;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.unsplash.configuration.EvnConfig;
import com.unsplash.hooks.WebDriverFactory;
import com.unsplash.hooks.cleanup.DataStorage;
import com.unsplash.testdata.CommonData;
import com.unsplash.utils.ScenarioContext;

import java.util.concurrent.TimeUnit;

@ContextConfiguration("file:src/test/resources/spring.xml")
public class CukesHook {

    @Autowired
    private DataStorage dataStorage;
    @Autowired
    private World world;

    @Before
    public void setupCukesBeforeScenarioAndConfigSpringContext(Scenario scenario) {
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
        //Also , This is to Setup things before each Cucumber Scenario.
        world.scenarioContext = new ScenarioContext();
        world.webDriverFactory = new WebDriverFactory();
        world.webDriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        world.webDriverFactory.getDriver().manage().timeouts().pageLoadTimeout(CommonData.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        world.webDriverFactory.getDriver().manage().timeouts().setScriptTimeout(CommonData.SCRIPT_TIMEOUT, TimeUnit.SECONDS);
        world.webDriverFactory.getDriver().manage().window().maximize();

        String featureName = scenario.getId().split(";")[0].replace("-", " ");
        System.out.println("=====Executing ..." + scenario.getName() + "(" + featureName + ")");
    }

    @After
    public void cleanUpAfterScenario(Scenario scenario) {
        String featureName = scenario.getId().split(";")[0].replace("-", " ");
        if (scenario.isFailed()) {
            // Taking the screenshot and saving into report in case the scenario is failed.
            if (EvnConfig.getEnvScreenShotOnFailure()) {
                byte[] source = ((TakesScreenshot) world.webDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(source, "image/png");
            }
            System.out.println("=====Scenario Failed!!!!!!! " + scenario.getName() + "===FEATURE LOCATION: " + featureName);
        }
        dataStorage.cleanUpAll();
        world.webDriverFactory.getDriver().quit();
    }
}
