package part4.task1.DB;


import db.client.PostgreSqlDbClient;
import db.exception.ConfigurationException;
import db.exception.DatabaseException;
import org.json.JSONException;
import part4.task1.DB.FlowerShopConfiguration;
import part4.task1.Flower;

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

    public List<Flower> getFlowers() throws DatabaseException {
        String sql = "select * from public.flowers";
        try {
            ResultSet rs = executeSelectStatement(sql);
            List<Flower> flowers = new ArrayList<>();
            while (rs.next()) {
                Flower flower = new Flower();
                flower.setType(rs.getString(1));
                flower.setPrice(rs.getDouble(2));
                flower.setColor(rs.getString(3));
                flower.setAmount(rs.getInt(4));
                flowers.add(flower);
            }
            return flowers;
        } catch (SQLException | ConfigurationException exception) {
            throw throwSQLException(sql, exception);
        } catch (JSONException exception) {
            throw throwJSONException(sql, exception);
        }
    }


    public int updateFlowersTable(List<Flower> flowers) throws DatabaseException {
        int rows = 0;
        String sql= "";
        try {
            for (Flower flower:flowers) {
                sql = "update public.flowers set  amount  = " + flower.getAmount() + " where type = '" + flower.getType() + "'";
                rows = executeUpdateStatement(sql);
                rows = + rows ;
            }
            return rows;
        } catch (SQLException | ConfigurationException throwable) {
            throw throwSQLException(sql,throwable);
        }
    }
}
