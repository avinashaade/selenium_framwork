package stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;
import hooks.Hooks;

public class DropdownSteps {

    HomePage home = new HomePage(Hooks.driver);

    @When("user selects country {string}")
    public void user_selects_country(String country) {
        home.selectCountry(country);
    }

    @Then("country should be selected as {string}")
    public void country_should_be_selected_as(String expected) {
        Assert.assertEquals(home.getSelectedCountry(), expected);
    }
}