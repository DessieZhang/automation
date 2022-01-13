package com.epam.part4.task1.Json;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.epam.part4.task1.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class JsonHandler {
    private static Logger logger = Logger.getLogger(JsonHandler.class.getName());
    private final String jsonFilePath = System.getProperty("user.dir") + "/src/test/resources/flowershop.json";

    public List<Flower> getFlowersData() throws FileNotFoundException {
        List<Flower> flowers = new ArrayList<>();
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(jsonFilePath));
            Flower[] flower = gson.fromJson(bufferReader, Flower[].class);
            flowers = Arrays.asList(flower);
            return flowers;
        } finally {
            try {
                if (bufferReader != null) bufferReader.close();
            } catch (IOException e) {
                logger.warning("Read Json file failed. The fail reason is: " + e.getMessage());
            }
        }
    }


    public void updateFlowersData(List<Flower> flowers) {
        try {
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            jsonWriter.beginArray();
            for (Flower flower : flowers) {
                jsonWriter.beginObject();
                jsonWriter.name("type").value(flower.getType());
                jsonWriter.name("price").value(flower.getPrice());
                jsonWriter.name("color").value(flower.getColor());
                jsonWriter.name("amount").value(flower.getAmount());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            logger.warning("Write to Json file failed. The fail reason is: " + e.getMessage());
        }
    }
}