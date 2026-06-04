package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import utils.ConfigReader;
import utils.DriverFactory;

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

            scenario.attach(
                    screenshot,
                    "image/png",
                    "Failure Screenshot");
        }

        DriverFactory.quitDriver();
    }
}