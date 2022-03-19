package scenarios.steps;

import com.aryeet.model.ReviewFilter;
import com.aryeet.model.TVInfoCard;
import com.aryeet.pages.TvInfoCardPage;
import com.aryeet.pages.WhichReviewHomePage;
import com.aryeet.rules.RuleVerificationDTO;
import com.aryeet.util.StringUtils;
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
import java.util.Map;
import java.util.stream.Collectors;

public class WhichTVReviewHomeSteps extends AbstractStepDefinition{

    private static final Logger log = LoggerFactory.getLogger(WhichTVReviewHomeSteps.class);

    @Autowired
    Environment environment;

    @Autowired
    private WhichReviewHomePage whichReviewHomePage;

    @Autowired
    private TvInfoCardPage tvInfoCardPage;

    @Autowired
    private RuleVerificationDTO ruleVerificationDTO;

    Map<Integer, TVInfoCard> getReviewCardMap = new HashMap<>();
    ReviewFilter filterConditions = null;

    @Before
    public void before(final Scenario scenario) {
        super.before(scenario);
    }

    @Given("user navigate to {string} page")
    public void user_navigate_to_page(String goToPage) {
        whichReviewHomePage.goTo(environment.getProperty("base.url.which.review.home"));
    }

    @Given("updating the reporting in framework")
    public void report() {
        embedTextInReport("this is krishan testing logging");
    }

    @When("sort the {string} page with {string} sort-option")
    public void sort_the_home_page_with_sort_option(String goToPage, String sortOption) {
        whichReviewHomePage.selectDropdownByText(sortOption);
    }

    @When("User set filter conditions")
    public void user_set_filter_conditions(ReviewFilter filterConditions) {

        whichReviewHomePage.setfilters(filterConditions);
        this.filterConditions = filterConditions;
        ruleVerificationDTO.setReviewFilter(filterConditions);
        embedTextInReport("Filter Conditions: " + filterConditions.toString());
    }

    @Then("verify filtered result of TV review products pass {string} rule")
    public void verify_filtered_result_of_TV_review_products_pass(String ruleEngineIndex) {
        SoftAssertions softly = new SoftAssertions();

        //Get All the tv product cards
        getReviewCardMap = tvInfoCardPage.getAllTVReviewCard();

        ruleVerificationDTO.setRuleIndex(ruleEngineIndex.split(";")[0]);
        //Get enum of input provided by user- screensize
        String filterStringToMatch = filterConditions.getFilterScreenSize().stream()
                .map(y -> y.getSizeOption())
                .collect(Collectors.joining(","));

        //convert enum to screen size e.g. screen_32_34 will be 32-inches, 33-inches, 34-inches
        String allTVSizeType = StringUtils.getTVsizeBasedOnInpu(filterStringToMatch);

        //Verify using soft assertion
        getReviewCardMap.entrySet().stream()
                .filter(y -> y != null && y.getValue() != null && y.getValue().getImportantFeature() != null)
                .forEach(screensizetest -> {

                    softly.assertThat(allTVSizeType).contains(screensizetest.getValue()
                            .getImportantFeature()
                            .getScreenSize());

                    embedTextInReport("Runtime screensize: " + screensizetest.getValue()
                            .getImportantFeature()
                            .getScreenSize() + " vs user expecting size in " + allTVSizeType);
                });

        //Get enum of input provided by user - screentype
        String filterStringToMatchForTvType = filterConditions.getFilterScreenType().stream()
                .map(y -> y.getScreenTypeOption())
                .collect(Collectors.joining(","));

        //convert enum to screen type e.g. OLED, LCD etc
        String allTVTypeType = StringUtils.getTVTypeBasedOnInput(filterStringToMatchForTvType);

        getReviewCardMap.entrySet().stream()
                .filter(y -> y != null && y.getValue() != null && y.getValue().getImportantFeature() != null)
                .forEach(screentypetest -> {
                    softly.assertThat(allTVTypeType).contains(screentypetest.getValue()
                            .getImportantFeature()
                            .getScreenType());

                    embedTextInReport("Runtime ScreenType: " + screentypetest.getValue()
                            .getImportantFeature()
                            .getScreenType() + " vs user expecting type in " + allTVTypeType);
                });

        softly.assertAll();
    }

    @When("get all the TV reviews on the page")
    public void get_all_the_TV_reviews_on_the_page() {

    }

}
