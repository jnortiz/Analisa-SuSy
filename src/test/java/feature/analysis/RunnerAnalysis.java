package feature.analysis;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(
		features={"src/test/resources/feature/analysis"},
		glue={"cukes"},
		plugin = {"pretty",
				"html:target/cucumber-html-report",
				"junit:target/cucumber-junit-report/allcukes.xml"},
		tags = {"@Runme"}
)

@RunWith(Cucumber.class)
public class RunnerAnalysis
{

}
