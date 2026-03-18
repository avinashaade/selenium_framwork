package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class DragDropPage extends BasePage {

    public DragDropPage(WebDriver driver){
        super(driver);
    }

    By source = By.xpath("//p[text()='Drag me to my target']");
    By target = By.id("droppable");

    public void performDragAndDrop(){

        WebElement src = waitForElement(source);
        WebElement dest = waitForElement(target);

        Actions actions = new Actions(driver);

        actions.clickAndHold(src)
                .moveToElement(dest)
                .release(dest)
                .build()
                .perform();
    }

    public String getDropText(){
        return getText(target);
    }
}