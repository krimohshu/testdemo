package scenarios.steps;

import com.aryeet.model.ReviewFilter;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class TypeConversionStepDefinitions {

    @DataTableType
    public ReviewFilter whichFilterCriteria(Map<String, String> entry) {
        return new ReviewFilter().setReviewFilter(entry);
    }

}
