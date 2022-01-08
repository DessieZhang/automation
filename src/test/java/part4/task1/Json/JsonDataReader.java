package part4.task1.Json;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import part4.task1.Flower;

public class JsonDataReader {
    //JsonReader jsonReader = new JsonReader(new FileReader(JsonDataReader.class.getResource()));
    private final String jsonFilePath = System.getProperty("user.dir")+"/src/test/resources/flowershop.json";
    private List<Flower> flowersList;
  //  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/flower.json");

    public JsonDataReader() throws FileNotFoundException {
        flowersList = getFlowersData();
    }

        public List<Flower> getFlowersData() throws FileNotFoundException {
            Gson gson = new Gson();
            BufferedReader bufferReader = null;
            try {
                bufferReader = new BufferedReader(new FileReader(jsonFilePath));
                Flower[] flowers = gson.fromJson(bufferReader, Flower[].class);
                return Arrays.asList(flowers);
            } finally {
                try { if(bufferReader != null) bufferReader.close();}
                catch (IOException ignore) {}
            }
        }

        public final Flower getFlowerByType(String type){
            return flowersList.stream().filter(x -> x.getType().equalsIgnoreCase(type)).findAny().get();
        }
}
