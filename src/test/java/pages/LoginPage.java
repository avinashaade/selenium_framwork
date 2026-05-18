package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){

        super(driver);
    }

    // Locators
    By username = By.id("username");

    By password = By.id("password");

    By submitBtn = By.id("submit");

    By successMsg = By.xpath("//h1");

    // Page methods
    public void enterUsername(String user){

        sendKeys(username, user);
    }

    public void enterPassword(String pass){

        sendKeys(password, pass);
    }

    public void clickSubmit(){

        click(submitBtn);
    }

    public String getSuccessMessage(){

        return getText(successMsg);
    }
}