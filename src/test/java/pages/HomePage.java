package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By name = By.id("name");
    By email = By.id("email");
    By countryDropdown = By.id("country");


    // Actions
    public void openSite() {
        driver.get("https://testautomationpractice.blogspot.com/");
    }

    public void enterName(String username) {
        driver.findElement(name).sendKeys(username);
    }

    public void enterEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }

    public void selectCountry(String country) {
        Select dropdown = new Select(driver.findElement(countryDropdown));
        dropdown.selectByVisibleText(country);
    }

    public String getSelectedCountry() {
        Select dropdown = new Select(driver.findElement(countryDropdown));
        return dropdown.getFirstSelectedOption().getText();
    }
}