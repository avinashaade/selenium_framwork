package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        }

)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false) // keep false for now
    public Object[][] scenarios() {
        return super.scenarios();
    }
}