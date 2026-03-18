package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.DragDropPage;

public class DragDropSteps {

    DragDropPage dragDropPage;

    public DragDropSteps(){
        dragDropPage = new DragDropPage(Hooks.driver);
    }

    @When("User performs drag and drop")
    public void user_performs_drag_and_drop() {
        dragDropPage.performDragAndDrop();
    }

    @Then("Element should be dropped successfully")
    public void element_should_be_dropped_successfully() {
        Assert.assertEquals(dragDropPage.getDropText(), "Dropped!");
    }
}