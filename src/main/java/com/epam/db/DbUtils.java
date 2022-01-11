package com.epam.db;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {
    private DbUtils() {
    }

    @NotNull
    public static JSONArray convertToJSONArray(ResultSet resultSet) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int totalRows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < totalRows; i++) {
                JSONObject obj = new JSONObject();
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.put(obj);
            }
        }
        return jsonArray;
    }

    @NotNull
    public static ArrayList<Object> convertToList(ResultSet resultSet) throws SQLException {
        ArrayList<Object> list = new ArrayList<Object>();
        while (resultSet.next()) {
            int totalRows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < totalRows; i++) {
                list.add( resultSet.getObject(i + 1));
            }
        }
        return list;
    }

    @NotNull
    public static List<Object> convertJSONArrayToList(JSONArray jArr)
    {
        List<Object> list = new ArrayList<Object>();
        try {
            for (int i=0, l=jArr.length(); i<l; i++){
                list.add(jArr.get(i));
            }
        } catch (JSONException e) {

        }

        return list;
    }
}
