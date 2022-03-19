package com.aryeet.demo.bdd.pages;

import com.aryeet.model.ReviewFilter;
import com.aryeet.model.SortBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhichReviewHomePage extends AbstractPageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhichReviewHomePage.class);

    private static final By SORTBY_DROPDOWN = By.id("product_listing_sorter");
    private static final By DATA_WHICH_BUTTON_SCREENSIZE = By.cssSelector("button[data-which-button='screen_size-filter']");
    private static final By DATA_WHICH_BUTTON_SCREENTYPE = By.cssSelector("button[data-which-button='screen_type-filter']");
    private static final By DONE_BUTTON = By.cssSelector("button[data-which-id='done-button']");
    private static final By DROPDOWN_CHOICES = By.cssSelector("label[for='_CSS_VALUE_']");
    private static final By SCREENSIZE_CHECKBOX = By.id("_ID_VALUE_");
    public static final String PATH = "/";

    public WhichReviewHomePage(final String path, final WebDriver driver, final int waitTimeOutSeconds) {
        super(path + PATH, driver, waitTimeOutSeconds);
    }

    public void selectDropdownByText(String sortOption) {
        super.selectDropdownByText(SORTBY_DROPDOWN, SortBy.valueOf(sortOption).getSortOption());
    }

    public void setfilters(ReviewFilter reviewFilter) {

        clickFilterDropDown(DATA_WHICH_BUTTON_SCREENSIZE);
        // Select all the Screensize of th{
        //
        //        clickFilterDropDown(DATA_WHICH_BUTTON_SCREENSIZE);
        //        // Select all the Screensize of the user provided by the cucumber
        //        reviewFilter.getFilterScreenSize()
        //                .stream()
        //                .forEach(checkScreenSizeOption -> {
        //                    setCheckbox(getCssSelectorWithValue(DROPDOWN_CHOICES, checkScreenSizeOption.getSizeOption()), true);
        //                });
        //
        //        clickFilterDropDown(DATA_WHICH_BUTTON_SCREENTYPE);
        //        // Select all the Screensize of the user provided by the cucumber
        //        reviewFilter.getFilterScreenType()
        //                .stream()
        //                .forEach(checkScreenSizeOption -> {
        //                    setCheckbox(getCssSelectorWithValue(DROPDOWN_CHOICES, checkScreenSizeOption.getScreenTypeOption()), true);
        //                });
        //
        //      /*  waitForInvisibilityOfSpinner(30);
        //        driver.findElement(DONE_BUTTON).click();*/
        //        clickFilterDropDown(DATA_WHICH_BUTTON_SCREENTYPE);
        //    }e user provided by the cucumber
        reviewFilter.getFilterScreenSize()
                .stream()
                .forEach(checkScreenSizeOption -> {
                    setCheckbox(getCssSelectorWithValue(DROPDOWN_CHOICES, checkScreenSizeOption.getSizeOption()), true);
                });

        clickFilterDropDown(DATA_WHICH_BUTTON_SCREENTYPE);
        // Select all the Screensize of the user provided by the cucumber
        reviewFilter.getFilterScreenType()
                .stream()
                .forEach(checkScreenSizeOption -> {
                    setCheckbox(getCssSelectorWithValue(DROPDOWN_CHOICES, checkScreenSizeOption.getScreenTypeOption()), true);
                });

      /*  waitForInvisibilityOfSpinner(30);
        driver.findElement(DONE_BUTTON).click();*/
        clickFilterDropDown(DATA_WHICH_BUTTON_SCREENTYPE);
    }

    private void clickFilterDropDown(By dropDownType) {
        waitForInvisibilityOfSpinner(10);
        getDriver().findElement(dropDownType).click();
        genericWait(3);
    }

}
