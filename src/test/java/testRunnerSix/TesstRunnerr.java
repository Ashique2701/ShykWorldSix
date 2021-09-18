package testRunnerSix;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = { "src/test/resources/Features" }, plugin = {
		"json:target/cucumber.json" }, glue = "steepdefination")

//tags= {""})
public class TesstRunnerr extends AbstractTestNGCucumberTests {

}
