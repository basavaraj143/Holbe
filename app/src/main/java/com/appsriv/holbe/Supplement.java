package com.appsriv.holbe;

/**
 * Created by appsriv-02 on 29/4/16.
 */
public class Supplement
{
    private String supplement_mapping_id;
    private String  supplement_name;
    private String  amount;
    private String  repitition;
    private String  when_time;
    private String  compliance;
    int ProgressBarRes;
    String Colour;

//profile screen
    private int supplement_count;
    private int supplement_completed;
    private int supplement_late;
    private int supplement_missed;


    public int getSupplement_count() {
        return supplement_count;
    }

    public void setSupplement_count(int supplement_count) {
        this.supplement_count = supplement_count;
    }

    public int getSupplement_completed() {
        return supplement_completed;
    }

    public void setSupplement_completed(int supplement_completed) {
        this.supplement_completed = supplement_completed;
    }

    public int getSupplement_late() {
        return supplement_late;
    }

    public void setSupplement_late(int supplement_late) {
        this.supplement_late = supplement_late;
    }

    public int getSupplement_missed() {
        return supplement_missed;
    }

    public void setSupplement_missed(int supplement_missed) {
        this.supplement_missed = supplement_missed;
    }

    private int int_compliance;

    public int getInt_compliance() {
        return int_compliance;
    }

    public void setInt_compliance(int int_compliance) {
        this.int_compliance = int_compliance;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }
    public int getProgressBarRes() {
        return ProgressBarRes;
    }

    public void setProgressBarRes(int progressBarRes) {
        ProgressBarRes = progressBarRes;
    }

    public String getSupplement_mapping_id() {
        return supplement_mapping_id;
    }

    public void setSupplement_mapping_id(String supplement_mapping_id) {
        this.supplement_mapping_id = supplement_mapping_id;
    }

    public String getSupplement_name() {
        return supplement_name;
    }

    public void setSupplement_name(String supplement_name) {
        this.supplement_name = supplement_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRepitition() {
        return repitition;
    }

    public void setRepitition(String repitition) {
        this.repitition = repitition;
    }

    public String getWhen_time() {
        return when_time;
    }

    public void setWhen_time(String when_time) {
        this.when_time = when_time;
    }

    public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }
}
