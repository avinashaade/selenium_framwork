package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AlertPage {

    WebDriver driver;

    public AlertPage(WebDriver driver){
        this.driver = driver;
    }

    // ✅ Correct Locators
    By simpleAlertBtn = By.xpath("//button[text()='Simple Alert']");
    By confirmAlertBtn = By.xpath("//button[text()='Confirmation Alert']");
    By promptAlertBtn = By.xpath("//button[text()='Prompt Alert']");
    By resultText = By.id("demo");

    // Actions
    public void clickSimpleAlert(){
        driver.findElement(simpleAlertBtn).click();
    }

    public void clickConfirmAlert(){
        driver.findElement(confirmAlertBtn).click();
    }

    public void clickPromptAlert(){
        driver.findElement(promptAlertBtn).click();
    }

    // Alert handling
    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    public void enterTextInAlert(String text){
        driver.switchTo().alert().sendKeys(text);
    }


    public String getResultMessage(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(resultText)
        ).getText();
    }
}