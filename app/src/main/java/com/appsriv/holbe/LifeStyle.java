package com.appsriv.holbe;

/**
 * Created by appsriv-02 on 29/4/16.
 */
public class LifeStyle
{
    private String lifestyle_mapping_id;
    private String lifestyle_name;
    private String time;
    private String repitition;
    private String when;
    private String compliance;
    int ProgressBarRes;
    String Colour;
    private int int_compliance;

    //profile screen
    private int lifestyle_count;
    private int lifestyle_completed;
    private int lifestyle_late;
    private int lifestyle_missed;

    public int getLifestyle_count() {
        return lifestyle_count;
    }

    public void setLifestyle_count(int lifestyle_count) {
        this.lifestyle_count = lifestyle_count;
    }

    public int getLifestyle_completed() {
        return lifestyle_completed;
    }

    public void setLifestyle_completed(int lifestyle_completed) {
        this.lifestyle_completed = lifestyle_completed;
    }

    public int getLifestyle_late() {
        return lifestyle_late;
    }

    public void setLifestyle_late(int lifestyle_late) {
        this.lifestyle_late = lifestyle_late;
    }

    public int getLifestyle_missed() {
        return lifestyle_missed;
    }

    public void setLifestyle_missed(int lifestyle_missed) {
        this.lifestyle_missed = lifestyle_missed;
    }

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
    public String getLifestyle_mapping_id() {
        return lifestyle_mapping_id;
    }

    public void setLifestyle_mapping_id(String lifestyle_mapping_id) {
        this.lifestyle_mapping_id = lifestyle_mapping_id;
    }

    public String getLifestyle_name() {
        return lifestyle_name;
    }

    public void setLifestyle_name(String lifestyle_name) {
        this.lifestyle_name = lifestyle_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepitition() {
        return repitition;
    }

    public void setRepitition(String repitition) {
        this.repitition = repitition;
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
