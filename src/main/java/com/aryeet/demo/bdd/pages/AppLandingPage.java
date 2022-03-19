package com.aryeet.demo.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AppLandingPage extends AbstractPageObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppLandingPage.class);

    protected static final By howManyDatesToGenerate = By.id("option-count-147fe348");
    protected static final By dateOutputFormat = By.id("option-format-147fe348");
    protected static final By customDateFormat = By.id("option-custom-format-147fe348");
    protected static final By startDate = By.id("option-start-147fe348");
    protected static final By endDate = By.id("option-end-147fe348");
    protected static final By resultDataInTextArea = By.className("data-wrapper");

    private String strDatesToGenerate= null;
    private String strDateOutputFormat= null;
    private String strCustomDateFormat= null;
    private String strStartDate= null;
    private String strEndDate = null;

    public static final String PATH = "/";

    public AppLandingPage(final String path, final WebDriver driver, final int waitTimeOutSeconds) {
        super(path + PATH, driver, waitTimeOutSeconds);
    }

    public AppLandingPage setAllTheOptions(){
        if(strDatesToGenerate !=null) {
            driver.findElement(howManyDatesToGenerate).clear();
            driver.findElement(howManyDatesToGenerate).sendKeys(strDatesToGenerate);
        }
        if(strDateOutputFormat !=null){
            Select dropdown = new Select(driver.findElement(dateOutputFormat));
            dropdown.selectByVisibleText(strDateOutputFormat);
        }
        if(strCustomDateFormat !=null){
            driver.findElement(customDateFormat).clear();
            driver.findElement(customDateFormat).sendKeys(strCustomDateFormat);
        }
        if(strStartDate !=null){
            driver.findElement(startDate).clear();
            driver.findElement(startDate).sendKeys(strStartDate);
        }
        if(strEndDate !=null){
            driver.findElement(endDate).clear();
            driver.findElement(endDate).sendKeys(strEndDate);
        }

        return this;
    }

    public void getresultDataInTextArea(){

      List<WebElement> textAreasElements =  driver.findElements(resultDataInTextArea);
        System.out.println(textAreasElements.get(0));
    }

    public AppLandingPage withSendDate(String strSendDate){
        this.strEndDate = strSendDate;
        return this;
    }

    public AppLandingPage withStartDate(String strStartDate){
        this.strStartDate = strStartDate;
        return this;
    }

    public AppLandingPage withCustomDateFormat(String strCustomDateFormat){
        this.strCustomDateFormat = strCustomDateFormat;
        return this;
    }

    public AppLandingPage withDateOutputFormat(String strDateOutputFormat){
        this.strDateOutputFormat = strDateOutputFormat;
        return this;
    }

    public AppLandingPage withHowManyDatesToGenerate(String strDatesToGenerate){
        this.strDatesToGenerate = strDatesToGenerate;
        return this;
    }




}
