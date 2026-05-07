package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DragDropPage extends BasePage {

    public DragDropPage(WebDriver driver){
        super(driver);
    }

    By source = By.xpath("//p[text()='Drag me to my target']");
    By target = By.id("droppable");
    By disabledDragElement = By.id("draggable-disabled");
    By invalidDropZone = By.id("invalid-drop");
    By dropDefaultText = By.id("dropDefaultText");

    // POSITIVE TEST - Original method
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

    // ==================== NEGATIVE TEST METHODS ====================

    /**
     * Drag element but don't release - cancels the drag using smaller safe offsets
     * This simulates a user starting to drag but canceling before completion
     */
    public void dragElementWithoutRelease(){
        WebElement src = waitForElement(source);

        // Store original position before drag
        int originalX = src.getLocation().getX();
        int originalY = src.getLocation().getY();

        Actions actions = new Actions(driver);
        try {
            // Use smaller, safer offsets (20 pixels instead of 50)
            actions.clickAndHold(src)
                    .moveByOffset(20, 20)
                    .build()
                    .perform();
            // Small pause to simulate user holding
            Thread.sleep(300);
            // Release without completing the drag
            actions.release().perform();
        } catch (WebDriverException e) {
            // If offset causes out of bounds, release and handle
            try {
                actions.release().perform();
            } catch (Exception ex) {
                System.out.println("Already released or error during release: " + ex.getMessage());
            }
            throw new RuntimeException("Drag offset out of bounds. Consider using smaller offsets.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get the original position of the source element
     */
    public String getSourceElementPosition(){
        WebElement src = waitForElement(source);
        return src.getCssValue("position");
    }

    /**
     * Verify element returns to original position
     */
    public boolean isElementInOriginalPosition(){
        try {
            WebElement src = waitForElement(source);
            String transform = src.getCssValue("transform");
            // If transform is none or empty, element is in original position
            boolean isOriginal = transform.equals("none") || transform.isEmpty() || transform.equals("matrix(1, 0, 0, 1, 0, 0)");
            return isOriginal;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the default text from drop target
     */
    public String getTargetDefaultText(){
        try {
            WebElement defaultText = wait.until(ExpectedConditions.visibilityOfElementLocated(dropDefaultText));
            return defaultText.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Drag element outside the target zone with safe offset handling
     */
    public void dragElementOutsideTarget(){
        WebElement src = waitForElement(source);

        try {
            Actions actions = new Actions(driver);

            // Get current position and calculate safe boundaries
            int elementX = src.getLocation().getX();
            int elementY = src.getLocation().getY();
            int elementWidth = src.getSize().getWidth();
            int elementHeight = src.getSize().getHeight();

            // Calculate offset that moves element outside but still within window
            // Use smaller offsets to avoid out of bounds
            int offsetX = 25;
            int offsetY = 25;

            actions.clickAndHold(src)
                    .moveByOffset(offsetX, offsetY)
                    .release()
                    .build()
                    .perform();

        } catch (WebDriverException e) {
            // Fallback: try to move to bottom right corner with even smaller offset
            try {
                Actions actions = new Actions(driver);
                actions.clickAndHold(src)
                        .moveByOffset(10, 10)  // Even smaller offset
                        .release()
                        .build()
                        .perform();
            } catch (Exception ex) {
                throw new RuntimeException("Cannot drag outside target - element too close to window edge", ex);
            }
        }
    }

    /**
     * Get the original location of source element
     */
    public String getSourceElementOriginalLocation(){
        WebElement src = waitForElement(source);
        int x = src.getLocation().getX();
        int y = src.getLocation().getY();
        return x + "," + y;
    }

    /**
     * Check if source element is still at original location
     */
    public boolean isSourceAtOriginalLocation(){
        try {
            WebElement src = waitForElement(source);
            // Check if element has minimal transformation
            String transform = src.getCssValue("transform");
            return transform.equals("none") || transform.isEmpty() || transform.equals("matrix(1, 0, 0, 1, 0, 0)");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Attempt to drag disabled element
     */
    public void attemptDragDisabledElement(){
        try {
            WebElement disabledElement = waitForElement(disabledDragElement);
            WebElement dest = waitForElement(target);

            if (!disabledElement.isEnabled()) {
                throw new IllegalStateException("Element is disabled");
            }

            Actions actions = new Actions(driver);
            actions.clickAndHold(disabledElement)
                    .moveToElement(dest)
                    .release(dest)
                    .build()
                    .perform();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Element is disabled or not found");
        }
    }

    /**
     * Drag element on invalid drop zone
     */
    public void dragElementOnInvalidZone(){
        try {
            WebElement src = waitForElement(source);
            WebElement invalidZone = waitForElement(invalidDropZone);

            Actions actions = new Actions(driver);
            actions.clickAndHold(src)
                    .moveToElement(invalidZone)
                    .release(invalidZone)
                    .build()
                    .perform();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Invalid drop zone not found", e);
        }
    }

    /**
     * Perform drag and drop operation N times with proper error handling
     */
    public void performDragAndDropNTimes(int times) throws Exception {
        for (int i = 0; i < times; i++) {
            try {
                performDragAndDrop();
                Thread.sleep(500); // Small delay between operations
            } catch (Exception e) {
                throw new Exception("Failed on iteration " + (i + 1) + ": " + e.getMessage(), e);
            }
        }
    }

    /**
     * Hide the target drop zone
     */
    public void hideTargetDropZone(){
        try {
            WebElement target = waitForElement(this.target);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", target);
        } catch (Exception e) {
            throw new RuntimeException("Failed to hide target", e);
        }
    }

    /**
     * Verify if target is visible
     */
    public boolean isTargetVisible(){
        try {
            WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(this.target));
            return target.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Remove the source element from DOM
     */
    public void removeSourceElement(){
        try {
            WebElement src = driver.findElement(source);
            ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", src);
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove source element", e);
        }
    }

    /**
     * Check if source element exists in DOM
     */
    public boolean isSourceElementPresent(){
        try {
            driver.findElement(source);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Press Escape key to cancel drag
     */
    public void cancelDragWithEscapeKey(){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    /**
     * Drag element and drop on itself (same source)
     */
    public void dragElementOnItself(){
        WebElement src = waitForElement(source);
        Actions actions = new Actions(driver);

        try {
            actions.clickAndHold(src)
                    .moveToElement(src)  // Move to same element
                    .release(src)
                    .build()
                    .perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to drag element on itself", e);
        }
    }

    /**
     * Get element's current CSS position value
     */
    public String getElementCSSPosition(){
        WebElement src = waitForElement(source);
        return src.getCssValue("position");
    }

    /**
     * Verify drop target text after operation
     */
    public String getCurrentDropTargetText(){
        try {
            WebElement dropTarget = waitForElement(target);
            return dropTarget.getText();
        } catch (Exception e) {
            return "Target not found";
        }
    }

    /**
     * Wait and verify if error message appears
     */
    public String getErrorMessage(){
        try {
            By errorLocator = By.id("error-message");
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
            return error.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Restore hidden target (for test cleanup)
     */
    public void restoreTargetDropZone(){
        try {
            WebElement target = driver.findElement(this.target);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", target);
        } catch (Exception e) {
            throw new RuntimeException("Failed to restore target", e);
        }
    }

    /**
     * Get element's initial state before any drag operation
     */
    public String captureElementState(){
        WebElement src = waitForElement(source);
        return src.getAttribute("style");
    }

    /**
     * Verify element state hasn't changed
     */
    public boolean hasElementStateChanged(String originalState){
        WebElement src = waitForElement(source);
        String currentState = src.getAttribute("style");
        return !originalState.equals(currentState);
    }
}
