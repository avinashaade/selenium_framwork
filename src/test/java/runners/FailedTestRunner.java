package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/failed_scenarios.txt",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/failed-report.html"
        }
)
public class FailedTestRunner
        extends AbstractTestNGCucumberTests {
}