package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.AlertPage;

public class AlertSteps {

    HomePage home = new HomePage(Hooks.driver);
    AlertPage alertPage = new AlertPage(Hooks.driver);

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        home.openSite();
    }

    @When("User clicks on {string} button")
    public void user_clicks_on_button(String button) {

        switch (button) {
            case "Simple Alert":
                alertPage.clickSimpleAlert();
                break;
            case "Confirmation Alert":
                alertPage.clickConfirmAlert();
                break;
            case "Prompt Alert":
                alertPage.clickPromptAlert();
                break;
            default:
                throw new IllegalArgumentException("Invalid button: " + button);
        }
    }

    @Then("Alert should be displayed")
    public void alert_should_be_displayed() {
        String text = alertPage.getAlertText();
        Assert.assertTrue(text != null && !text.isEmpty());
    }

    @Then("User should see alert message {string}")
    public void user_should_see_alert_message(String expected) {
        Assert.assertEquals(alertPage.getAlertText(), expected);
    }

    @When("User accepts the alert")
    public void user_accepts_the_alert() {
        alertPage.acceptAlert();
    }

    @When("User dismisses the alert")
    public void user_dismisses_the_alert() {
        alertPage.dismissAlert();
    }

    @When("User enters {string} in the prompt")
    public void user_enters_in_the_prompt(String text) {
        alertPage.enterTextInAlert(text);
    }

    @Then("Result message should be {string}")
    public void result_message_should_be(String expected) {
        Assert.assertEquals(alertPage.getResultMessage(), expected);
    }

    @Then("Result message should contain {string}")
    public void result_message_should_contain(String expected) {
        Assert.assertTrue(alertPage.getResultMessage().contains(expected));
    }
}