
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha.learning;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Evaluation;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.NominalPrediction;
import weka.core.FastVector;
import weka.core.Instances;


public class Validation {
    
    
    public String validate(Classifier classifer,Instances instances)
    {
        try {
            Evaluation eTest = new Evaluation(instances);
                eTest.evaluateModel(classifer, instances);
               System.out.println(eTest.toSummaryString());
               return eTest.toSummaryString();
        } catch (Exception ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            
    }
    
    
     public String Evaluate(Classifier model,Instances instances)
    {
        try {
            String Summary = "";
            Evaluation eTest = new Evaluation(instances);
                eTest.evaluateModel(model,instances);
                       FastVector f = new FastVector();
                       f = eTest.predictions();      
                   int i=1;
                  // System.out.println("\n\nEvaluation:\n================\n");
                       for(Object o : f)
                              {
                                  NominalPrediction n = (NominalPrediction) o;
                                  
//                                  if(n.actual()==n.predicted())
//                                  {
                                         System.out.println(i+"   Actual: "+instances.classAttribute().value((int)n.actual()) +"   Predicted: "+instances.classAttribute().value((int)n.predicted()));
                                         Summary += i+"   Actual: "+instances.classAttribute().value((int)n.actual()) +"   Predicted: "+instances.classAttribute().value((int)n.predicted())+"\n";
                                         
                                     
                                   
                                         
//                                  }
//                                  else
//                                  {
//                                      System.out.println(i+"   Actual: "+instances.classAttribute().value((int)n.actual()) +"   Predicted: "+instances.classAttribute().value((int)n.predicted())+"   Incorrect");
//                                      Summary += i+"   Actual: "+instances.classAttribute().value((int)n.actual()) +"   Predicted: "+instances.classAttribute().value((int)n.predicted())+"   Incorrect\n";
//                                  }
                                  
                                  i++;   
                               }
                       
                       //clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel)
                              return Summary;
                               
        } catch (Exception ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            
    }
    
}
