package com.aryeet.demo.bdd.pages;

import com.aryeet.model.ImportantFeature;
import com.aryeet.model.TVInfoCard;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TvInfoCardPage extends AbstractPageObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvInfoCardPage.class);

    private static final By TV_PRODUCT_CARD_ITEM = By.cssSelector("div[data-which-id='product-card']");

    private static final By TV_MANUFACTURER = By.cssSelector("span[itemprop='manufacturer']");
    private static final By TV_MODEL = By.cssSelector("span[itemprop='model']");
    private static final By PRODUCT_IMAGE = By.cssSelector("img[data-test-element='product-image']");
    private static final By PRODUCT_SCORE_LABEL = By.cssSelector("span[data-test-element='product-score-label']");
    private static final By PRODUCT_SCORE_VALUE = By.cssSelector("span[data-test-element='product-score-value']");
    private static final By PRODUCT_PERCENTAGE = By.cssSelector("g[fill='none']");
    private static final By TESTEDDATE = By.cssSelector("p[data-test-element='tested-date']");
    private static final By IMPORTANT_FEATURE = By.cssSelector("span[data-which-id='important-feature']");
    // private static final By PRODUCT_AMOUNT = By.cssSelector("p[data-test-element='product-price']");
    //  tv.findElement(By.xpath(".//*")).findElement(By.className("_3FpyT")).getText()
    private static final By PRODUCT_AMOUNT = By.className("_3FpyT");
    private static final By VIEW_RETAILER = By.className("_3wctv");
    private static final By COMPARE = By.className("_1pGtD");
    private static final By COMPARECHECKBOX = By.className("h47Xt");


    //private static final By DATA_WHICH_BUTTON_SCREENTYPE = By.cssSelector("button[data-which-button='screen_type-filter']");

    Map<Integer, TVInfoCard> tvInfoCardMap = new LinkedHashMap();


    public static final String PATH = "/";

    public TvInfoCardPage(final String path, final WebDriver driver, final int waitTimeOutSeconds) {
        super(path + PATH, driver, waitTimeOutSeconds);
    }

    public Map<Integer, TVInfoCard> getAllTVReviewCard() {
        driver.navigate().refresh();
        genericWait(5);

        //Get all the information of all the product - later overload this method with constraint parameter
        List<WebElement> elementsAllTVProductCard = driver.findElements(TV_PRODUCT_CARD_ITEM);
        AtomicInteger atomicCounter = new AtomicInteger(0);

        elementsAllTVProductCard.stream()
                .forEach(tv -> {
                    TVInfoCard tvInfoCard = new TVInfoCard();
                    try {
                        tvInfoCard.setManufacturer(tv.findElement(By.xpath(".//*")).findElement(TV_MANUFACTURER).getText());
                        tvInfoCard.setModel(tv.findElement(By.xpath(".//*")).findElement(TV_MODEL).getText());
                        genericWait(1);
                        tvInfoCard.setProductImage(tv.findElement(By.xpath(".//*")).findElement(PRODUCT_IMAGE).getAttribute("src"));

                        tvInfoCard.setProductScoreValue(tv.findElement(By.xpath(".//*")).findElement(PRODUCT_SCORE_VALUE).getText());
                        tvInfoCard.setProductScoreLabel(tv.findElement(By.xpath(".//*")).findElement(PRODUCT_SCORE_LABEL).getText());

                        tvInfoCard.setProductpercentage(tv.findElement(By.xpath(".//*")).findElement(PRODUCT_PERCENTAGE).getAttribute("fill-rule"));
                        tvInfoCard.setReviewDate(tv.findElement(By.xpath(".//*")).findElement(TESTEDDATE).getText());

                        tvInfoCard.setImportantFeature(new ImportantFeature(
                                tv.findElement(By.xpath(".//*")).findElements(IMPORTANT_FEATURE).get(0).getText(),
                                tv.findElement(By.xpath(".//*")).findElements(IMPORTANT_FEATURE).get(1).getText(),
                                tv.findElement(By.xpath(".//*")).findElements(IMPORTANT_FEATURE).get(2).getText()
                        ));

//                        tvInfoCard.setProductAmount(tv.findElement(By.xpath(".//*")).findElement(PRODUCT_AMOUNT).getText());
//                        tvInfoCard.setViewRetailer(tv.findElement(By.xpath(".//*")).findElement(VIEW_RETAILER).getText());
//                        tvInfoCard.setCompareText(tv.findElement(By.xpath(".//*")).findElement(COMPARE).getText());
//                        tvInfoCard.setCompareCheckBox(tv.findElement(By.xpath(".//*")).findElement(COMPARECHECKBOX).isSelected());
                    } catch (Exception nse) {
                        LOGGER.info("error to locate tv review product" + atomicCounter.get() + tvInfoCard.toString());
                    } finally {

                        tvInfoCardMap.put(atomicCounter.incrementAndGet(), tvInfoCard);
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        if (atomicCounter.intValue() % 5 == 0) {
                            js.executeScript("document.title = 'Scroller height debugging [" + atomicCounter.intValue() + "]'");
                            switch (atomicCounter.intValue()) {
                                case 5:
                                    js.executeScript("window.scrollBy(0,300)");
                                    break;
                                case 10:
                                    js.executeScript("window.scrollBy(0,500)");
                                    break;
                                case 15:
                                    js.executeScript("window.scrollBy(0,750)");
                                    break;
                                case 20:
                                    js.executeScript("window.scrollBy(0,950)");
                                    break;
                                case 25:
                                    js.executeScript("window.scrollBy(0,1200)");
                                    break;
                                case 30:
                                    js.executeScript("window.scrollBy(0,1300)");
                                    break;
                                case 35:
                                    js.executeScript("window.scrollBy(0,1500)");
                                    break;
                                default:

                            }

                            genericWait(1);
                        }

                    }
                });

        return tvInfoCardMap;
    }

}
