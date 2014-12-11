/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha.learning;

    import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.text.SupplementaryCharacterData;
import weka.classifiers.Classifier;
 import weka.core.Instances;


public class Classification extends Learning{
   
     Modeling m = new Modeling();
    Validation v =new Validation();
    
public String OfflineClassify(String UnClassifedDataSet, String ModelPath)
{
   
    String final_summary = "";
    
            // load unclassifed data
            Instances unlabeled = m.ARFFloader(UnClassifedDataSet);
          
            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            
            //Load Model
            Classifier ann = m.ReadModel(ModelPath);
          
            //Evaluate Unclassified Data
            System.out.println("\nValdation of Dataset\n==========================\n");
            final_summary += "\nValdation of Dataset\n==========================\n";
            final_summary += v.validate(ann, unlabeled);
            final_summary += "\n\n";
            System.out.println("\nEvaluation of Dataset\n==========================\n");
            final_summary += "\nEvaluation of Dataset\n==========================\n";
            
            final_summary += v.Evaluate(ann, unlabeled);
            return final_summary;
           // JOptionPane.showMessageDialog(null, "Please see console for evaluated results");           
}


public String OnlineClassify(String UnClassifedDataSet, String DatasetToUpdate, String ModelPath)
{
    
      Instances new_dataset = m.ARFFloader(UnClassifedDataSet);

      m.ARFFwriter(DatasetToUpdate, new_dataset);
        
      Instances updated_dataset = m.ARFFloader(DatasetToUpdate);
    
      return Train_Update_ANN(new_dataset, updated_dataset, ModelPath);          
      
      
            
    //  JOptionPane.showMessageDialog(null, "Model Updated, Please see console for evaluated data");            
}



}
