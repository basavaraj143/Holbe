package com.appsriv.holbe;

/**
 * Created by appsriv-02 on 29/4/16.
 */
public class Food
{

    private String food_mapping_id;
    private String food_name;
    private String when;
    private String compliance;

    int ProgressBarRes;
    String Colour;
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

    public String getFood_mapping_id() {
        return food_mapping_id;
    }

    public void setFood_mapping_id(String food_mapping_id) {
        this.food_mapping_id = food_mapping_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }
}
