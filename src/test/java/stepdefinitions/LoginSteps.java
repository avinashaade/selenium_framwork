package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    public LoginSteps(){

        loginPage = new LoginPage(Hooks.driver);
    }

    @Given("I am on the login page")
    public void open_login_page(){

        Hooks.driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("I enter valid username and password")
    public void enter_credentials(){

        loginPage.enterUsername("student");

        loginPage.enterPassword("Password123");

        loginPage.clickSubmit();
    }

    @Then("I should be redirected to the dashboard")
    public void verify_dashboard(){

        String message = loginPage.getSuccessMessage();

        Assert.assertTrue(message.contains("Logged In Successfully"));
    }
}