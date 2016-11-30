package com.image.Controller;

import java.io.File;

import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Imgproc.*;

import com.googlecode.javacv.cpp.opencv_contrib.FaceRecognizer;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_core.MatVector;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;

public class Demo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Demo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opencvpath = System.getProperty("user.dir") + "\\";
		//System.out.println("opencvpath: " + opencvpath);
		String libPath = System.getProperty("java.library.path") + ";";
		//System.out.println("libPath: " + libPath);
		System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
		
		String trainingDir = "D:\\train";
	        Mat testImage= Imgcodecs.imread("D:\\photo.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
	        File root = new File(trainingDir);
	        
	        FilenameFilter imgFilter = new FilenameFilter() {
	            public boolean accept(File dir, String name) {
	                name = name.toLowerCase();
	                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
	            }
	        };
	        File[] imageFiles = root.listFiles(imgFilter);

	        MatVector images = new MatVector(imageFiles.length);

	        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
	        IntBuffer labelsBuf = labels.getIntBuffer();

	        int counter = 0;
	        
	        for (File image : imageFiles) {
	            Mat img = Imgcodecs.imread(image.getAbsolutePath());

	            int label = Integer.parseInt(image.getName().split("\\-")[0]);

	            images.put(counter, img);

	            labelsBuf.put(counter, label);

	            counter++;
	        }
	        //FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
	        //FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
	         FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();

	        faceRecognizer.train(images, labels);

	        int predictedLabel = faceRecognizer.predict(testImage);

	        System.out.println("Predicted label: " + predictedLabel);
	        
	        
	        String trainingDir = "D:\\train";
	        Mat testImage = Imgcodecs.imread("D:\\photo.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);

	        File root = new File(trainingDir);

	        FilenameFilter pngFilter = new FilenameFilter() {
	          public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".png");
	          }
	        };

	        File[] imageFiles = root.listFiles(pngFilter);

	        MatVector images = new MatVector(imageFiles.length);

	        int[] labels = new int[imageFiles.length];

	        int counter = 0;
	        int label;

	        Mat img;
	        Mat grayImg;

	        for (File image : imageFiles) {
	          // Get image and label:
	          img = Imgcodecs.imread(image.getAbsolutePath());
	          label = Integer.parseInt(image.getName().split("\\-")[0]);
	          // Convert image to grayscale:
	          grayImg = new Mat(img.width(), img.height(), img.type());
	          Imgproc.cvtColor(img, grayImg, Imgproc.COLOR_RGB2GRAY);
	          // Append it in the image list:
	         images.put(0, img);
	          // And in the labels list:
	          labels[counter] = label;
	          // Increase counter for next image:
	          counter++;
	        }

	        FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
	        // FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
	        // FaceRecognizer faceRecognizer = createLBPHFaceRecognizer()

	        faceRecognizer.train(images, labels);

	        // Load the test image:
	        IplImage greyTestImage = IplImage.create(testImage.width(), testImage.height(), testImage.depth(), 1);
	        //cvtColor(testImage, greyTestImage, CV_BGR2GRAY);

	        // And get a prediction:
	        int predictedLabel = faceRecognizer.predict(greyTestImage);
	        System.out.println("Predicted label: " + predictedLabel);
	        
	}

}
