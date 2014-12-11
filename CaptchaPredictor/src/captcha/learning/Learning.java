/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package captcha.learning;

/**
 *
 * @author Dv6
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.filters.supervised.attribute.AddClassification;
import weka.filters.unsupervised.attribute.ClassAssigner;
import weka.gui.beans.ClassAssignerCustomizer;


public class Learning
{
        
    Modeling m = new Modeling();
    //Training c = new Training();
    Training t = new Training();
    Validation v = new Validation();
    FileChooser f = new FileChooser();
    public static String ModelSavePath =  System.getProperty("user.dir")+"\\CaptchaANN.model";
    public static String DataSetPath; 
    
    
    //Writing ANN Model
    public String Train_ANN(String ModelPath,String DataPath)
    {
            
        try
        {
            DataSetPath = DataPath;
            ModelSavePath = ModelPath;
    
            String final_summary = "";
            
       // DataSetPath = f.FileChoose("ARFF Files","arff");
        Classifier ann_model = t.ApplyANNClassifer(DataSetPath);
        //ModelSavePath = f.FileChoose("MODEL Files","model");
        m.WriteModel(ModelSavePath, ann_model);
        
        System.out.println("\nValdation of Dataset\n==========================\n");
        final_summary +="\nValdation of Dataset\n==========================\n";
        final_summary += v.validate(ann_model, m.ARFFloader(DataSetPath));

        final_summary += "\n\n";
        
        System.out.println("\nEvaluation of Dataset\n==========================\n");
       
        final_summary +="\nEvaluation of Dataset\n==========================\n";
        final_summary += v.Evaluate(ann_model, m.ARFFloader(DataSetPath));
        
        JOptionPane.showMessageDialog(null, "Model of ANN and Classified data is created at: "+ ModelSavePath);
        
        return  final_summary;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
    }
    
    
    
    //Write SVM Model
    public String Train_SVM(String ModelPath,String DataPath)
    {
        
        try
        {
            DataSetPath = DataPath;
            ModelSavePath = ModelPath;
    
            String final_summary = "";
            
       // DataSetPath = f.FileChoose("ARFF Files","arff");
        Classifier svm_model = t.ApplySVMClassifer(DataSetPath);
        //ModelSavePath = f.FileChoose("MODEL Files","model");
        m.WriteModel(ModelSavePath, svm_model);
        
        System.out.println("\nValdation of Dataset\n==========================\n");
        final_summary +="\nValdation of Dataset\n==========================\n";
        final_summary += v.validate(svm_model, m.ARFFloader(DataSetPath));

        final_summary += "\n\n";
        
        System.out.println("\nEvaluation of Dataset\n==========================\n");
       
        final_summary +="\nEvaluation of Dataset\n==========================\n";
        final_summary += v.Evaluate(svm_model, m.ARFFloader(DataSetPath));
        
        JOptionPane.showMessageDialog(null, "Model of SVM and Classified data is created at: "+ ModelSavePath);
        
        return  final_summary;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
    }
    
    
    public String Train_NaiveBayes(String ModelPath,String DataPath)
    {
           try
        {
            DataSetPath = DataPath;
            ModelSavePath = ModelPath;
    
            String final_summary = "";
            
       // DataSetPath = f.FileChoose("ARFF Files","arff");
        Classifier nb_model = t.ApplyNaiveClassifer(DataSetPath);
        //ModelSavePath = f.FileChoose("MODEL Files","model");
        m.WriteModel(ModelSavePath, nb_model);
        
        System.out.println("\nValdation of Dataset\n==========================\n");
        final_summary +="\nValdation of Dataset\n==========================\n";
        final_summary += v.validate(nb_model, m.ARFFloader(DataSetPath));

        final_summary += "\n\n";
        
        System.out.println("\nEvaluation of Dataset\n==========================\n");
       
        final_summary +="\nEvaluation of Dataset\n==========================\n";
        final_summary += v.Evaluate(nb_model, m.ARFFloader(DataSetPath));
        
        JOptionPane.showMessageDialog(null, "Model of Naive Bayes and Classified data is created at: "+ ModelSavePath);
        
        return  final_summary;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
    }
    
    
    public String Train_j48(String ModelPath,String DataPath)
    {
          try
        {
            DataSetPath = DataPath;
            ModelSavePath = ModelPath;
    
            String final_summary = "";
            
       // DataSetPath = f.FileChoose("ARFF Files","arff");
        Classifier j48_model = t.ApplyJ48Classifer(DataSetPath);
        //ModelSavePath = f.FileChoose("MODEL Files","model");
        m.WriteModel(ModelSavePath, j48_model);
        
        System.out.println("\nValdation of Dataset\n==========================\n");
        final_summary +="\nValdation of Dataset\n==========================\n";
        final_summary += v.validate(j48_model, m.ARFFloader(DataSetPath));

        final_summary += "\n\n";
        
        System.out.println("\nEvaluation of Dataset\n==========================\n");
       
        final_summary +="\nEvaluation of Dataset\n==========================\n";
        final_summary += v.Evaluate(j48_model, m.ARFFloader(DataSetPath));
        
        JOptionPane.showMessageDialog(null, "Model of j48 and Classified data is created at: "+ ModelSavePath);
        
        return  final_summary;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
    }
    
    
    public String Train_Update_ANN(Instances new_dataset,Instances updated_dataset,String ModelPath)
    {
       try
        {
      
            
            
        String final_summary = "";    
        ModelSavePath = ModelPath;    
            
        Classifier ann_update_model = m.UpdateModel(updated_dataset, ModelSavePath);
        
        m.WriteModel(ModelSavePath, ann_update_model);
        
        System.out.println("\nValdation and Evaluation of Updated Dataset\n==========================\n");
        final_summary += "\nValdation and Evaluation of Updated Dataset\n==========================\n";
        final_summary += v.validate(ann_update_model, updated_dataset);
        final_summary +="\n";
        final_summary += v.Evaluate(ann_update_model, updated_dataset);
        
        final_summary += "\n\n";
        System.out.println("\nValdation and Evaluation of New Dataset\n==========================\n");
        final_summary += "\nValdation and Evaluation of New Dataset\n==========================\n";
        final_summary += v.validate(ann_update_model, new_dataset);
        final_summary += v.Evaluate(ann_update_model, new_dataset);
        
        JOptionPane.showMessageDialog(null, "Model is updated with new classifications");
        
        return final_summary;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
    }
    
    
    
}
