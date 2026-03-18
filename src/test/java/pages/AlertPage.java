package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class AlertPage extends BasePage {

    public AlertPage(WebDriver driver){
        super(driver);
    }

    By simpleAlertBtn = By.xpath("//button[text()='Simple Alert']");
    By confirmAlertBtn = By.xpath("//button[text()='Confirmation Alert']");
    By promptAlertBtn = By.xpath("//button[text()='Prompt Alert']");
    By resultText = By.id("demo");

    public void clickSimpleAlert(){
        click(simpleAlertBtn);
    }

    public void clickConfirmAlert(){
        click(confirmAlertBtn);
    }

    public void clickPromptAlert(){
        click(promptAlertBtn);
    }

    public Alert waitForAlert(){
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText(){
        return waitForAlert().getText();
    }

    public void acceptAlert(){
        waitForAlert().accept();
    }

    public void dismissAlert(){
        waitForAlert().dismiss();
    }

    public void enterTextInAlert(String text){
        waitForAlert().sendKeys(text);
    }

    public String getResultMessage(){
        return getText(resultText);
    }
}