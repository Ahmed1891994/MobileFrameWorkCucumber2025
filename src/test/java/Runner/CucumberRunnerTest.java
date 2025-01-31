package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        glue = "StepDefinition", // Package containing step definitions
        features = "src/test/resources/Feature", // Path to feature files
        plugin = {
                "pretty", // Pretty formatting for console output
                "summary", // Summary of test results
                "html:target/cucumber/report.html", // HTML report
                "json:target/cucumber/report.json", // JSON report
                "junit:target/cucumber/report.xml" // JUnit XML report
        }
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    private static final Logger logger = LoggerFactory.getLogger(CucumberRunnerTest.class);

    /**
     * Overrides the scenarios method to enable parallel execution of Cucumber scenarios.
     *
     * @return A 2D array of scenarios for parallel execution.
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        logger.info("Initializing Cucumber runner for parallel execution");
        Object[][] scenarios = super.scenarios();
        logger.debug("Number of scenarios to execute: {}", scenarios.length);
        return scenarios;
    }
}