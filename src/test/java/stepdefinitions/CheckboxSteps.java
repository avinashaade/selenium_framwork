package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;

public class CheckboxSteps {

    HomePage home;

    public CheckboxSteps(){
        home = new HomePage(Hooks.driver);
    }

    @When("User selects {string} and {string} checkboxes")
    public void user_selects_checkboxes(String day1, String day2) {
        home.selectCheckbox(day1);
        home.selectCheckbox(day2);
    }

    @Then("{string} and {string} checkboxes should be selected")
    public void verify_checkboxes(String day1, String day2) {
        Assert.assertTrue(home.isCheckboxSelected(day1));
        Assert.assertTrue(home.isCheckboxSelected(day2));
    }
}