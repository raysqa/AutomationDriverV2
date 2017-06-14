package raysullivan.unitTest;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import raysullivan.operation.AdException;
import raysullivan.operation.AdVariable;

public class TestAdVariable {
	AdVariable var = new AdVariable();

	@Test(description = "validateVariable", dataProvider = "validVariableNames", enabled = true)
	public final void validateVariable(String fullyFormedVariableNameString, String newVariableName)
			throws AdException {
		assertThat(var.validateVariableName(fullyFormedVariableNameString)).isEqualTo(newVariableName);
	}

	@Test(description = "validateVariableInvalid", dataProvider = "invalidVariableNames", expectedExceptions = AdException.class, enabled = true)
	public final void validateVariableInvalid(String fullyFormedVariableNameString, String newVariableName)
			throws AdException {
		assertThat(var.validateVariableName(fullyFormedVariableNameString)).isEqualTo(newVariableName);
	}

	@Test(dataProvider = "setVariableOverwrite", description = "setVariableAndOverwrite", enabled = true)
	public void setVariableAndOverwrite(String variableName, String variableValue, String newVariableValue)
			throws AdException {
		assertThat(var.setVariableValue(variableName, variableValue)).isEqualTo(variableValue);
		assertThat(variableValue).isEqualTo(var.getVariableValue(variableName));
		assertThat(var.setVariableValue(variableName, newVariableValue)).isEqualTo(newVariableValue);
		assertThat(newVariableValue).isEqualTo(var.getVariableValue(variableName));
		assertThat(variableValue).isNotEqualTo(var.getVariableValue(variableName));
	}

	@Test(dataProvider = "setVariableMultiple", description = "setVariableMultiple", enabled = true)
	public void setVariableMultiple(String variableName, String variableValue, String newVariableName,
			String newVariableValue) throws AdException {
		assertThat(var.setVariableValue(variableName, variableValue)).isEqualTo(variableValue);
		assertThat(var.setVariableValue(newVariableName, newVariableValue)).isEqualTo(newVariableValue);
		assertThat(variableValue).isEqualTo(var.getVariableValue(variableName));
		assertThat(newVariableValue).isEqualTo(var.getVariableValue(newVariableName));
		assertThat(var.getVariableValue(variableName)).isNotEqualTo(var.getVariableValue(newVariableName));
	}

	@Test(description = "validateAssertVariables", dataProvider = "assertVariables", expectedExceptions = AdException.class, enabled = true)
	public void validateAssertVariables(String variable1, String variable2) throws AdException {
		var.checkAssertVariables(variable1, variable2);
	}

	@Test(description = "validateisAVariable", dataProvider = "isAVariable", enabled = true)
	public void isAVariableTest(String variable, boolean expected) throws AdException {
		assertThat(var.isAVariable(variable)).isEqualTo(expected);
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
	public final Object[][] isAVariable() {
		return new Object[][] { new Object[] { "${ValidVariableName}", true },
				new Object[] { "${Valid.Variable}", true }, new Object[] { "${a}", true },
				new Object[] { "${Valid_Really_Long_Variable}", true },
				new Object[] { "${Truncated_Long_Vari}able", true },
				new Object[] { "Not a Variable", false },
				new Object[] { "{NotaVariable}", false }, new Object[] { "{NotaVariable", false },
				new Object[] { "NotaVariable}", false }, new Object[] { "n", false },
				new Object[] { "nv", false }, new Object[] { "nva", false }, new Object[] { "", false },
				new Object[] { null, false }, };
	}

	@DataProvider
	public final Object[][] validVariableNames() {
		return new String[][] { new String[] { "${ValidVariableName}", "${ValidVariableName}" },
				new String[] { "${Valid.Variable}", "${Valid.Variable}" }, new String[] { "${a}", "${a}" },
				new String[] { "${Valid_Really_Long_Variable}", "${Valid_Really_Long_Variable}" },
				new String[] { "${Truncated_Long_Vari}able", "${Truncated_Long_Vari}" },};
	}
	
	@DataProvider
	public final Object[][] invalidVariableNames() {
		return new String[][] { new String[] { "${Invalid VariableName}", "${Invalid VariableName}" },
				new String[] { "${}", "${}" }, new String[] { "${InalidVariableName", "${InalidVariableName" }, };
	}

	@DataProvider
	public final Object[][] assertVariables() {
		return new String[][] { new String[] { "${user.name}", "${user.name}", }, };
	}

}
