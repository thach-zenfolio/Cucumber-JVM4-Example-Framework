package com.unsplash.configuration;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.unsplash.utils.Props;

/*----TAGS LIST----
 * @regression
 * @mpix
 * ------ @print @small-print @medium-large-print @square-wide-prints @value-prints
 * ------ @greeting-cards @flat-greeting-cards @modern-pop-cards @folded-greeting-cards
 * ------ @photo-gifts @calendars
 *
 * @design-template
 * */

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.unsplash.stepdefs",
        tags = "@regression and (not @wip) and (not @production)",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cukesjson.json",
                "testng:target/cucumber-reports/cukestestng.xml",
                "rerun:target/cucumber-reports/failed_scenarios.txt"
        }
)

public class CukesTestRunner extends AbstractTestNGCukesTest {

    @BeforeClass
    public static void before() {
        // Load Profile Path for Test/Stage/Production Environment.
        Props.loadRunConfigProps(EvnConfig.getEnvProfilePath());

        // Setup Before run any Cucumber Test
    }

    @AfterClass
    public static void after() {
        //Tear Down After All Cucumber Scenarios run.
    }

}
