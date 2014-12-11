package captcha.denoise;

import ij.ImagePlus;
import ij.io.Opener;
import ij.plugin.filter.RankFilters;
import ij.process.*;
import java.awt.image.BufferedImage;


public class Denoising {
   
    
    public synchronized BufferedImage Denoise(String URLorFILE,Float threshold,Double radius,Double second_radius,Double median_radius,int cropX, int cropY, int cropWidth, int cropHeight)
    {
        ImagePlus imp;     
        Opener opener = new Opener();
        RankFilters filter = new RankFilters();
        ImageProcessor ip;    
        ImageConverter ic;
        ImageConverter icov;
      
       try
       {
       //===============From URL===================
       //imp = opener.openImage(URLorFILE);  
        
      //================From File===================    
      imp = opener.openImage(URLorFILE);     // Loads Image
    
      imp.setImage(cropAndResize(imp, cropX,cropY,cropWidth,cropHeight));  //Crop and Resize this shit
      
      icov = new ImageConverter(imp);    
      icov.convertToGray16();
    //  icov.convertRGBtoIndexedColor(256);              // Converts to Gray Scale 32bit
     
      ip = imp.getProcessor();
      filter.rank(ip,radius,RankFilters.OUTLIERS,1,threshold);         //Apply Filter Remove noise via 'Remove Outliers' >
      filter.rank(ip,second_radius,RankFilters.OUTLIERS,1,threshold);
      imp.updateAndDraw();
      
      filter.rank(ip, median_radius, RankFilters.MEDIAN);   //Apply Median Filter to Strong the image
    
   //   imp.show();       //Shows ImageJ Api image window
      
      
      return imp.getBufferedImage();
      
     
       }
       catch (Exception ex)
       {
           System.out.println(ex.getMessage());
           return null;
       }
    }
    
    
    public synchronized static BufferedImage cropAndResize(ImagePlus imp,int cropX,int cropY, int targetWidth, int targetHeight) throws Exception
    {
        try
        {
            ImageProcessor ip = imp.getProcessor();
            ip.setInterpolationMethod(ImageProcessor.BILINEAR);
            ip.setRoi(cropX, cropY, targetWidth, targetHeight);
            ip = ip.crop();
            return ip.getBufferedImage();
        } 
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
  }
    
}
