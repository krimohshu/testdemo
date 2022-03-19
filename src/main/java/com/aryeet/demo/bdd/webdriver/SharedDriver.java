package com.aryeet.demo.bdd.webdriver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Krishan Shukla .
 */

@Component
public class SharedDriver extends EventFiringWebDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SharedDriver.class);
    //KMS - Change to RemoteDriver when you wish to run remotely on grid...
    private static RemoteWebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            quitGlobalInstance();
        }
    };
    //BROWSERSTACK SETTINGS - Please pass "browserStack_krishan" in getRealDriver()
    public static final String USERNAME = "krishanshukla2";
    public static final String AUTOMATE_KEY = "XXX";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Autowired
    public SharedDriver(@Value("${ui.acceptance.test.selenium.default.browser}") String defaultBrowser,
                        @Value("${ui.acceptance.test.selenium.browser.version}") String browserVersion,
                        @Value("${ui.acceptance.test.selenium.default.hub.url}") String defaultHubUrl,
                        @Value("${ui.acceptance.test.selenium.wait.timeout.seconds}") String waitTimeOutSeconds,
                        @Value("${ui.acceptance.test.selenium.browser.browserstack}") String browserStack,
                        @Value("${path.chrome.driver}") String chromeDriverPath,
                        BrowserStackProperties browserStackProperties) throws MalformedURLException {

        super(getRealDriver("", browserVersion, defaultHubUrl, Integer.valueOf(waitTimeOutSeconds), Boolean.valueOf(browserStack), chromeDriverPath, browserStackProperties));
       // super(getRealDriver("browserStack_krishan", browserVersion, defaultHubUrl, Integer.valueOf(waitTimeOutSeconds), Boolean.valueOf(browserStack), chromeDriverPath, browserStackProperties));
        Runtime.getRuntime().addShutdownHook (CLOSE_THREAD);
    }

    private static void quitGlobalInstance() {
        final WebDriver driver = REAL_DRIVER;
        REAL_DRIVER = null;
        if (driver != null) {
            driver.quit();
        }
    }

    private static WebDriver getRealDriver(final String defaultBrowser,
                                           final String browserVersion,
                                           final String defaultHubUrl,
                                           final int waitTimeOutSeconds,
                                           final boolean browserStack,
                                           final String chromeDriverPath,
                                           final BrowserStackProperties browserStackProperties)
            throws MalformedURLException {
        if (REAL_DRIVER == null) {
            if (browserStack) {
                REAL_DRIVER = getBrowserStackDriver(browserStackProperties);
            } else {
                switch (defaultBrowser) {
                    case "browserStack_krishan":
                       /* REAL_DRIVER = getFirefoxDriver(defaultHubUrl);
                        REAL_DRIVER.setFileDetector(new LocalFileDetector());*/
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("browser", "Chrome");
                        caps.setCapability("browser_version", "80.0");
                        caps.setCapability("os", "OS X");
                        caps.setCapability("os_version", "Mojave");
                        caps.setCapability("resolution", "1024x768");
                        caps.setCapability("name", "Bstack-[Java] Sample Test");

                        REAL_DRIVER = new RemoteWebDriver(new URL(URL), caps);

                        break;

                        case "firefox":
                        REAL_DRIVER = getFirefoxDriver(defaultHubUrl);
                        REAL_DRIVER.setFileDetector(new LocalFileDetector());
                        break;
                    case "chrome":
                        REAL_DRIVER = getChromeDriver(defaultHubUrl);
                        REAL_DRIVER.setFileDetector(new LocalFileDetector());
                        break;
                    case "ie":
                        REAL_DRIVER = getIEDriver(browserVersion, defaultHubUrl);
                        REAL_DRIVER.setFileDetector(new LocalFileDetector());
                        break;
                    default:
                        // System.out.println("Using default browser");
                        System.out.println("Using default browser");
                        ChromeOptions options = new ChromeOptions();
                        options.setCapability("UNEXPECTED_ALERT_BEHAVIOUR", "ACCEPT");
                        options.addArguments("--headless");
                        options.setCapability("unexpectedAlertBehaviour", "accept");
                        options.setCapability("CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR", "ACCEPT");
                        options.setCapability("UnexpectedAlertBehaviour", "ACCEPT");
                        options.addArguments("start-maximized");
                        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                        REAL_DRIVER  = new ChromeDriver(options);
                        // REAL_DRIVER.DesiredCapabilities.FIREFOX["unexpectedAlertBehaviour"] = "accept"

                        REAL_DRIVER.manage().window().maximize();

                        /*FirefoxProfile profile = new FirefoxProfile();
                        profile.setEnableNativeEvents(true);
                        REAL_DRIVER = new FirefoxDriver(profile);*/
                        break;
                }
            }
            REAL_DRIVER.manage().timeouts().implicitlyWait(waitTimeOutSeconds, TimeUnit.SECONDS);
        }
        return REAL_DRIVER;
    }
    private static RemoteWebDriver getIEDriver(final String browserVersion, final String defaultHubUrl) throws MalformedURLException {
        final DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        capability.setVersion(browserVersion);
        return new RemoteWebDriver(new URL(defaultHubUrl), capability);
    }

    private static RemoteWebDriver getChromeDriver(final String defaultHubUrl) throws MalformedURLException {
        final DesiredCapabilities capability = DesiredCapabilities.chrome();
        return new RemoteWebDriver(new URL(defaultHubUrl), capability);
    }

    private static RemoteWebDriver getFirefoxDriver(final String defaultHubUrl) throws MalformedURLException {
        final DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        return new RemoteWebDriver(new URL(defaultHubUrl), capability);
    }

    private static RemoteWebDriver getBrowserStackDriver(final BrowserStackProperties browserStackProperties) throws MalformedURLException {
        final DesiredCapabilities caps = new DesiredCapabilities();
        return new RemoteWebDriver(new URL(""), caps);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            super.close();
        }
    }

}
