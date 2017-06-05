package raysullivan.operation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutomationDriverVariable {

	private ArrayList<String> vNameList = new ArrayList<String>();
	private ArrayList<String> vValueList = new ArrayList<String>();
	private int index;

	public AutomationDriverVariable() {
	}

	public String setVariableValue(String variableName, String variableValue) throws AutomationDriverException {
		validateVariableName(variableName);
		checkNullValue(variableValue);
		String returnString = variableValue;
		if (vNameList.contains(variableName)) {
			index = vNameList.indexOf(variableName);
			vValueList.set(index, variableValue);
		} else {
			vNameList.add(variableName);
			vValueList.add(variableValue);
		}
		return returnString;
	}

	public String getVariableValue(String variableName) throws AutomationDriverException {
		validateVariableName(variableName);
		String variableValue = null;
		int index = vNameList.indexOf(variableName);
		if (vNameList.contains(variableName)) {
			variableValue = vValueList.get(index);
			return variableValue;
		} else {
			throw new AutomationDriverException(
					"Error: Variable '" + variableName + "' has not been previously created");
		}
	}

	public String validateVariableName(String variableName) throws AutomationDriverException {
		checkEmptyVariable(variableName);
		try {
			String vDs = "${", vDe = "}";
			if (variableName.substring(0, 2).equals(vDs)) {
				try {
					int sx = variableName.indexOf(vDs) + 2;
					int ex = variableName.indexOf(vDe);
					String validatedVariableName = variableName.substring(sx, ex);
					checkNullValue(validatedVariableName);
					checkWhitespace(validatedVariableName);
					variableName = variableName.substring(variableName.indexOf(vDs), ex + 1);
					return variableName;
				} catch (StringIndexOutOfBoundsException s) {
					throw new AutomationDriverException(
							"Error:  Variable '" + variableName + "' does not have a valid ending delimiter }");
				}
			} else {
				return variableName;
				//throw new AutomationDriverException(
				//		"Error:  Variable '" + variableName + "' does not have a valid starting delimiter ${");
			}
		} catch (NullPointerException npe) {
			throw new AutomationDriverException("Error: Variable name cannot be null");
		} catch (StringIndexOutOfBoundsException e) {
		}
		throw new AutomationDriverException("Error: Variable '" + variableName + "' is not a valid variable name");
	}

	private void checkEmptyVariable(String stringToCheck) throws AutomationDriverException {
		try {
			if (stringToCheck == "${}") {
				throw new AutomationDriverException("Error: Variable name cannot be empty '${}'");
			}
		} catch (NullPointerException e) {
			throw new AutomationDriverException("Error: Variable name or value cannot contain blank or null values");
		}

	}

	private void checkNullValue(String stringToCheck) throws AutomationDriverException {
		try {
			if (stringToCheck.length() == 0) {
			}
		} catch (NullPointerException e) {
			throw new AutomationDriverException("Error: Variable name or value cannot contain blank or null values");
		}
	}

	private void checkWhitespace(String variableName) throws AutomationDriverException {

		if (variableName.length() > 1) {
			Pattern pattern = Pattern.compile("\\s");
			CharSequence s = variableName;
			Matcher matcher = pattern.matcher(s);
			boolean found = matcher.find();
			if (found || variableName.length() <= 1) {
				throw new AutomationDriverException("Variable '" + variableName + "' cannot contain whitespace");
			}
		}
	}

	public void checkAssertVariables(String variable1, String variable2) throws AutomationDriverException {
		if (variable1.equals(variable2)) {
			throw new AutomationDriverException("Error:  Cannot compare a variable to itself.");
		}

	}

}
