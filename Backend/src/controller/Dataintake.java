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
@WebServlet("/display")
public class Dataintake extends HttpServlet {
   /**
    * @see HttpServlet#HttpServlet()
    */
   public Dataintake(){
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
    	 Integer start=Integer.parseInt(request.getParameter("start"));
    	 Integer limit=Integer.parseInt(request.getParameter("limit"));
    	 String title=request.getParameter("title");
    	 String diname=request.getParameter("diname");
    	 String year=request.getParameter("year");
    	 String lang=request.getParameter("lang");
         System.out.println(title+diname+year+lang);
         if(year!="") {year=year.substring(0,4);}
    	 if(start!=null) {
        Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url,username,password);
		Statement s1 = conn.createStatement();
		String counter="SELECT COUNT(*) FROM film JOIN LANGUAGE ON film.language_id=language.language_id WHERE isdeleted=0 AND title LIKE '"+title+"%' AND release_year LIKE '"+year+"%' AND director LIKE '"+diname+"%' AND language.language_id LIKE '"+lang+"%' ORDER BY film_id";
		ResultSet rs1=s1.executeQuery(counter);
		System.out.println(counter);
		System.out.println("connection stabilish");	
		PreparedStatement stmt =conn.prepareStatement("SELECT * FROM film JOIN LANGUAGE ON film.language_id=language.language_id WHERE isdeleted=0 AND title LIKE ? AND release_year LIKE ? AND director LIKE ? AND language.language_id LIKE ? ORDER BY film_id LIMIT ?,?");
		stmt.setString(1,""+title+"%");
		stmt.setString(2,""+year+"%");
		stmt.setString(3,""+diname+"%");
		stmt.setString(4,""+lang+"%");
		stmt.setInt(5,start);
		stmt.setInt(6,limit);
		System.out.println(stmt);
		ResultSet rs=stmt.executeQuery();
		rs1.next();
        JsonArray array = new JsonArray(); 
		while(rs.next()) {					
		JsonObject record = new JsonObject();
		        record.addProperty("film_id", rs.getString("film_id"));
		        record.addProperty("title", rs.getString("title"));
				record.addProperty("description", rs.getString("DESCRIPTION"));
				record.addProperty("release_year", rs.getInt("release_year"));
				record.addProperty("language", rs.getString("name"));
				record.addProperty("director", rs.getString("Director"));
				record.addProperty("rating", rs.getString("rating"));
				record.addProperty("special_features", rs.getString("special_features"));
				array.add(record);
			}
            JsonObject record = new JsonObject();
			record.addProperty("total", rs1.getInt(1));
			record.add("root", array);
			out.println(record);
			out.flush();
        	   }} 
      catch(Exception e){
       System.out.println(e);
       }
    }
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

	}