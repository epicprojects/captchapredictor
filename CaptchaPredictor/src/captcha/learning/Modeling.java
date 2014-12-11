/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha.learning;

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
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.filters.supervised.attribute.AddClassification;
import weka.filters.unsupervised.attribute.ClassAssigner;
import weka.gui.beans.ClassAssignerCustomizer;

/**
 *
 * @author Dv6
 */
public class Modeling {
    
    
    public Classifier ReadModel(String ModelPath)
    {
        try {
            Classifier model = (Classifier) SerializationHelper.read(ModelPath);
            return model;
        } catch (Exception ex) {
            Logger.getLogger(Modeling.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
     public boolean WriteModel(String ModelPath, Classifier Classifer_Model)
    {
        try {
            SerializationHelper.write(ModelPath, Classifer_Model);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Modeling.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
     
     
     public Classifier UpdateModel(Instances data,String ModelPath)
     {
        try {
             AddClassification testDataClassify = new AddClassification();
             Classifier model = ReadModel(ModelPath); //(Classifier) SerializationHelper.read("C:\\Users\\Imran\\Desktop\\ntest.model");
             boolean setInputFormat = testDataClassify.setInputFormat(data);
             testDataClassify.setRemoveOldClass(true);
             testDataClassify.setDebug(false);
             testDataClassify.setOutputClassification(false);
             testDataClassify.setOutputDistribution(false);
             testDataClassify.setOutputErrorFlag(false);
             testDataClassify.setClassifier(model);
             model.buildClassifier(data);
             return model;
        } catch (Exception ex) {
            Logger.getLogger(Modeling.class.getName()).log(Level.SEVERE, null, ex);
            return  null;
        }
     }
     
     public void ARFFwriter(String ArffFilePath,Instances data)
     {
        BufferedWriter writer = null;
        try {
            
            writer = new BufferedWriter(new FileWriter(ArffFilePath,true));
            int num = data.numInstances();
            List<Instance> list = (data.subList(0, num));
            
            for (Instance i : list)
            {
                writer.write("\r\n"+i.toString());
               // writer.write("\n");
            }
            
     //       writer.flush();
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Modeling.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Modeling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     }
     
     public Instances ARFFloader(String DataSetPath)
     {  
        
        try {
            
            ArffLoader testSource = new ArffLoader();
            File file = new File (DataSetPath);
            InputStream in = new FileInputStream(file);
            testSource.setUseRelativePath(true);
            testSource.setSource(in);
            Instances Data = testSource.getDataSet();
            Data.setClassIndex(Data.numAttributes()-1);
            in.close();
            
            return Data;
            
        } catch (Exception ex) {
            Logger.getLogger(Modeling.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
     }
     
    
    
}
