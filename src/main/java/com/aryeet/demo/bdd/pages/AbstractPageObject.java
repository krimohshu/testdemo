package com.aryeet.demo.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbstractPageObject {
    protected final String path;
    protected final WebDriver driver;
    protected final int waitTimeOutSeconds;
    protected static final int BIG_TIMEOUT_IN_SECONDS = 30;
    protected static final int SMALL_TIMEOUT_IN_SECONDS = 2;

    protected static final By SPINNER = By.className("_25mE_ GGvcI Y4_7P");


    public AbstractPageObject(final String path, final WebDriver driver, final int waitTimeOutSeconds) {
        this.path = path;
        this.driver = driver;
        this.waitTimeOutSeconds = waitTimeOutSeconds;
    }

    public String getPath() {
        return path;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public int getWaitTimeOutSeconds() {
        return waitTimeOutSeconds;
    }

    public static int getBigTimeoutInSeconds() {
        return BIG_TIMEOUT_IN_SECONDS;
    }

    public static int getSmallTimeoutInSeconds() {
        return SMALL_TIMEOUT_IN_SECONDS;
    }

    /**
     * perform global navigation based on the path provided to page
     */
    public void goTo(final String path) {
        //  driver.navigate().refresh();
        getDriver().get(path);

        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    /**
     * Universal dropdown selector
     * accept By definition and call a timebound selection with appropriate waitProperty
     */
    public void selectDropdownByText(final By locator, final String visibleText) {
        waitForElement(locator);
        final List<WebElement> element = getElements(locator);
        selectDropdownByText(element, visibleText);
    }

    protected void selectDropdownByText(final List<WebElement> element, final String visibleText) {
        //Finding first dropdown
        WebElement selectElement = element.stream()
                .filter(elemetTyep -> elemetTyep.getTagName().equalsIgnoreCase("select"))
                .findFirst().get();


        final Select filterSelect = new Select(selectElement);
        waitForDropdownItems(selectElement);
        final long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < 10000) {
            if (!filterSelect.getOptions().isEmpty()) {
                filterSelect.selectByVisibleText(visibleText);
                break;
            }
        }
    }

    /**
     * Universal checkobox selector
     * accept By definition and call a timebound selection with appropriate waitProperty
     */

    public void setCheckbox(final List<WebElement> selement, final boolean checked) {

        WebElement currentDropdownChoice = selement.get(1);
        currentDropdownChoice.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForInvisibilityOfSpinner(30);
    }

    public boolean isChecked(final WebElement element) {
        return element.isSelected();
    }

    public void setCheckbox(final By locator, final boolean checked) {
        setCheckbox(getElements(locator), checked);
    }


    protected List<WebElement> getElements(final By locator) {
        return getDriver().findElements(locator);
    }

    protected WebElement getElement(final By locator) {
        return getDriver().findElement(locator);
    }

    /*
     * All the wait conditions will be handled
     * @param locator
     */

    private void waitForElement(final By locator) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOutSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void waitForDropdownItems(final WebElement element) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForElementToBeVisible(final WebElement element) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOutSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(final WebElement element) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void genericWait(final int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replace the identification value at run time so similair object can be manage better
     */

    public By getCssSelectorWithValue(final By cssSelector, final String cssValue) {
        return By.cssSelector(cssSelector.toString().replace("By.cssSelector: ", "").replace("_CSS_VALUE_", cssValue));
    }

    public By getIdReplacingIdValue(final By id, final String idValue) {
        return By.id(id.toString().replace("By.id: ", "").replace("_ID_VALUE_", idValue));
    }

    /**
     * Handling spinner
     */

    public boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.StaleElementReferenceException
                | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void waitForSpinnerToBeGone(WebElement element, int timeout) {
        if (isElementDisplayed(element)) {
            new WebDriverWait(this.driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
        }
    }

    public void waitForInvisibilityOfSpinner(final int waitTimeOutSeconds) {
        waitUntilTrueOrTimeout(ExpectedConditions.invisibilityOfElementLocated(SPINNER), waitTimeOutSeconds);
    }

    protected <V> V waitUntilTrueOrTimeout(final ExpectedCondition<V> isTrue, final int waitTimeSeconds) {
        return new WebDriverWait(this.driver, waitTimeSeconds).ignoring(StaleElementReferenceException.class).until(isTrue);
    }

}
