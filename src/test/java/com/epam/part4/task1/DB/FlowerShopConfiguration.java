package com.epam.part4.task1.DB;

import com.epam.PropertiesConfiguration;
import com.epam.db.exception.ConfigurationException;
import com.epam.db.config.DatabaseConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class FlowerShopConfiguration extends DatabaseConfiguration {
    public FlowerShopConfiguration() throws ConfigurationException, IOException {
        conf = new PropertiesConfiguration("/db-flowershop.properties");
        this.host = conf.getString("qa.host");
        this.databaseName = conf.getString("qa.dbname");
        this.username = conf.getString("qa.username");
        this.password = conf.getString("qa.password");
        if (host == null || databaseName == null || username == null || password == null) {
            throw new ConfigurationException("Invalid FlowerShop DB configuration: " + getJDBCConnectionString());
        }
    }
}
