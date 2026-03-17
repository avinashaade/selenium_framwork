package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import hooks.Hooks;

public class FormSteps {

    HomePage home = new HomePage(Hooks.driver);

    @Given("user opens website")
    public void openWebsite() {
        home.openSite();
    }

    @When("user enters name {string}")
    public void enterName(String name) {
        home.enterName(name);
    }

    @When("user enters email {string}")
    public void enterEmail(String email) {
        home.enterEmail(email);
    }
}