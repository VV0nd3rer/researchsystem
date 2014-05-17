package model;

public class Estimates { 
    private int riskId;
    private int criteriaId;
    private int dlpId;
    private float fuzzyEstimate;
    private String linguisticEstimate;
    private float mean;
    public void setMean(float mean) {
        this.mean = mean;
    }
    public float getMean() {
        return mean;
    }
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
    public void setCriteriaId(int id) {
        criteriaId = id;
    }
    public int getCriteriaId() {
        return criteriaId;
    }  
    public void setDlpId(int id) {
        dlpId = id;
    }
    public int getDlpId() {
        return dlpId;
    }
    public void print() {
        System.out.println("dlp id: " + dlpId);
        System.out.println("criteria  id: " + criteriaId);
        System.out.println("fuzzy estimate: " + fuzzyEstimate);
    }
}
