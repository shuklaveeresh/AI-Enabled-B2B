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
@WebServlet("/insert")
public class Datainsert extends HttpServlet {
   /**
    * @see HttpServlet#HttpServlet()
    */
   public Datainsert(){
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
    	 String fid=request.getParameter("film_id");
    	 String title=request.getParameter("title");
    	 String date=request.getParameter("date");
    	 String special=request.getParameter("special");
    	 String rating=request.getParameter("rating");
    	 String lang=request.getParameter("lang");
    	 String diname=request.getParameter("diname");
    	 String descrip=request.getParameter("des");
    	 System.out.println(fid);
    	 if(title!=null) {
        Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url,username,password);
		System.out.println("connection stabilish");
		PreparedStatement stmt;
		if(fid=="")
		{
		stmt =conn.prepareStatement("INSERT INTO film(title,release_year,special_features,rating,language_id,director,description) VALUES (?,?,?,?,?,?,?)");
		stmt.setString(1, title);
		stmt.setInt(2, Integer.parseInt(date));
		stmt.setString(3, special);
		stmt.setString(4, rating);
		stmt.setInt(5, Integer.parseInt(lang));
		stmt.setString(6, diname);
		stmt.setString(7, descrip);
		stmt.executeUpdate();}
		else
		{
			stmt =conn.prepareStatement("UPDATE film SET title=?,release_year=?,special_features=?,rating=?,language_id=?,director=?,description=? WHERE film_id=?");
			stmt.setString(1, title);
			stmt.setInt(2, Integer.parseInt(date));
			stmt.setString(3, special);
			stmt.setString(4, rating);
			stmt.setInt(5, Integer.parseInt(lang));
			stmt.setString(6, diname);
			stmt.setString(7, descrip);
			stmt.setString(8, fid);
			stmt.executeUpdate();
		}
		System.out.println("Succesfully send data");
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