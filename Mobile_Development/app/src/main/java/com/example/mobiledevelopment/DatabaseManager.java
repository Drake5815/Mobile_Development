package com.example.mobiledevelopment;


import static java.util.Arrays.asList;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DatabaseManager {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection collection;
    public DatabaseManager(){
        Connection();
        collection = database.getCollection("Clients");
    }

    /*
        Connection To Database
     */
    private void Connection(){
        try{
            mongoClient = MongoClients.create("mongodb://localhost:27017/");
            database = mongoClient.getDatabase("MobileDevelopment");
        } catch (Exception e){
            System.out.println("System error: " + e);
        }
    }

    /*
        Setters and Getters indicated by Comments
     */
    // New Collection Setter
    public boolean setCollection(String name){
        try{
            collection = database.getCollection(name);
            return true;
        } catch (Exception e){
            System.out.println("System error: " + e);
            return false;
        }
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    // Appending Documents
    private Document doc;
    public void setAppendDocument(String key, Object value){
        this.doc.append(key, value);
    }
    public void createAccount(String key, HashMap<String, String> Account){
        Document temp = new Document();
        for(Map.Entry<String, String> entry : Account.entrySet()){
            temp.put(entry.getKey(),entry.getValue());
        }
        Document document = new Document(key, temp);
        collection.insertOne(document);
    }
    public void createRental(String key, Object Rental, String setKey, String setObject){
        Document query = new Document(setKey, setObject);
        Document found = new Document("$set", new Document(key,Rental));
        collection.updateOne(query, found);
    }
    public void createHotels(String key, Object Hotels, String setKey, String setObject){
        Document query = new Document(setKey, setObject);
        Document found = new Document("$set", new Document(key,Hotels));
        collection.updateOne(query, found);
    }
    public void createComplex(String key, Object Complex, String setKey, String setObject){
        Document query = new Document(setKey, setObject);
        Document found = new Document("$set", new Document(key,Complex));
        collection.updateOne(query, found);
    }
    public void createResort(String key, Object Resort, String setKey, String setObject){
        Document query = new Document(setKey, setObject);
        Document found = new Document("$set", new Document(key,Resort));
        collection.updateOne(query, found);
    }

}
