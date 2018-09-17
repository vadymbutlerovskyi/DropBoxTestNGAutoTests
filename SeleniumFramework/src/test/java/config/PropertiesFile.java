package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import tests.BaseTest;

public class PropertiesFile {

	static Properties prop = new Properties();
	public static void main(String[] args) {
		setProperties();
		//getProperties();
	}

	public static void getProperties() {
		try {
			String propPath = System.getProperty("user.dir") + "/src/test/java/config/config.properties";
			InputStream input = new FileInputStream(propPath);
			prop.load(input);
			BaseTest.browser = prop.getProperty("browser");
		}
		catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	public static void setProperties() {
		try {
			String propPath = System.getProperty("user.dir") + "/src/test/java/config/config.properties";
			OutputStream output = new FileOutputStream(propPath);
			prop.setProperty("browser", "firefox");
			prop.store(output, null);			
		}
		catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
