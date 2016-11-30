package com.image.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Properties;
import javax.imageio.ImageIO;
/**
 *
 * @author Sanjay
 */
public class LBPH 
{
    public static int getRGB(BufferedImage bim,int i,int j)
    {
         Color c=new Color(bim.getRGB(i, j));
         int r=c.getRed();
         int g=c.getGreen();
         int b=c.getBlue();
         int sum=(r+g+b)/3;
         
         return sum;
    }
    public static void readDataForOriginalImg(String filePath,String propFileName) 
    {
        try
        {                   
            FileInputStream fin=new FileInputStream(filePath);
            BufferedImage bim=ImageIO.read(fin);
                       
            FileOutputStream fout=new FileOutputStream(propFileName);
            
            System.out.println((float)bim.getHeight()/bim.getWidth());
            Properties p=new Properties();
            for(int i=1;i<bim.getWidth()-1;i++)
            {
                for(int j=1;j<bim.getHeight()-1;j++)
                {                    
                    //LBPH
                    //ist row 1st col
                    int fistRfirstC=getRGB(bim,i-1,j-1);
                    int fistRsecC=getRGB(bim,i-1,j);
                    int fistRthirdC=getRGB(bim,i-1,j+1);
                    int secRfirstC=getRGB(bim,i,j-1);
                    int center=getRGB(bim,i,j);
                    int secRthirdC=getRGB(bim,i,j+1);
                    int thirdRfirstC=getRGB(bim,i+1,j-1);
                    int thirdRsecC=getRGB(bim,i+1,j);
                    int thirdRthirdC=getRGB(bim,i+1,j+1);
                    
                    char b7=(fistRfirstC>=center)?'1':'0';
                    char b6=(fistRsecC>=center)?'1':'0';
                    char b5=(fistRthirdC>=center)?'1':'0';
                    char b4=(secRthirdC>=center)?'1':'0';
                    char b3=(thirdRthirdC>=center)?'1':'0';
                    char b2=(thirdRsecC>=center)?'1':'0';
                    char b1=(thirdRfirstC>=center)?'1':'0';
                    char b0=(secRfirstC>=center)?'1':'0';
                    
                    String bin=b7+""+b6+""+b5+""+b4+""+b3+""+b2+""+b1+""+b0;
                   
                    
                    int dec=convertToDecimal(bin);
                    
                    p.put(""+i, dec+"");
                }
            }
            p.store(fout, "decimal values");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }      
    
    public static int convertToDecimal(String b)
    {
        long binval=Long.parseLong(b,2);
        return (int)binval;
    }
      
}
