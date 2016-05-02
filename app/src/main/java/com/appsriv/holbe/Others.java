package com.appsriv.holbe;

/**
 * Created by appsriv-02 on 29/4/16.
 */
public class Others
{
    private String others_mapping_id;
    private String others_name;
    private String duration;
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
    public String getOthers_mapping_id() {
        return others_mapping_id;
    }

    public void setOthers_mapping_id(String others_mapping_id) {
        this.others_mapping_id = others_mapping_id;
    }

    public String getOthers_name() {
        return others_name;
    }

    public void setOthers_name(String others_name) {
        this.others_name = others_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }
}
