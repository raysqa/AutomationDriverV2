package raysullivan.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AutomationDriverReadProperties {
	private Properties p = new Properties();
	private AutomationDriverUtil util = new AutomationDriverUtil();
	public Properties getObjectRepository(String propertyFileName)
			throws IOException, AutomationDriverException{
		InputStream stream;
		try {
			stream = new FileInputStream(new File(util.getTestPropertyPath()
					+ propertyFileName));
		} catch (FileNotFoundException e) {
			throw new AutomationDriverException(
					"Error:  Cannot find test property file " + propertyFileName
							+ " at location " + util.getTestPropertyPath());
		}
		p.load(stream);
		return p;
	}

}
