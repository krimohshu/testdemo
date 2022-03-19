package scenarios.steps;

import com.aryeet.CucumberAutomationApp;
import com.aryeet.api.request.CommonRequestSpecDto;
import com.aryeet.api.request.HttpOperations;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("qa")
@EnableAutoConfiguration
@ContextConfiguration(classes = CucumberAutomationApp.class)
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
        //TODO: Removed relaxedHTTPSValidation when trusted cert is used. Add trusted cert to keystore
        requestSpecification = RestAssured.given().relaxedHTTPSValidation();

    }

    @Test
    public void contextIntegrationTest() {
        System.out.println("A test of springboottest config");
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response get(String endpoint) {
        return requestSpecification.get(endpoint);
    }

    public void header(String key, String value) {
        requestSpecification.header(key, value);
    }

    public Response post(String endpoint) {
        return requestSpecification.post(endpoint);
    }

    public String getBaseUrl() {
        return RestAssured.baseURI;
    }

    public String getVersion() {
        return version;
    }

    public RequestSpecification clearRequestSpecification() {
        requestSpecification = RestAssured.given().relaxedHTTPSValidation();
        return requestSpecification;
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

    protected Response getResponse(CommonRequestSpecDto request, HttpOperations ops, URL url) {
        Response response = null;

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "*/*");
//        headers.put("Cache-Control", "no-cache");
//        headers.put("Postman-Token", "cc6e17df-4306-4990-8b0e-3389d1200619");
        headers.put("Host", "dummy.restapiexample.com");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Upgrade-Insecure-Requests", "1");
        //  header("user-agent", "rest assured");
    //    header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
        header("User-Agent", "PostmanRuntime/7.20.1");
        //   headers.put("Connection", "keep-alive");
        // headers.put("Vary", "Accept-Encoding,X-APP-JSON");


        switch (ops) {
            case GET:
                log.info("GET Call : " + request.getEndPoint());

                response = requestSpecification.headers(headers).get(request.getEndPoint());


                response = requestSpecification.contentType("application/json;charset=utf-8")
                        .header("ContentType", "application/json").get(request.getEndPoint());
                break;
            case PUT:
                log.debug("PUT Call : " + request.getEndPoint());
                response = requestSpecification.put(request.getEndPoint());
                break;
            case POST:
                log.debug("POST Call : " + request.getEndPoint());
                response = requestSpecification.post(request.getEndPoint());
                break;
            case PATCH:
                log.debug("PATCH Call : " + request.getEndPoint());
                response = requestSpecification.patch(request.getEndPoint());
                break;
            case DELETE:
                break;
        }

        return response;
    }

    @Given("hi")
    public void hi() {

    }

}