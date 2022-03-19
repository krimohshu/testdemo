package com.aryeet.demo.cucumber.scenarios.steps;

import com.aryeet.demo.SpringApp;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("qa")
@EnableAutoConfiguration
@ContextConfiguration(classes = SpringApp.class)
@TestPropertySource({
        "classpath:application.properties",
        "classpath:application-${spring.profiles.active}.properties"
})
public class CucumberContextConfiguration {
    private static final Logger log = LoggerFactory.getLogger(CucumberContextConfiguration.class);

    @Autowired
    private ConfigurableEnvironment env;

    /* @LocalServerPort
     protected int port;
 */
    private RequestSpecification requestSpecification;
    private final String version;

    @Autowired
    public CucumberContextConfiguration(@Value("${rest.baseurl}") String baseUrl, @Value("${version.number}") String version) {
        this.version = version;
        RestAssured.baseURI = baseUrl + version;
        requestSpecification = RestAssured.given().relaxedHTTPSValidation();

    }

    @Test
    public void contextIntegrationTest() {
        System.out.println("A test of springboottest config");
    }

    public String getEndPointUrl(String endpointInCucumber) {

        final String endpoint;

        switch (endpointInCucumber) {
            case "EMPLOYEES_BASEURL":
                endpoint = env.getProperty("EMPLOYEES_BASEURL");
                break;

            case "CREATE_EMPLOYEE_BASEURL":
                endpoint = env.getProperty("CREATE_EMPLOYEE_BASEURL");
                break;

            default:
                throw new IllegalArgumentException("Invalid endpoint in cucumber feature file: " + endpointInCucumber);

        }
        return endpoint;
    }

    @Given("hi")
    public void hi() {

    }

}