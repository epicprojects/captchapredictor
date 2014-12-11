
package captcha.main;

import captcha.denoise.*;
import captcha.gui.ClassifyForm;
import captcha.gui.MainForm;
import captcha.learning.Classification;
import captcha.learning.Learning;
import captcha.learning.Training;
import captcha.segmentation.Segmentation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class MainClass {

  Training t = new Training();
    public static void main(String[] args) {
        
        
        Learning l = new Learning();
        Classification  c = new Classification();
       ClassifyForm cf = new ClassifyForm();
       
//       l.Train_ANN("C:\\Users\\Dv6\\Documents\\NetBeansProjects\\CAPTCHA_CRACKING\\CaptchasNET.model", "C:\\Users\\Dv6\\Documents\\NetBeansProjects\\CAPTCHA_CRACKING\\CaptchasNET.arff");
          
        // c.OfflineClassify("C:\\Users\\Dv6\\Desktop\\UNzc.arff");
       // c.OnlineClassify("C:\\Users\\Dv6\\Desktop\\UNzc.arff", "C:\\Users\\Dv6\\Desktop\\zc.arff");
            
        
        
        try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");//UIManager.getCrossPlatformLookAndFeelClassName());                

                    } catch (Exception e) {
                      System.err.println("Look and feel not set.");
                    }
        
       MainForm start = new MainForm();
       
        // Determine the new location of the window
 
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - start.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - start.getHeight()) / 2);
    start.setLocation(x, y);
       
      
    start.setResizable(false);
       //Segmentation seg = new Segmentation();
       //Denoising d = new Denoising();   
       start.show();
        
       
//        
//        
//      try {
//
//           //LATEST WORKING DENOISE========================  
//         
//          BufferedImage img = d.Denoise("d:\\DS\\1.bmp", (float)50, (double)5, (double)0.5, (double)0, 5, 5, (240-10), 55);
//     
//          //PARAM   file, denoise_threshold, radius, second_radius, median_radius, cropX, cropY, cropWidth, cropHeight
//          //=================================================
// 
//          
//          //Image Writer Code===============================
//          /*
//           if(img == null)
//           {
//               System.exit(0);
//           }
//           else
//           {
//           
//           File outputfile = new File("d:\\saved.png");
//            
//           try 
//               {
//                    ImageIO.write(img,"png",outputfile);
//               } 
//               catch (IOException ex) 
//               {
//                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//               }
//
//           }
//           * =====================================================
//          */
//            
//            
//         //Segmentation
//          
//         seg.Segment(img, 16);
//         //================================================================
//            
//            
//            
//            
//        } catch (Exception ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      
//        System.out.println("Finished");
//       // System.exit(0);
    }
    
    
    
}
