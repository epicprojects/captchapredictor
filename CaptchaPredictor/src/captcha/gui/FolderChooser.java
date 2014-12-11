/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha.gui;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Dv6
 */
public class FolderChooser {
   
    public String FolderChoose()
    {

          JFileChooser fileopen = new JFileChooser();
          fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int ret = fileopen.showDialog(null, "Select folder");

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

      // return null;
    }

}
