package com.aryeet.demo.bdd.webdriver;

import io.cucumber.core.internal.gherkin.ast.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseWebDriverHooks {
   @Autowired
    private SharedDriver driver;


    private static final Logger LOGGER = LoggerFactory.getLogger(BaseWebDriverHooks.class);

    public void before(final Scenario scenario) {
        LOGGER.info("Running scenario: " + getScenarioIdentifier(scenario));
    }
    public void embedScreenshotIfFailed(final Scenario scenario){
    }


    private String getScenarioIdentifier(final Scenario scenario) {
        return scenario.getName() + " (#" + scenario.getSteps() + ")";
    }

}
