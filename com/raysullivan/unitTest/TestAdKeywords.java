package raysullivan.unitTest;

import org.testng.annotations.Test;

import raysullivan.operation.AdKeywords;
import raysullivan.operation.AdVariable;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.DataProvider;

public class TestAdKeywords {
	AdVariable var = new AdVariable();
	AdKeywords kw = new AdKeywords();

	@Test(description = "TestAssertEquals", dataProvider = "AssertEquals", enabled = true)
	public void TestAssertEquals(String variableName1, String variableValue1, String variableName2,
			String variableValue2, String returnString) throws Exception {
		var.setVariableValue(variableName1, variableValue1);
		var.setVariableValue(variableName2, variableValue2);
		assertThat(kw.assertEqual(variableName1, variableName2, var)).isEqualTo(returnString);
	}

	@Test(description = "TestAssertNotEquals", dataProvider = "AssertNotEquals", enabled = true)
	public void TestAssertNotEquals(String variableName1, String variableValue1, String variableName2,
			String variableValue2, String returnString) throws Exception {
		var.setVariableValue(variableName1, variableValue1);
		var.setVariableValue(variableName2, variableValue2);
		assertThat(kw.assertNotEqual(variableName1, variableName2, var)).isEqualTo(returnString);
	}

	@Test(description = "TestAssertContains", dataProvider = "AssertContains", enabled = true)
	public void TestAssertContains(String variableName1, String variableValue1, String variableName2,
			String variableValue2, String returnString) throws Exception {
		var.setVariableValue(variableName1, variableValue1);
		var.setVariableValue(variableName2, variableValue2);
		assertThat(kw.assertContains(variableName1, variableName2, var)).isEqualTo(returnString);
	}

	@Test(description = "TestAssertNotContains", dataProvider = "AssertNotContains", enabled = true)
	public void TestAssertNotContains(String variableName1, String variableValue1, String variableName2,
			String variableValue2, String returnString) throws Exception {
		var.setVariableValue(variableName1, variableValue1);
		var.setVariableValue(variableName2, variableValue2);
		assertThat(kw.assertNotContains(variableName1, variableName2, var)).isEqualTo(returnString);
	}

	@DataProvider
	public final Object[][] AssertVariablesValid() {
		return new String[][] { new String[] { "${vname1}", "${vname2}", "ASSERTEQUALS", "${vname1}", "${vname2}" },
				new String[] { "${vname2}", "${vname1}", "ASSERTNOTEQUALS", "${vname2}", "${vname1}" }, };
	}

	@DataProvider
	public final Object[][] AssertVariablesValidInvalid() {
		return new String[][] { new String[] { "", "", "ASSERTEQUALS" }, new String[] { "vname1", "", "ASSERTEQUALS" },
				new String[] { "vname1", "vname2", "ASSERTEQUALS" },
				new String[] { "${vname1}", "vname2", "ASSERTEQUALS" }, new String[] { null, null, "ASSERTEQUALS" },
				new String[] { "${vname1}", null, "ASSERTEQUALS" }, new String[] { "${}", "${vname2}", "ASSERTEQUALS" },
				new String[] { "${vname1}", "${}", "ASSERTEQUALS" }, new String[] { "${}", "${}", "ASSERTEQUALS" },
				new String[] { "${vname1", "${vname2", "ASSERTEQUALS" },
				new String[] { "${vname1}", "${vname2", "ASSERTEQUALS" },
				new String[] { "{vname}", "{vname2}", "ASSERTEQUALS" },
				new String[] { "${vname1}", "{vname2}", "ASSERTEQUALS" },
				new String[] { "${vname1", "{vname2}", "ASSERTEQUALS" },
				new String[] { "${vname1}", "{vname2", "ASSERTEQUALS" }, };
	}

	@DataProvider
	public final Object[][] AssertEquals() {
		return new String[][] {
				new String[] { "${vname1}", "this is an equal value", "${vname2}", "this is an equal value",
						"Success" },
				new String[] { "${vname1}", "THIS IS AN EQUAL VALUE", "${vname2}", "this is an equal value", "Error" },
				new String[] { "${vname1}", "this is an equal value", "${vname2}", "this is not an equal value",
						"Error" },
				new String[] { "${vname1}", "", "${vname2}", "", "Success" }, };
	}

	@DataProvider
	public final Object[][] AssertNotEquals() {
		return new String[][] {
				new String[] { "${vname1}", "this is an equal value", "${vname2}", "this is an equal value", "Error" },
				new String[] { "${vname1}", "THIS IS AN EQUAL VALUE", "${vname2}", "this is an equal value",
						"Success" },
				new String[] { "${vname1}", "this is an equal value", "${vname2}", "this is not an equal value",
						"Success" }, };
	}

	@DataProvider
	public final Object[][] AssertContains() {
		return new String[][] { new String[] { "${vname1}", "This is a value", "${vname2}", " a val", "Success" },
				new String[] { "${vname1}", "This is a value", "${vname2}", " A VALUE", "Error" },
				new String[] { "${vname1}", "This is a value", "${vname2}", "This is a value", "Success" },
				new String[] { "${vname1}", "This is a value", "${vname2}", "This is a value that I like", "Error" },
				new String[] { "${vname1}", "This is a value", "${vname2}", " ", "Success" },
				new String[] { "${vname1}", "This is a value", "${vname2}", "I want a horsey", "Error" },
				new String[] { "${vname1}", " ", "${vname2}", "I want a horsey", "Error" }, };
	}

	@DataProvider
	public final Object[][] AssertNotContains() {
		return new String[][] { new String[] { "${vname1}", "This is a value", "${vname2}", " a value", "Error" },
				new String[] { "${vname1}", "This is a value", "${vname2}", "THIS IS A VALUE", "Success" },
				new String[] { "${vname1}", "This is a value", "${vname2}", " ", "Error" },
				new String[] { "${vname1}", "This is a value", "${vname2}", "I want a horsey", "Success" },
				new String[] { "${vname1}", " ", "${vname2}", "I want a horsey", "Success" }, new String[] {
						"${vname1}", "This is a value", "${vname2}", "This is a value that I like", "Success" }, };
	}
}
