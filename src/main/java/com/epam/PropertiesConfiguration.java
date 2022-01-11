package com.epam;

import com.epam.db.exception.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Abstract class to retrieve values from the given properties file
 */
public class PropertiesConfiguration {

    private final String filepath;

    /**
     * Creates a new instance of PropertiesConfiguration by the given properties
     * file
     *
     * @param propertiesPath - path for the properties file, local to derived class
     */
    public PropertiesConfiguration(String propertiesPath) throws ConfigurationException {
        if (propertiesPath != null) {
            this.filepath = propertiesPath;
        } else {
            throw new ConfigurationException("No properties file defined");
        }
    }

    /**
     * Get a value as String from given properties file
     *
     * @param key - System property or system environment variable name
     * @return value of the requested key
     */
    public String getString(String key) throws IOException {
        return readPropertyValue(key);
    }

    /**
     * Get a value as Integer from given properties file
     *
     * @param key - System property or system environment variable name
     * @return value of the requested key
     */
    public int getIntegerValue(String key) throws IOException {
        return Integer.parseInt(readPropertyValue(key));
    }

    // Read value from properties file
    private String readPropertyValue(String key) throws IOException {
        Properties properties = new Properties();
        InputStream input = null;
        String result;
        try {
            input = this.getClass().getResourceAsStream(filepath);
            properties.load(input);
            result = properties.getProperty(key);
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return result;
    }
}
