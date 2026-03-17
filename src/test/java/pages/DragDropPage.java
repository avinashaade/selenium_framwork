package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class DragDropPage {

    WebDriver driver;

    public DragDropPage(WebDriver driver){
        this.driver = driver;
    }

    // Locators
    By source = By.xpath("//p[text()='Drag me to my target']");
    By target = By.xpath("//div[@id='droppable']");

    // Action
    public void performDragAndDrop(){

        WebElement src = driver.findElement(source);
        WebElement dest = driver.findElement(target);

        Actions actions = new Actions(driver);

        // Method 1 (simple)
        actions.dragAndDrop(src, dest).perform();

        // Alternative (more reliable)
        // actions.clickAndHold(src)
        //        .moveToElement(dest)
        //        .release(dest)
        //        .build()
        //        .perform();
    }

    public String getDropText(){
        return driver.findElement(target).getText();
    }
}