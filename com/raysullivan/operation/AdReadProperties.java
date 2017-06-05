package raysullivan.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AdReadProperties {
	private Properties p = new Properties();
	private AdUtil util = new AdUtil();
	public Properties getObjectRepository(String propertyFileName)
			throws IOException, AdException{
		InputStream stream;
		try {
			stream = new FileInputStream(new File(util.getTestPropertyPath()
					+ propertyFileName));
		} catch (FileNotFoundException e) {
			throw new AdException(
					"Error:  Cannot find test property file " + propertyFileName
							+ " at location " + util.getTestPropertyPath());
		}
		p.load(stream);
		return p;
	}

}
