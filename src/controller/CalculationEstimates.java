package controller;

import java.util.List;
import java.util.ArrayList;

//class Estimates {
//    private float fuzzyEstimate;
//    private String linguisticEstimate;
//    public void setFuzzyEstimate(float estimate) {
//        fuzzyEstimate = estimate;
//    }
//    public float getFuzzyEstimate() {
//        return fuzzyEstimate;
//    }
//    public void setLinguisticEstimate(String estimate) {
//        linguisticEstimate = estimate;
//    }
//    public String getLinguisticEstimate() {
//        return linguisticEstimate;
//    }
//}

public class CalculationEstimates {
    private List<Float> listOfData = new ArrayList();
    private List<Float> listOfDifferences = new ArrayList();
    private List<Float> listOfSquares = new ArrayList();
//    private List<Estimates> listOfEstimates = new ArrayList();
    private float mean = 0;
//    double size;    

    public CalculationEstimates(List<Float> data) 
    {
        this.listOfData = data;
//        size = data.length;
    }   

    float findMean()
    {
        float sum = 0;
        for(float data : listOfData)
            sum += data;
        System.out.println("mean: " + sum/listOfData.size());
        return sum/listOfData.size();
    }
  void setDifference() {
       mean = findMean();
       for(float data :listOfData) {
           float difference = data - mean;
           listOfDifferences.add(difference);
       }
       
       //return listOfDifferences;
   }
   void setSquares() {
       setDifference();
       for(float difference :listOfDifferences) {
           float square = difference*difference;
           listOfSquares.add(square);
       }
   }
    public float getMean() {
        System.out.println("getMean: " + mean);
        return mean;
    }
    public float getStdDev()
    {
        setSquares();
        float sum = 0;
        for (float square :listOfSquares) 
            sum += square;
        float res = sum/(listOfData.size() - 1);
        System.out.println("standartDeviation: " + (float)Math.sqrt(res));
        return (float)Math.sqrt(res);
    }
}
