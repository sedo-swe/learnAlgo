package main.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadJsonSaveInFormat {
    public ReadJsonSaveInFormat() {

    }

    /*public void readJsonSaveInFormatNow() {
        try {
            JsonObject myobject = (JsonObject) new JsonParser().parse(new FileReader("MyFilePath"));
            System.out.println("Reading JSON file from Java program");
            FileReader fileReader = new FileReader(file);
            JSONObject json = (JSONObject) parser.parse(fileReader);
            String title = (String) json.get("title");
            String author = (String) json.get("author");
            long price = (long) json.get("price");
            System.out.println("title: " + title);
            System.out.println("author: " + author);
            System.out.println("price: " + price);
            JSONArray characters = (JSONArray) json.get("characters");
            Iterator i = characters.iterator();
            System.out.println("characters: ");
            while (i.hasNext()) {
                System.out.println(" " + i.next());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }*/
    public void readJsonAndWriteInPlain() {
        String filepath = "/Users/soh/IdeaProjects/learnAlgo/src/main/tools/products.json";
        StringBuffer sb = new StringBuffer();
        try {
            JsonArray products = (JsonArray) new JsonParser().parse(new FileReader(filepath));
            for (int i = 0; i < products.size(); i++) {
                JsonObject obj = (JsonObject) products.get(i);
//                System.out.println(obj.toString());
                System.out.println(obj.get("productName")
                        + " / " + obj.get("consumptionMarket")+ " / " + obj.get("productOfferingLevel")
                        + " / " + obj.get("billingFrequency") + " / " + obj.get("purchaseType")
                        + " / " + obj.get("productOfferingId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadJsonSaveInFormat readJsonSaveInFormat = new ReadJsonSaveInFormat();
        readJsonSaveInFormat.readJsonAndWriteInPlain();
    }
}
