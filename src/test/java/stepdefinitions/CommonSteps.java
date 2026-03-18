package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import pages.HomePage;

public class CommonSteps {

    HomePage home;

    public CommonSteps(){
        home = new HomePage(Hooks.driver);
    }

    @Given("User opens the application")
    public void user_opens_the_application() {
        home.openSite();
    }
}