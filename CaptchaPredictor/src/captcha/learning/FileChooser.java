/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha.learning;

import captcha.gui.*;
//import com.sun.org.apache.xml.internal.security.utils.ElementCheckerImpl;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Dv6
 */
public class FileChooser {
   
    public String FileChoose(String FilterString, String FilterExtention)
    {

          JFileChooser fileopen = new JFileChooser();
          FileFilter filter = new FileNameExtensionFilter(FilterString,FilterExtention);
          fileopen.setFileFilter(filter);
          fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
          
          
          int ret = fileopen.showDialog(null, "Select File");

            if (ret == JFileChooser.APPROVE_OPTION)
            {
              File file = fileopen.getSelectedFile();
              return file.toString();
            }
         else
            {
               // System.exit(0);
                return "";
            }

       //return null;
    }
    
    
    public String SaveFileChoose(String FilterString, String FilterExtention)
    {

          JFileChooser fileopen = new JFileChooser();
          FileFilter filter = new FileNameExtensionFilter(FilterString,FilterExtention);
          fileopen.setFileFilter(filter);
          fileopen.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
          
          
          int ret = fileopen.showDialog(null, "Select Path");

            if (ret == JFileChooser.APPROVE_OPTION)
            {
              File file = fileopen.getSelectedFile();
              return file.toString();
            }
         else
            {
                //System.exit(0);
                return  "";
            }

     //  return null;
    }

}
