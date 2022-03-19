package com.aryeet.demo.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends AbstractPageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    private static final By SEARCH_INPUT_BOX = By.id("search_query");
    private static final By SEARCH_RESULT = By.xpath("//*[@id=\"root\"]/div/ul/li[1]/a");


    public static final String PATH = "/";
    public HomePage(final String path, final WebDriver driver, final int waitTimeOutSeconds) {
        super(path + PATH, driver, waitTimeOutSeconds);

    }

    public void search(String searchCriteria){
        getElement(SEARCH_INPUT_BOX).sendKeys(searchCriteria);

    }

    public void selectSearchResult(String searchText) {
        getElement(SEARCH_RESULT).click();
    }
}
