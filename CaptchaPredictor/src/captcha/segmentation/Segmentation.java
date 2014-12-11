
package captcha.segmentation;

import captcha.main.MainClass;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.sourceforge.javaocr.gui.*;

public class Segmentation {
    
    File in;
    File out;
 
    String captcha = "\\captcha.bmp";
    String path = System.getProperty("user.dir")+"\\Res";
    String segment_path = System.getProperty("user.dir")+"\\Seg";
    File[] listOfSegments;
    
    public static List<BufferedImage> SEGMENTS = new ArrayList<>();

      
    
    public List<BufferedImage> Segment(String srcpath,Integer OutputCharDimensionsX,Integer OutputCharDimensionsY)
    {
        File seg_folder = new File(segment_path);
        File res_folder = new File(path);
    //    File f = new File(path);
        listOfSegments = null;
        //SEGMENTS.clear();
        
//        if(f.exists())
//        {
//          WriteFile(image,path+captcha);
//        }
//        else
//        {
//          new File(path).mkdir();   
//          
//          WriteFile(image,path+captcha);
//        }
        
        
         if(!res_folder.exists()) {
          new File(path).mkdir();
        }
        
               if(!seg_folder.exists()) {
          new File(segment_path).mkdir();
        }

        
        in  = new File(srcpath);
       
        out = new File(segment_path);

        
        
        
       GUIController guiController = new GUIController();
       guiController.extractChars(in,out,OutputCharDimensionsX,OutputCharDimensionsY);
       

           
       
       listOfSegments = seg_folder.listFiles(); 
     //  int i=0;
       for(File segment : listOfSegments)
       {
            try {
                SEGMENTS.add(ImageIO.read(segment));
               // WriteFile(SEGMENTS.get(i), "D:\\SEG\\seg_"+i+".bmp");
               // i++;
                //JOptionPane.showMessageDialog(null, segment.toPath().toString());
            } catch (IOException ex) {
                Logger.getLogger(Segmentation.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       
      // JOptionPane.showMessageDialog(null, "Done!");
       new File(path+captcha).delete();
       
       for(File segment : listOfSegments) {
            segment.delete();
        }
 
       return SEGMENTS;
      
    }
    
   public void WriteFile(BufferedImage image,String Path)
    {
         try 
               {
                    ImageIO.write(image,"bmp",new File(Path));
               } 
         catch (IOException ex) 
               {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
    
}
