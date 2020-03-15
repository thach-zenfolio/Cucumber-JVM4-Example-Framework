package com.unsplash.configuration;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.unsplash.utils.Props;


/**
 * Change "pretty" to "com.unsplash.utils.log4j.PrettyLogFormatter" if you want to see the console log while running parallel
 * To Run Smoke Test for Production, change the tags to "@production and @smoke-test" and profile.path to production environment
 */

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "com.unsplash.stepdefs",
        tags = "@add-search-result",
        plugin = {
                "pretty",
                "json:target/json-cucumber-reports/cukejson.json",
                "testng:target/testng-cucumber-reports/cuketestng.xml",
                "rerun:target/failed_scenarios.txt"
        },
        dryRun = false,
        strict = true
)
public class CukesTestRunnerParallel extends AbstractTestNGCucumberParallelTests {

    @BeforeClass
    public static void before() {
        // Load Profile Path for Test/Stage/Production Environment.
        Props.loadRunConfigProps(EvnConfig.getEnvProfilePath());

        // Setup Before run any Cucumber Test
    }

    @AfterClass
    public static void after() {
        //Clean Up After run all Cucumber Tests
    }
}