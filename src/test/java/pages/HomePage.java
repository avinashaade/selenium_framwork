package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By name = By.id("name");
    By email = By.id("email");
    By countryDropdown = By.id("country");

    // Checkboxes
    By mondayCheckbox = By.id("monday");
    By sundayCheckbox = By.id("sunday");

    // Actions
    public void openSite() {
        driver.get("https://testautomationpractice.blogspot.com/");
    }

    public void enterName(String username) {
        sendKeys(name, username);
    }

    public void enterEmail(String mail) {
        sendKeys(email, mail);
    }

    public void selectCountry(String country) {
        Select dropdown = new Select(waitForElement(countryDropdown));
        dropdown.selectByVisibleText(country);
    }

    public String getSelectedCountry() {
        Select dropdown = new Select(waitForElement(countryDropdown));
        return dropdown.getFirstSelectedOption().getText();
    }

    public String getEnteredName(){
        return driver.findElement(name).getAttribute("value");
    }

    public String getEnteredEmail(){
        return driver.findElement(email).getAttribute("value");
    }

    // Checkbox Methods
    public void selectCheckbox(String day) {

        By locator;

        switch (day.toLowerCase()) {
            case "monday":
                locator = mondayCheckbox;
                break;
            case "sunday":
                locator = sundayCheckbox;
                break;
            default:
                throw new IllegalArgumentException("Invalid day: " + day);
        }

        WebElement checkbox = waitForElement(locator);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isCheckboxSelected(String day) {

        By locator;

        switch (day.toLowerCase()) {
            case "monday":
                locator = mondayCheckbox;
                break;
            case "sunday":
                locator = sundayCheckbox;
                break;
            default:
                throw new IllegalArgumentException("Invalid day: " + day);
        }

        return driver.findElement(locator).isSelected();
    }
}