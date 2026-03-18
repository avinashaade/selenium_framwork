package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator){
        waitForElement(locator).click();
    }

    public void sendKeys(By locator, String text){
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator){
        return waitForElement(locator).getText();
    }
}