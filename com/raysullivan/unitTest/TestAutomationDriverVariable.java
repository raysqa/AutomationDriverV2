package raysullivan.unitTest;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import raysullivan.operation.AutomationDriverException;
import raysullivan.operation.AutomationDriverVariable;

public class TestAutomationDriverVariable {
	AutomationDriverVariable var = new AutomationDriverVariable();

	@Test(description = "validateVariable", dataProvider = "validVariableNames", enabled = true)
	public final void validateVariable(String fullyFormedVariableNameString, String newVariableName)
			throws AutomationDriverException {
		assertThat(var.validateVariableName(fullyFormedVariableNameString)).isEqualTo(newVariableName);
	}

	@Test(description = "validateVariableInvalid", dataProvider = "invalidVariableNames", expectedExceptions = AutomationDriverException.class, enabled = true)
	public final void validateVariableInvalid(String fullyFormedVariableNameString, String newVariableName)
			throws AutomationDriverException {
		assertThat(var.validateVariableName(fullyFormedVariableNameString)).isEqualTo(newVariableName);
	}

	@Test(dataProvider = "setVariableOverwrite", description = "setVariableAndOverwrite", enabled = true)
	public void setVariableAndOverwrite(String variableName, String variableValue, String newVariableValue)
			throws AutomationDriverException {
		assertThat(var.setVariableValue(variableName, variableValue)).isEqualTo(variableValue);
		assertThat(variableValue).isEqualTo(var.getVariableValue(variableName));
		assertThat(var.setVariableValue(variableName, newVariableValue)).isEqualTo(newVariableValue);
		assertThat(newVariableValue).isEqualTo(var.getVariableValue(variableName));
		assertThat(variableValue).isNotEqualTo(var.getVariableValue(variableName));
	}

	@Test(dataProvider = "setVariableMultiple", description = "setVariableMultiple", enabled = true)
	public void setVariableMultiple(String variableName, String variableValue, String newVariableName,
			String newVariableValue) throws AutomationDriverException {
		assertThat(var.setVariableValue(variableName, variableValue)).isEqualTo(variableValue);
		assertThat(var.setVariableValue(newVariableName, newVariableValue)).isEqualTo(newVariableValue);
		assertThat(variableValue).isEqualTo(var.getVariableValue(variableName));
		assertThat(newVariableValue).isEqualTo(var.getVariableValue(newVariableName));
		assertThat(var.getVariableValue(variableName)).isNotEqualTo(var.getVariableValue(newVariableName));
	}

	@Test(description = "setinvalidVariableValue", dataProvider = "setVariableInvalid", expectedExceptions = AutomationDriverException.class, enabled = true)
	public void setinvalidVariableValue(String variableName, String variableValue) throws AutomationDriverException {
		var.setVariableValue(variableName, variableValue);
	}

	@Test(description = "validateAssertVariables", dataProvider = "assertVariables", expectedExceptions = AutomationDriverException.class, enabled = true)
	public void validateAssertVariables(String variable1, String variable2) throws AutomationDriverException {
		var.checkAssertVariables(variable1, variable2);
	}

	@DataProvider
	public final Object[][] setVariableOverwrite() {
		return new String[][] { new String[] { "${user.name}", "Heidi Mc Natt", "Curious George" },
				new String[] { "${user.name}", "Heidi Mc Natt", "Heidi McNatt" }, 
				new String[] { "${user.name}", "Heidi Mc Natt", "" }, };
	}

	@DataProvider
	public final Object[][] setVariableMultiple() {
		return new String[][] { new String[] { "${user.name}", "Heidi Mc Natt", "${animal.name}", "Curious George" },
				new String[] { "${user.name}", "Heidi Mc Natt", "${full.name}", "Hildred F. Mc Natt III" }, };
	}

	@DataProvider
	public final Object[][] validVariableNames() {
		return new String[][] { new String[] { "${ValidVariableName}", "${ValidVariableName}" },
				new String[] { "${Valid.Variable}", "${Valid.Variable}" }, new String[] { "${a}", "${a}" },
				new String[] { "${Valid_Really_Long_Variable}", "${Valid_Really_Long_Variable}" },
				new String[] { "${Truncated_Long_Vari}able", "${Truncated_Long_Vari}" }, 
				new String[] { "Not a Variable", "Not a Variable" }, 
				new String[] { "{NotaVariable}", "{NotaVariable}" }, new String[] { "{NotaVariable", "{NotaVariable" },
				new String[] { "NotaVariable}", "NotaVariable}" }, new String[] { "a", "a", },
				new String[] { "ab", "ab" }, new String[] { "abc", "abc" }, new String[] { "", "", },};
	}

	@DataProvider
	public final Object[][] invalidVariableNames() {
		return new String[][] { new String[] { "${Invalid VariableName}", "${Invalid VariableName}" },
				
				new String[] { "{NotaVariable}", "{NotaVariable}" }, new String[] { "{NotaVariable", "{NotaVariable" },
				new String[] { "NotaVariable}", "NotaVariable}" }, new String[] { "a", "a", },
				new String[] { "ab", "ab" }, new String[] { "abc", "abc" }, new String[] { "", "", },
				new String[] { "${}", "${}" }, new String[] { "${InalidVariableName", "${InalidVariableName" },
				new String[] { null, "Heidi Mc Natt" }, new String[] { null, null }, };
	}

	@DataProvider
	public final Object[][] setVariableInvalid() {
		return new String[][] { new String[] { null, null }, 
				new String[] { null, null },
				new String[] { null, "Invalid" }, 
				new String[] { "", "Invalid" }, 
				new String[] { "Invalid", null }, 
				new String[] { null, "" },};
	}

	@DataProvider
	public final Object[][] assertVariables() {
		return new String[][] { new String[] { "${user.name}", "${user.name}", }, };
	}

}
