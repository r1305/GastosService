/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import dto.Conexion;
import java.util.Date;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Lenovo
 */
public class Dao {
    private Conexion c = new Conexion();
    public String login(String u, String p) {
        String id;
        JSONObject o = new JSONObject();
        MongoCollection<Document> col = c.getConnection("usuarios");
        Document doc = col.find(and(eq("usuario", u), eq("psw", p))).first();
        try {
            o.put("nombre", doc.getString("nombre"));
            o.put("id", doc.getInteger("_id"));
        } catch (NullPointerException e) {
            System.out.println(e);
            o.put("nombre", "error");
            o.put("id", "error");
        }
        return o.toString();
    }
    
    public String guardarGasto(String descripcion,String monto,String id,String categoria,String fecha){
        String rpta="{\"status\":\"ok\"}";
        c = new Conexion();
        MongoCollection<Document> col = c.getConnection("gastos");
        //int idUsuario = 0;
        Document doc = new Document();       
        doc.append("idU", Integer.parseInt(id));
        doc.append("descripcion", descripcion);
        doc.append("monto", Double.parseDouble(monto));
        doc.append("categoria",categoria);
        doc.append("fecha", fecha);
        col.insertOne(doc);
        return rpta;
    }
    
    public String listarGastos(String id){
        MongoCollection<Document> col = c.getConnection("gastos");
        MongoCursor<Document> cursor = col.find(eq("idU",Integer.parseInt(id))).iterator();
        Document doc;
        JSONArray a=new JSONArray();
        JSONObject o=new JSONObject();
        try {
            while (cursor.hasNext()) {
                doc = cursor.next();
                JSONObject g=new JSONObject();
                g.put("desc",doc.getString("descripcion"));
                g.put("monto",doc.getDouble("monto"));
                g.put("categoria",doc.getString("categoria"));
                a.add(g);
            }
            o.put("data", a);
        } catch (Exception e) {
            System.out.println("ListarGastos: " + e);
        } finally {
            cursor.close();
        }
        return o.toString();
    }
    
    public String listarCategorias(){
        MongoCollection<Document> col = c.getConnection("categorias");
        MongoCursor<Document> cursor = col.find().iterator();
        Document doc;
        JSONArray a=new JSONArray();
        JSONObject o=new JSONObject();
        try {
            while (cursor.hasNext()) {
                doc = cursor.next();
                JSONObject g=new JSONObject();
                g.put("id",doc.getInteger("_id"));
                g.put("desc",doc.getString("descripcion"));
                a.add(g);
            }
            o.put("data", a);
        } catch (Exception e) {
            System.out.println("listarCategorias: " + e);
        } finally {
            cursor.close();
        }
        return o.toString();
    }
    
}
