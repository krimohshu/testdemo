package com.aryeet.demo.cucumber.scenarios.steps;

import com.aryeet.demo.bdd.webdriver.SharedDriver;
import io.cucumber.java.Scenario;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractStepDefinition {
    protected Scenario scenario;
    @Autowired
    SharedDriver driver;

    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    protected void embedTextInReport(final String text) {
        scenario.write(text + "\n");
    }

    protected void logTimeOnReport(){
        scenario.write("Step Executed on : " +  new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));

    }

    protected String createEndPoint(String baseURl, String apiRelativeEndPoint , String pathParam, String requestParam  ){

        return  baseURl + apiRelativeEndPoint + pathParam ;
    }


}
