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
@WebServlet("/delete")
public class Datadelete extends HttpServlet {
   /**
    * @see HttpServlet#HttpServlet()
    */
   public Datadelete(){
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
    	 String id=request.getParameter("film_id");
    	 System.out.println(id);
    	 if(id!=null) {
        Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url,username,password);
		System.out.println("connection stabilish");
		String[] innerArray = id.split(",");
		StringBuilder DELETE = new StringBuilder("UPDATE film SET isdeleted =1 where film_id in (");
		for (int i = 0; i < innerArray.length; i++) {
		if (i > 0) {DELETE.append(",");}DELETE.append("?");}DELETE.append(")");
				String psmt= DELETE.toString();
		PreparedStatement stmt;
		stmt =conn.prepareStatement(psmt);
		for (int i = 0; i < innerArray.length; i++) {
			stmt.setInt(i+1, Integer.parseInt(innerArray[i]));
		}
		stmt.executeUpdate();}
		System.out.println("Succesfully send data");
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