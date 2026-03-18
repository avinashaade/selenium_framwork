package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;

public class FormSteps {

    HomePage home;

    public FormSteps(){
        home = new HomePage(Hooks.driver);
    }

    @When("User enters name {string}")
    public void enterName(String name) {
        home.enterName(name);
    }

    @When("User enters email {string}")
    public void enterEmail(String email) {
        home.enterEmail(email);
    }

    @Then("Name should be {string}")
    public void validateName(String expected) {
        Assert.assertEquals(home.getEnteredName(), expected);
    }

    @Then("Email should be {string}")
    public void validateEmail(String expected) {
        Assert.assertEquals(home.getEnteredEmail(), expected);
    }
}