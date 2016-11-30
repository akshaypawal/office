package com.image.Controller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	 public static boolean validate(String name,String password) {        
	        boolean status = false;
	        Connection conn = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;

	      
	        try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            conn = DriverManager
	                    .getConnection("jdbc:mysql://localhost:3306/akdb","root","root");
	            System.out.println("connectoin open");

	            pst = conn.prepareStatement("select * from register where name= ?");
	            pst.setString(1, name);
	         
	            rs = pst.executeQuery();
	                  
	            
	            
	            
	           // pst.setString(1, id);
	          //  pst.setString(2, name);

	            rs = pst.executeQuery();
	            status = rs.next();
	            
	        
	 

	            
	            
	            
	            
	            
	            
	        /* if(rs.next())
	         {
	         String str = rs.getString("address");
	          System.out.println("address dao "+str);
	            
	            Blob key = rs.getBlob("password");
	           // System.out.println(key);
	            int blobLength = (int) key.length();  
	            byte[] keyBytes = key.getBytes(1, blobLength);
                String pass = RC4_Main.decryptPwd(keyBytes);   //decrypted password
	            System.out.println("pass "+pass);*/
	         
	            
	         
	         
	         
	         
	            
	         
	         
	            
	            
	           
	           
	            
	        //    status = rs.next();
	            
	        
	 

	            
	            
	            
	        } catch (Exception e) {
	            System.out.println(e);
	        
	            }
	        
	        return status;
	    }

}
