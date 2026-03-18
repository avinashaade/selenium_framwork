package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AlertPage;

public class AlertSteps {

    AlertPage alertPage;

    public AlertSteps(){
        alertPage = new AlertPage(Hooks.driver);
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
        Assert.assertTrue(alertPage.getAlertText().length() > 0);
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