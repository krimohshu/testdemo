package scenarios.steps;


import com.aryeet.demo.bdd.model.CharacterFilter;
import com.aryeet.demo.bdd.pages.HomePage;
import com.aryeet.demo.bdd.pages.ResultPage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WhichTVReviewHomeSteps extends AbstractStepDefinition {


    private static final Logger log = LoggerFactory.getLogger(WhichTVReviewHomeSteps.class);

    @Autowired
    Environment environment;

    @Autowired
    private HomePage homePage;

    @Autowired
    private ResultPage resultPage;


    @Given("user navigate to {string} page")
    public void user_navigate_to_page(String goToPage) {
        homePage.goTo(environment.getProperty("base.url.home"));
    }
    @Given("User provide search (.*) field")
    public void search_text(String searchText) {
        homePage.search(searchText);
        homePage.selectSearchResult(searchText);

    }

    @When("verify result of search criteria")
    public void user_char_filter(CharacterFilter filterConditions) {
        SoftAssertions softly = new SoftAssertions();
        Map<String, String> resultValMap = new LinkedHashMap<>();

        resultValMap = resultPage.getResultTable();
        // | Pokédex_number | Height | Weight | Type  | Held_Items   |

        softly.assertThat(resultValMap.get("Pokédex_number")).isEqualToIgnoringCase(filterConditions.getPokédexNumber().stream().findFirst().get());
        softly.assertThat(resultValMap.get("Height")).isEqualToIgnoringCase(filterConditions.getHeight().stream().findFirst().get());
        softly.assertThat(resultValMap.get("Weight")).isEqualToIgnoringCase(filterConditions.getWeight().stream().findFirst().get());
        softly.assertThat(resultValMap.get("Type")).isEqualToIgnoringCase(filterConditions.getType().stream().findFirst().get());
        softly.assertThat(resultValMap.get("Held_Items")).isEqualToIgnoringCase(filterConditions.getHeldItems().stream().findFirst().get());
        softly.assertAll();

    }

}
