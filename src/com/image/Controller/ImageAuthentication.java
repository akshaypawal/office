package com.image.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;




public class ImageAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isMultipart;

	public ImageAuthentication() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		long size = 0;
		
		PrintWriter pw = response.getWriter();
		
		System.out.println("in image authentication");
		
		long s=0;
		
		boolean isMultipart;
		String filename = null;
		isMultipart = ServletFileUpload.isMultipartContent(request);
	
		System.out.println("isMultipart: "+isMultipart);
		
		String filepath = "E:\\Akshay Files\\Project Data\\upload\\";    //setting path of output file
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setRepository(new File("D:\\temp"));
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List fileItems;
		try {
			
			//uplaoding file to specified path
												 			 
					 System.out.println("else");
					 	fileItems = upload.parseRequest(request);
					 	System.out.println(fileItems);
					 	Iterator i = fileItems.iterator();
		      
					 	while(i.hasNext()){
					 		System.out.println("obj type: "+i.getClass());   
					 		FileItem fi = (FileItem) i.next();
					 		System.out.println("filename"+filename);
					 		if(!fi.isFormField()){
					 			filename = fi.getName();
					 			size = fi.getSize();
					 			
					 			if(size == 0)
					 			{
					 				break;
					 			}
					 			
					 			System.out.println("filename->>"+filename);
					 			System.out.println("file size->>"+size);
		    		  fi.write(new File(filepath+filename));
		    	  		    	  
		    	  System.out.println("img uploaded "+filename);
		    	  	    	  
		    	  request.setAttribute("filename", filename);
		    	  
		    	  RequestDispatcher rd = request.getRequestDispatcher("facedetect");  //facedetect
		    	  rd.forward(request, response);
						
					 		
					 		}
					 	}	
					 	
					 /*	pw.write("please select image to upload");
				
					 	response.sendRedirect("ImageAuthorization.jsp");*/
				 	
		}
		 catch (Exception e) {
	
			e.printStackTrace();
		}
	    		
	
				
	}		
				
		
	}


