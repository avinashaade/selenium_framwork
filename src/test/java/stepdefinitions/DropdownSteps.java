package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;

public class DropdownSteps {

    HomePage home;

    public DropdownSteps(){
        home = new HomePage(Hooks.driver);
    }

    @When("User selects country {string}")
    public void user_selects_country(String country) {
        home.selectCountry(country);
    }

    @Then("country should be selected as {string}")
    public void country_should_be_selected_as(String expected) {
        Assert.assertEquals(home.getSelectedCountry(), expected);
    }
}