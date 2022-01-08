package db.config;

import configs.PropertiesConfiguration;
import db.exception.ConfigurationException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Setter
public class DatabaseConfiguration {
    protected PropertiesConfiguration conf;
    protected String host;
    protected String username;
    protected String password;
    protected String databaseName;

    protected final Logger log = LogManager.getLogger(this.getClass());

    public DatabaseConfiguration() {
        // Creates a DatabaseConfiguration object with no given parameters
    }

    public Connection getConnection() throws SQLException, ConfigurationException {
        String url = "";
        try {
            url = this.getJDBCConnectionString();
            return DriverManager.getConnection(url, username, password);
        } catch (ConfigurationException e) {
            log.error(e);
            throw e;
        } catch (SQLException throwables) {
            log.debug("FDI Connection string: {}", url);
            log.error(throwables);
            throw throwables;
        }
    }

    public String getJDBCConnectionString() throws ConfigurationException {

        if (isInvalidConfig()) {
            throw new ConfigurationException("Invalid DB configuration found." +
                    " Host:" + host +
                    " DatabaseName:" + databaseName +
                    " Username:" + username +
                    " Password:" + password
            );
        }
        String databasePort = "5432";
        String connectionString = "jdbc:postgresql://" + host + ":" + databasePort + "/" + databaseName + "?zeroDateTimeBehavior=convertToNull";
        log.debug("Generated JDBC connection string: {}", connectionString);
        return connectionString;
    }

    protected boolean isInvalidConfig() {
        return StringUtils.isBlank(host) || StringUtils.isBlank(username) || StringUtils.isBlank(password);
    }
}
