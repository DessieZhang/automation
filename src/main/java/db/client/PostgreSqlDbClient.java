package db.client;

import db.DbUtils;
import db.config.DatabaseConfiguration;
import db.exception.ConfigurationException;
import db.exception.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class PostgreSqlDbClient {
    protected final Logger log = LogManager.getLogger(this.getClass());
    protected DatabaseConfiguration databaseConfiguration;



    public PostgreSqlDbClient() {
        this.configure();
    }

    public abstract void configure();

    public ResultSet executeSelectStatement(String query) throws SQLException, ConfigurationException {
        log.debug("Executing SELECT SQL: {}", query);
        try (Connection conn = databaseConfiguration.getConnection();) {
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (Exception ex) {
            log.debug(ex.getStackTrace(), ex);
            throw ex;
        }
    }

    public int executeUpdateStatement(String query) throws SQLException, ConfigurationException {
        log.debug("Executing UPDATE SQL: {}", query);
        try (Connection conn = databaseConfiguration.getConnection();
             Statement statement = conn.createStatement())
        {
            int updatedRows = statement.executeUpdate(query);
            log.debug("Updated {} row(s)", updatedRows);
            return updatedRows;
        } catch (Exception ex) {
            log.debug(ex.getStackTrace(), ex);
            throw ex;
        }
    }

    protected DatabaseException throwSQLException(String sql, Exception exception) {
        log.debug("Error on executing query: {} with error {} ", sql, exception.getMessage());
        return new DatabaseException("Error on executing query: " + sql, exception);
    }

    protected DatabaseException throwJSONException(String sql, Exception exception) {
        log.debug("SQL result error: {} with {} ", exception.getMessage(), sql);
        return new DatabaseException("SQL JSON result error: " + sql, exception);
    }
}