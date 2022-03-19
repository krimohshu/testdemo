package com.aryeet.demo.cucumber.scenarios.steps;

import com.aryeet.demo.bdd.model.CharacterFilter;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class TypeConversionStepDefinitions {

    @DataTableType
    public CharacterFilter whichFilterCriteria(Map<String, String> entry) {
        return new CharacterFilter().setReviewFilter(entry);
    }

}
