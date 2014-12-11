/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha.learning;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;

/**
 *
 * @author Dv6
 */
public class Training {
    
    
    
    
    // ANN Classifer
    public Classifier ApplyANNClassifer(String DataSetPath)
    {
        
        //J48 cModel = new J48();  
       
        try {
           MultilayerPerceptron cModel = new MultilayerPerceptron();
           
           ConverterUtils.DataSource source = new ConverterUtils.DataSource(DataSetPath);
           Instances data = source.getDataSet();
           data.setClassIndex(data.numAttributes()-1);

           cModel.setOptions(new String[] {""});  
           
           
           cModel.setLearningRate(0.3);
           cModel.setMomentum(0.2);
           cModel.setSeed(0);
           cModel.setTrainingTime(500);
           cModel.setValidationSetSize(0);
           cModel.setValidationThreshold(20);
           cModel.setNominalToBinaryFilter(true);
           cModel.setReset(true);
           cModel.setNormalizeNumericClass(true);
           cModel.setNormalizeAttributes(true);
           cModel.setHiddenLayers("a");
           cModel.setDecay(false);
           cModel.setDebug(false);
           cModel.setAutoBuild(true);
           cModel.setGUI(true);
           
           
           cModel.buildClassifier(data);
           //cModel.setGUI(true);
           
           return cModel;
           
        
        } catch (Exception ex) {
            Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
    
    
    //SVM Classifer
      public Classifier ApplySVMClassifer(String DataSetPath)
    {
        
       
        try {
            
           SMO cModel = new SMO();
           ConverterUtils.DataSource source = new ConverterUtils.DataSource(DataSetPath);
           Instances data = source.getDataSet();
           data.setClassIndex(data.numAttributes()-1);  
           
           cModel.buildClassifier(data);
           
           return cModel;
           
        
        } catch (Exception ex) {
            Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
      
      
      
      
      
    //Naive Bayes Classifer
       public Classifier ApplyNaiveClassifer(String DataSetPath)
    {
        
     
       
        try {
           NaiveBayes cModel = new NaiveBayes();
           
           ConverterUtils.DataSource source = new ConverterUtils.DataSource(DataSetPath);
           Instances data = source.getDataSet();
           data.setClassIndex(data.numAttributes()-1);
           
           
           cModel.buildClassifier(data);
           //cModel.setGUI(true);
           
           return cModel;
           
        
        } catch (Exception ex) {
            Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
       
       
       
       
       //Decision Tree Classifer
         public Classifier ApplyJ48Classifer(String DataSetPath)
    {
        
       
        try {
           
           J48 cModel = new J48();             
           ConverterUtils.DataSource source = new ConverterUtils.DataSource(DataSetPath);
           Instances data = source.getDataSet();
           data.setClassIndex(data.numAttributes()-1);
           
           cModel.buildClassifier(data);
           
           return cModel;
           
        
        } catch (Exception ex) {
            Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
         
      
}
