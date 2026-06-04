package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import utils.ConfigReader;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {

        String browser = ConfigReader.getProperty("browser");

        driver = DriverFactory.initDriver(browser);

        driver.get(ConfigReader.getProperty("url"));
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            TakesScreenshot ts = (TakesScreenshot) driver;

            byte[] screenshot =
                    ts.getScreenshotAs(OutputType.BYTES);

            // Cucumber Report
            scenario.attach(
                    screenshot,
                    "image/png",
                    "Failure Screenshot"
            );

            // Allure Report
            Allure.addAttachment(
                    "Failure Screenshot",
                    new ByteArrayInputStream(screenshot)
            );
        }

        DriverFactory.quitDriver();
    }
}