package feature.commons;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(
		features={"src/test/resources/feature/commons"},
		plugin = {"pretty",
				"html:target/cucumber-html-report",
				"junit:target/cucumber-junit-report/allcukes.xml"},
		tags = {"@Runme"},
        glue = {"feature.commons"}        
)

@RunWith(Cucumber.class)
public class RunnerCommons {

}
