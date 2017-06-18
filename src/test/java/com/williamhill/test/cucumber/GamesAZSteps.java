package com.williamhill.test.cucumber;

import com.williamhill.test.model.pages.LoginDialogue;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class GamesAZSteps {

    private LoginDialogue loginDialogue = new LoginDialogue();
    private WebDriver webDriver;

    private Logger log = Logger.getLogger(this.getClass().getName());

    @Before
    public void previousSteps() throws Throwable {

        log.info("Games AZ Steps");

        loginDialogue.createDriver();
        webDriver = loginDialogue.getDriver();

        extractSiteVersion();

        extractCookie("STACK");

    }

    @Given("^The user is at 'games\\.williamhill\\.com'$")
    public void the_user_is_at_gameswilliamhillcom() throws Throwable {
        assertTrue(loginDialogue.getDriver().getCurrentUrl().equals("https://games.williamhill.com/#!/")
                && loginDialogue.getDriver().findElement(By.className("wf-join-button")).isEnabled());
    }

    @Then("^user's profile is loaded$")
    public void users_profile_is_loaded() throws Throwable {
        assertTrue(loginDialogue.isLogged());
    }

    @When("^opens the login dialogue$")
    public void opens_the_login_dialogue() throws Throwable {
        loginDialogue.openLoginDialogue();
        loginDialogue.verifyExpectedElementsAreDisplayed();
    }

    @And("^enters username$")
    public void enters_username() throws Throwable {
        loginDialogue.setUserNameParameter(System.getProperty("username"));
    }

    @And("^enters password$")
    public void enters_password() throws Throwable {
        loginDialogue.setPasswordParameter(System.getProperty("password"));
    }

    @And("^clicks Login$")
    public void clicks_login() throws Throwable {
        loginDialogue.login();
    }

    @Given("^User is logged into the lobby$")
    public void user_is_logged_into_the_lobby() throws Throwable {
        assertTrue(loginDialogue.isLogged());
    }

    @When("^click in the Tab 'A\\-Z'$")
    public void click_in_the_tab_az() throws Throwable {

        WebElement menuBarLobby = webDriver.findElement(By.className("menu-bar-list"));
        Optional<WebElement> tabAZ = menuBarLobby.findElements(By.tagName("a"))
                .stream()
                .filter(element -> element.getText().equals("A-Z"))
                .findFirst();

        if (tabAZ.isPresent()) tabAZ.get().click();
        else log.info("Tab A-Z not found!");

    }

    @Then("^shows the list of games$")
    public void shows_the_list_of_games() throws Throwable {
        int numGames = 1;
        showAndCountGames(numGames);
    }

    private void showAndCountGames(int numGames) {
        List<WebElement> games;

        By tileImageSelector = By.cssSelector(".games-container .game-tile img");

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 3);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(tileImageSelector, numGames));

            games = webDriver.findElements(tileImageSelector);
            int loaded = games.size();
            log.info(loaded + " games loaded...");

            showAndCountGames(loaded);
        } catch (TimeoutException ex) {
            games = webDriver.findElements(tileImageSelector);
            for (WebElement element : games.subList(numGames - 1, games.size() - 1)) {
                log.info(element.getAttribute("alt"));
            }
            log.info("Total Nº Games = " + numGames);
        }
    }

    private void extractCookie(String cookieName) {
        try {
            Cookie stackCookie = webDriver.manage().getCookieNamed(cookieName);
            if (stackCookie == null)
                log.warning(cookieName + " not found!");
            else
                log.info(cookieName + " " + stackCookie);
        } catch (WebDriverException e) {
            log.severe(cookieName + " : " + e.getMessage());
        }
    }

    private void extractSiteVersion() {
        WebElement siteVersion = webDriver.findElement(By.cssSelector("link[rel=\"stylesheet\"]"));

        if (siteVersion == null)
            log.warning("Site version not found!");
        else {
            String[] partsHref = siteVersion.getAttribute("href").split("/");
            log.info("Site version = " + partsHref[4]);
        }
    }

    @After
    private void closeDriver() {
        webDriver.quit();
    }

}
