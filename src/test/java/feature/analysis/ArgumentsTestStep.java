package feature.analysis;

import org.junit.Assert;

import com.ic.analisaSusy.commons.CLInterface;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ArgumentsTestStep {
	
	private String[] arguments; 
	private CLInterface aCLI;
    
	@Given("^The correct arguments as input for Analisa-SuSy$")
	public void correctArgumentsAsInput() {
		arguments = new String[2];
		arguments[0] = "-f";
		arguments[1] = "overview.c";
		Assert.assertTrue(arguments.length == 2);
	}
	
	@When("^SuSy calls Analisa-SuSy$")
	public void suSyCallsAnalisaSuSy() {
		aCLI = new CLInterface(arguments);
		Assert.assertTrue(aCLI.getFilepaths().size() == 0);
	}	
	
	@Then("^Analisa-SuSy won't return an error message$")
	public void analisaSuSyWontReturnErrorMessage() {
		aCLI.parse();
		Assert.assertTrue(aCLI.getFilepaths().size() > 0);
	}
	
	@Given("^0 arguments as input for Analisa-SuSy$")
	public void zeroArgumentsAsInput() {
		arguments = new String[0];
		Assert.assertTrue(arguments.length == 0);
	}
	
	@When("^SuSy calls Analisa-SuSy using 0 arguments$")
	public void suSyCallsAnalisaSuSyZeroArguments() {
		aCLI = new CLInterface(arguments);
		Assert.assertTrue(aCLI.getFilepaths().size() == 0);
	}
	
	@Then("^Analisa-SuSy will return an error message$")
	public void analisaSuSyWillReturnErrorMessage() {
		aCLI.parse();
		Assert.assertTrue(aCLI.getFilepaths().size() == 0);
	}
	
	@Given("^f option as argument$")
	public void fOptionAsArgument() {
		arguments = new String[1];
		arguments[0] = "-f";	
		Assert.assertTrue(arguments.length == 1);
	}
	
	@When("^SuSy calls Analisa-SuSy using just 1 argument$")
	public void suSyCallsAnalisaSuSyOneArgument() {
		aCLI = new CLInterface(arguments);
		Assert.assertTrue(aCLI.getFilepaths().size() == 0);
	}
}