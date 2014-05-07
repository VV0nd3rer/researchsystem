package model;

public class Estimates {  
    private int id;
    private float fuzzyEstimate;
    private String linguisticEstimate;
    public void setFuzzyEstimate(float estimate) {
        fuzzyEstimate = estimate;
    }
    public float getFuzzyEstimate() {
        return fuzzyEstimate;
    }
    public void setLinguisticEstimate(String estimate) {
        linguisticEstimate = estimate;
    }
    public String getLinguisticEstimate() {
        return linguisticEstimate;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }  
}
