package scenarios.steps;

import com.aryeet.api.model.EmployeeListWithStatus;
import com.aryeet.api.request.CommonRequestSpecDto;
import com.aryeet.api.request.HttpOperations;
import com.aryeet.api.verify.VerifyRules;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestAPISteps extends AbstractStepDefinition {

    private static final Logger log = LoggerFactory.getLogger(RestAPISteps.class);

    @Autowired
    private Environment environment;

    @Autowired
    private VerifyRules verifyRules;

    @Autowired
    private CucumberContextConfiguration cucumberCtx;

    @Autowired
    private CommonRequestSpecDto commonRequestSpecDto;

    private Response response;
    SoftAssertions softly = new SoftAssertions();
    EmployeeListWithStatus returnedEmps = null;

    @Before
    public void before(final Scenario scenario) {
        super.before(scenario);
    }


    @Given("user call following restapi {string} with {string} page")
    public void user_Call_Following_Restapi(String relativeEndPoint, String httpMethod) throws MalformedURLException {
        // embedTextInReport("Following endpoint with HTTP method");

        URL baseUrl = new URL(cucumberCtx.getBaseUrl());
        URL url = new URL(baseUrl, environment.getProperty(relativeEndPoint));

        commonRequestSpecDto.setEndPoint(url.toString());
        response = cucumberCtx.getResponse(commonRequestSpecDto, HttpOperations.setOpertaion(httpMethod) ,url );
        System.out.println(response.getBody().asString());

    }


    @Then("User should able to verify {string} tests")
    public void user_Call_Following_Restapi(String expectedResult) {

       // returnedEmps = response.getBody().as(EmployeeListWithStatus.class);
        // embedTextInReport("Following endpoint with HTTP method");
        //Assertions
        softly.assertThat(response.getStatusCode()).isEqualTo(200);

        if (verifyRules.isRulePresent(expectedResult)) assertTrue(verifyRules.isRulePassed(expectedResult, response));

        if (returnedEmps != null) softly.assertThat(returnedEmps.getEmployee().size()).isGreaterThan(1);


        softly.assertAll();

    }


}
