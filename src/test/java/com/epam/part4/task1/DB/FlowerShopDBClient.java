package com.epam.part4.task1.DB;


import com.epam.db.client.PostgreSqlDbClient;
import com.epam.db.exception.ConfigurationException;
import com.epam.db.exception.DatabaseException;
import com.epam.part4.task1.*;
import org.json.JSONException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlowerShopDBClient extends PostgreSqlDbClient {

    public FlowerShopDBClient() {
        super();
    }

    @Override
    public void configure() {
        try {
            this.databaseConfiguration = new FlowerShopConfiguration();
        } catch (ConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get flowers info from DB
     * @return List of flowers info
     * @throws DatabaseException
     */
    public List<Flower> getFlowers() throws DatabaseException {
        String sql = "select * from public.flowers";
        try {
            ResultSet rs = executeSelectStatement(sql);
            List<Flower> flowers = new ArrayList<>();
            while (rs.next()) {
                Flower flower = null;
                switch (rs.getString("type")) {
                    case "Rose":
                        flower = new Rose();
                        break;
                    case "Pansy":
                        flower = new Pansy();
                        break;
                    case "Petunia":
                        flower = new Petunia();
                        break;
                    case "Violet":
                        flower = new Violet();
                        break;
                    case "Carnation":
                        flower = new Carnation();
                        break;
                    default:
                        System.out.println("No flowers found");
                }
                flower.setType(rs.getString("type"));
                flower.setPrice(rs.getDouble("price"));
                flower.setColor(rs.getString("color"));
                flower.setAmount(rs.getInt("amount"));
                flowers.add(flower);
            }
            return flowers;
        } catch (SQLException | ConfigurationException exception) {
            throw throwSQLException(sql, exception);
        } catch (JSONException exception) {
            throw throwJSONException(sql, exception);
        }
    }

    /**
     * Update flower info
     * @param flowers current flowers
     * @return updated rows number
     * @throws DatabaseException
     */
    public int updateFlowersTable(List<Flower> flowers) throws DatabaseException {
        int rows = 0;
        String sql = "";
        try {
            for (Flower flower : flowers) {
                sql = "update public.flowers set  amount  = " + flower.getAmount() + " where type = '" + flower.getType() + "'";
                rows = executeUpdateStatement(sql);
                rows = +rows;
            }
            return rows;
        } catch (SQLException | ConfigurationException throwable) {
            throw throwSQLException(sql, throwable);
        }
    }
}
