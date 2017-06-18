package com.williamhill.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class WebDriverRunner {

    /*               Supported Browsers             */
    private static final String BROWSER_FIREFOX = "FIREFOX";
    private static final String BROWSER_CHROME = "CHROME";
    private static final String BROWSER_IE = "INTERNETEXPLORER";
    private static final String BROWSER_SAFARI = "SAFARI";

    private static WebDriver driver = null;
    private static WebDriverRunner instance = null;


    public static WebDriverRunner getInstance() {
        if (instance == null) {
            instance = new WebDriverRunner();
            instance.addShutDownHook();
        }
        return instance;
    }

    public WebDriver getDriver(String browserType) throws IOException {
        if (driver == null) {
            driver = RunDriver(browserType);
        }
        return driver;
    }

    private void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (driver != null) {
                    driver.quit();
                    driver = null;
                }
            }
        });
    }

    private WebDriver RunDriver(String browserType) throws MalformedURLException {
        return startEmbeddedWebDriver(browserType);
    }

    private WebDriver startEmbeddedWebDriver(String browserType) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        String osName = System.getProperty("os.name");
        System.out.println("Operating System = " + osName);
        URL path;
        switch (browserType.toUpperCase()) {
            case BROWSER_FIREFOX:
                path = loader.getResource(Webdriver.FIREFOX.getCorrectWebdriver(osName));
                System.setProperty("webdriver.gecko.driver", path.getPath());
                return new FirefoxDriver();
            case BROWSER_CHROME:
                path = loader.getResource(Webdriver.CHROME.getCorrectWebdriver(osName));
                System.setProperty("webdriver.chrome.driver", path.getPath());
                return new ChromeDriver();
            case BROWSER_IE:
                path = loader.getResource(Webdriver.IE.getWindows());
                System.setProperty("webdriver.ie.driver", path.getPath());
                return new InternetExplorerDriver();
            case BROWSER_SAFARI:
                path = loader.getResource(Webdriver.SAFARI.getMac());
                System.setProperty("webdriver.safari.driver", path.getPath());
                return new SafariDriver();
            default:
                String errorMessage = String.format(
                        "browserType: <%s> was not recognized as supported browser.", browserType);
                throw new IllegalArgumentException(errorMessage);
        }
    }
}
