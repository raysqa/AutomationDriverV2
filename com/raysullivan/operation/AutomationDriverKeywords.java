package raysullivan.operation;


public class AutomationDriverKeywords {
	private static AutomationDriverVariable var = null;
	private static AutomationDriverUtil util = new AutomationDriverUtil();

	public static String[] validateAssertVariables(String vName1, String vName2, String operation)
			throws AutomationDriverException {
		var = new AutomationDriverVariable();
		String[] variableNames = { var.validateVariableName(vName1), var.validateVariableName(vName2) };
		return variableNames;
	}

	public String AssertEqual(String variableName1, String variableName2, AutomationDriverVariable var)
			throws AutomationDriverException {
		if (var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String AssertNotEqual(String variableName1, String variableName2, AutomationDriverVariable var)
			throws AutomationDriverException {
		if (!var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String AssertContains(String variableName1, String variableName2, AutomationDriverVariable var)
			throws AutomationDriverException {
		if (var.getVariableValue(variableName1).contains(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String AssertNotContains(String variableName1, String variableName2, AutomationDriverVariable var)
			throws AutomationDriverException {
		if (!var.getVariableValue(variableName1).contains(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}
}
