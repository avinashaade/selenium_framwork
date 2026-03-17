package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import pages.HomePage;

public class CommonSteps {

    HomePage home = new HomePage(Hooks.driver);

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        home.openSite();
    }
}