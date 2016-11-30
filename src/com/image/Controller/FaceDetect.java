package com.image.Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public class FaceDetect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FaceDetect() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Demo Servlet");
		
		//final String XML_FILE="haarcascade_frontalface_default.xml";
		//System.out.println("file address is "+XML_FILE);
		
		PrintWriter pw = response.getWriter();
		String newfilename = (String) request.getAttribute("filename");
		System.out.println("file name: "+newfilename);
		System.out.println("name of file"+newfilename);
		String opencvpath = System.getProperty("user.dir") + "\\";
		
		System.out.println("opencvpath: " + opencvpath);
		String libPath = System.getProperty("java.library.path") + ";";
		System.out.println("libPath: " + libPath);
		System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
						
		/*URL path=getClass().getResource("/resources/lena.jpg");
		System.out.println(" image Url1"+path);
		String path1=path.toString();
		System.out.println(" image Url"+path1);*/
		String path="";
		//CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("/haarcascade_frontalface_default.xml").getPath());
		CascadeClassifier faceDetector = new CascadeClassifier("E:\\Akshay Files\\Project Data\\haarcascade_frontalface_default.xml");

		System.out.println("xml file"+faceDetector);
				
		Mat image = Imgcodecs.imread("E:\\Akshay Files\\Project Data\\upload\\"+newfilename,Imgcodecs.CV_LOAD_IMAGE_COLOR);
		 //Mat image = Imgcodecs.imread(getClass().getResource("/lena.jpg").getPath());
			
		System.out.println("image= "+image);
				 MatOfRect faceDetections = new MatOfRect();
				
		    faceDetector.detectMultiScale(image, faceDetections);
		   
		    Rect rect_Crop=null;
		    
		    int no_0f_faces = faceDetections.toArray().length;
		 //   System.out.println(String.("Detected %s faces", faceDetections.toArray().length));
		   System.out.println("No of detectformated faces :"+no_0f_faces);
		    
		   if(no_0f_faces >= 3)
		   {
			   try {
				throw new Exception("Face detection");
			} catch (Exception e) {
				
				System.out.println("More than one person is not allowed ");
				e.printStackTrace();
			}
		   }
		   
		   for (Rect rect : faceDetections.toArray()) {
		        Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		        Imgcodecs.imwrite("E:\\Akshay Files\\Project Data\\face\\face_person.jpg", image);
		        rect_Crop = new Rect(rect.x, rect.y, rect.width, rect.height);
		    }
		    String filename = "faceDetection.png";
		    System.out.println(String.format("Writing %s", filename));
		    Imgcodecs.imwrite("E:\\Akshay Files\\Project Data\\rectface\\"+filename, image);
		    System.out.println("face detected");
		    		    
		    Mat image_roi = new Mat(image,rect_Crop);
		    Imgcodecs.imwrite("E:\\Akshay Files\\Project Data\\face\\face_person_2.jpg",image_roi);
		    
		    System.out.println("face crop and save");
		    Desktop.getDesktop().open(new File("E:\\Akshay Files\\Project Data\\face"));
		    		   	    		    
		    System.out.println("Exit from Demo Servlet");
		   
		    pw.write("face detected.Shown as in folder :");
		    response.sendRedirect("ImageAuthorization.jsp");
	
		}

}
