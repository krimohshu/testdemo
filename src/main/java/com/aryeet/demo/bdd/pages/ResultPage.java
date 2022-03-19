package com.aryeet.demo.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultPage extends AbstractPageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultPage.class);

    private static final By SEARCH_RESULT_VALUES = By.xpath("//*[@id=\"root\"]/div/div[1]/div/table");

    public static final String PATH = "/";

    public ResultPage(final String path, final WebDriver driver, final int waitTimeOutSeconds) {
        super(path + PATH, driver, waitTimeOutSeconds);

    }

    public  Map<String, String> getResultTable() {
        List<WebElement> rowsList = getElement(SEARCH_RESULT_VALUES).findElements(By.tagName("tr"));
        List<WebElement> columnsList = null;

        Map<String, String> resultValMap = new LinkedHashMap<>();

        for (WebElement row : rowsList) {
            System.out.println();
            columnsList = row.findElements(By.tagName("td"));
            resultValMap.put(columnsList.get(0).getText(), columnsList.get(1).getText());

//            for (WebElement column : columnsList) {
//                System.out.print(column.getText() + ",");
//                resultValMap.put(columnsList[0] , columnsList[1]);
//            }

        }
        return resultValMap;

    }


}
