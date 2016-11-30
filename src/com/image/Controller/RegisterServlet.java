package com.image.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;






import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.image.Controller.RC4;

//@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterServlet() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {
		
		PrintWriter out=response.getWriter();
		 response.setContentType("text/html");
        String uname=request.getParameter("name");
        String id=request.getParameter("id");
        String pwd=request.getParameter("password");  //do not insert
       
        byte[] key = "Key".getBytes();
		RC4 rc4 = new RC4(key);
    	String cipherText = rc4.encrypt(pwd);
        
        String bdate=request.getParameter("date");
        String addr=request.getParameter("address");
        String city=request.getParameter("city");
        String state=request.getParameter("state");
        
        
        PreparedStatement ps=null;
        Connection con;
        
        try{
       Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akdb","root","root");
     
      
         ps= con.prepareStatement("insert into register values (?,?,?,?,?,?,?)");
           
            ps.setString(1,uname);
            ps.setString(2,id);
           
            ps.setString(3,cipherText);
           
            
            ps.setString(4,bdate);
            ps.setString(5,addr);
            ps.setString(6,city);
            ps.setString(7,state);
          
               int insertResult = ps.executeUpdate();
             if (insertResult!=0)
            {
                 out.println("Successfully registered");
              
            }
             
             response.sendRedirect("login.jsp");
   
        }
        catch(Exception e)
        {
        	out.println("failed");
        }
        
   		
	  }

}
