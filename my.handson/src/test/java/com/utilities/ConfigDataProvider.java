package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	Properties pro;

	// Constructor to load config.properties file
	public ConfigDataProvider() {

		File src = new File("./Config/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);

			pro = new Properties();

			pro.load(fis);
		}

		catch (Exception e) {

			System.out.println("Not able to load config file: " + e.getMessage());

		}

	}

	public String getDatafromConfig(String randomSearchKey) {

		return pro.getProperty(randomSearchKey);

	}

	public String getBrowser() {

		return pro.getProperty("browser");

	}

	public String getURL() {

		return pro.getProperty("autTestURL");
	}

}
