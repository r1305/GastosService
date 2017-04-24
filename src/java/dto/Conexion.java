/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Lenovo
 */
public class Conexion {
    private MongoClient client;
    private final MongoClientURI clientURI = new MongoClientURI("mongodb://root:root@ds135790.mlab.com:35790/gastos_app");
    private MongoDatabase database;
    private MongoCollection<Document> col;

    public Conexion() {
    }

    public Conexion(MongoClient client, MongoDatabase database, MongoCollection col) {
        this.client = client;
        this.database = database;
        this.col = col;
    }

    public MongoClient getClient() {
        return client;
    }

    public void setClient(MongoClient client) {
        this.client = client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    public MongoCollection getCol() {
        return col;
    }

    public void setCol(MongoCollection col) {
        this.col = col;
    }
           
    
    public MongoCollection getConnection(String collection) {
        client = new MongoClient(clientURI);
        database = client.getDatabase("gastos_app");
        col =database.getCollection(collection);      
        return col;
    }
}