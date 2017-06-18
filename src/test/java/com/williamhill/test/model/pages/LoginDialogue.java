package com.williamhill.test.model.pages;

import com.williamhill.test.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginDialogue {

    private final String url = "https://games.williamhill.com";

    private WebElement username;

    private WebElement password;

    private WebElement btnLogin;

    private WebDriver driver;

    public LoginDialogue() {
    }

    public void createDriver() throws Throwable {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) throw new Exception("Parameter browser not found!");

        driver = Browser.getDriver(browser);
        driver.get(url);
    }

    public void verifyExpectedElementsAreDisplayed() throws Throwable {

        username = getDriver().findElement(By.name("username"));
        if (!username.isDisplayed()) throw new Exception("Username!");

        password = getDriver().findElement(By.name("password"));
        if (!password.isDisplayed()) throw new Exception("Password not found!");

        WebElement buttonLogin = getDriver().findElement(By.cssSelector("button[type=submit]"));

        if (buttonLogin == null) throw new Exception("Login button not found!");
        else btnLogin = buttonLogin;

    }

    public void login() throws Throwable {
        btnLogin.click();
    }

    public void openLoginDialogue() {
        getDriver().findElement(By.className("wf-user-button__login")).click();
    }

    public void setUserNameParameter(String userNameParameter) throws Throwable {
        if (userNameParameter == null) throw new Exception("Parameter username not found!");
        else username.sendKeys(userNameParameter);
    }

    public void setPasswordParameter(String passwordParameter) throws Throwable {
        if (passwordParameter == null) throw new Exception("Parameter password not found!");
        else password.sendKeys(passwordParameter);
    }

    public Boolean isLogged() {
        Boolean logged = true;
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.className("wf-user-button__balance")));
        } catch (Exception e) {
            logged = false;
        }
        return logged;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
