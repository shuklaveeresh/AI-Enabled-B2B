package com.highradius.training.contoller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.highradius.training.dao.POJO;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
@WebServlet("/unique")
public class Dataunique extends HttpServlet {
   /**
    * @see HttpServlet#HttpServlet()
    */
   public Dataunique(){
       super();
       // TODO Auto-generated constructor stub
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	     PrintWriter out=response.getWriter();  
         String url = "jdbc:mysql://localhost:3306/sakila";
         String username = "root";
         String password = "root";
        Connection conn=null;
     try{
        Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url,username,password);
		System.out.println("connection stabilish");	
		Statement s1 = conn.createStatement();
		ResultSet rs1=s1.executeQuery("SELECT DISTINCT special_features FROM film");
		Statement s2 = conn.createStatement();
		ResultSet rs2=s2.executeQuery("SELECT DISTINCT rating FROM film");
		Statement s3 = conn.createStatement();
		ResultSet rs3=s3.executeQuery("SELECT name,language_id FROM language");
		JsonArray array = new JsonArray();
		while(rs1.next()) {					
			JsonObject record = new JsonObject();
			        record.addProperty("special_features", rs1.getString(1));
					array.add(record);
				}
		JsonArray array2 = new JsonArray();
		while(rs2.next()) {					
			JsonObject record = new JsonObject();
			        record.addProperty("rating", rs2.getString(1));
					array2.add(record);
				}
		JsonArray array3 = new JsonArray();
		while(rs3.next()) {					
			JsonObject record = new JsonObject();
			        record.addProperty("language", rs3.getString(1));
			        record.addProperty("value", rs3.getString(2));
					array3.add(record);
				}
		JsonObject record = new JsonObject();
		record.add("special_features", array);
		record.add("rating", array2);
		record.add("language", array3);
		out.println(record);
		out.flush();
        	   } 
      catch(Exception e){
       System.out.println(e);
       }
    }
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

	}