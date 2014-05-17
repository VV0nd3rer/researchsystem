package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class CalculationEstimates {
    private Vector commonData = new Vector();
    private List<Float> minList = new ArrayList();
    private List<Float> maxList = new ArrayList();
    private float minValue;
    private float maxValue;
    private List<Float> listOfData = new ArrayList();
    private List<Float> listOfDifferences = new ArrayList();
    private List<Float> listOfSquares = new ArrayList();
//    private List<Estimates> listOfEstimates = new ArrayList();
    private float mean = 0;
//    double size;    

    public CalculationEstimates(Object data) 
    {
        commonData = (Vector)data;
        findMaxValue();
        findMinValue();
    }   
    public CalculationEstimates(List<Float> data) {
        this.listOfData = data;
    }
    public float getMinValue() {
        return minValue;
    }
    public float getMaxValue() {
        return maxValue;
    }
    private void findMaxValue() {
        findMaxList();
        maxValue = Collections.max(maxList);
    }
    private void findMinValue() {
        findMinList();
        minValue = Collections.min(minList);
    }
    private void findMaxList() {
        for (int i = 0; i < commonData.size(); i++) {
            maxList.add(Collections.max((List<Float>)commonData.get(i)));
        }
    }
    private void findMinList() {
          for (int i = 0; i < commonData.size(); i++) {
            minList.add(Collections.min((List<Float>)commonData.get(i)));
        }
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
