package com.image.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.image.Controller.RC4;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uname = request.getParameter("name");
		String pass = request.getParameter("password");
		 
		
	
	
	//	String decrypted_pass = RC4_Main.decrypted(encrypted);			//call to decrypt data
		 
		
//		System.out.println("decrypted pass "+decrypted_pass);
		
		try{
			
			PrintWriter pw = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			ResultSet rs = null;
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/akdb","root","root");
			

			
			PreparedStatement pst = con.prepareStatement("select * from register where name = ?");
			pst.setString(1, uname);
			
	

		
	
			rs = pst.executeQuery();
			
			if(rs.next())
			{			
			
			String enc_pass = rs.getString("password");
			
			System.out.println("password from table "+enc_pass);		
			
			
						
			
			byte[] key = "Key".getBytes();
			RC4 rc4 = new RC4(key);			
			String decrypted = rc4.decrypt(enc_pass);   //call to decrypt data
			 
			System.out.println("decrypted pass in login servlet "+decrypted);
			
			
					if(decrypted.equals(pass))
					{
						response.sendRedirect("ImageAuthorization.jsp");
					}
					else
					{
						pw.write("password is incorrect");
					
					}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		
		}
	
		
		
		
	}

}
