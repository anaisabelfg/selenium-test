package com.williamhill.test;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Browser {

    private static WebDriverRunner webDriverRunner = null;

    private Browser() {
    }

    public static WebDriver getDriver(String browserType) {
        if (webDriverRunner == null) {
            webDriverRunner = WebDriverRunner.getInstance();
        }

        WebDriver driver = null;
        try {
            driver = webDriverRunner.getDriver(browserType);
        } catch (IOException ex) {
            Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return driver;
    }
}
