package com.appsriv.holbe;

public class Workout

{
    /*


     "workout_mapping_id":"5",
      "workout_name":"cycling",
      "reps":"23",
      "sets":"3",
      "weight":"45% of body weight",
      "compliance":"100"

*/
    private int int_compliance;
    private String workout_mapping_id;
    private  String workout_name;
    private String reps;
    private String sets;
    private String weight;
    private String compliance;
    private String colour;
    private int progressBarRes;

    public int getProgressBarRes() {
        return progressBarRes;
    }

    public void setProgressBarRes(int progressBarRes) {
        this.progressBarRes = progressBarRes;
    }

    public int getInt_compliance() {
        return int_compliance;
    }

    public void setInt_compliance(int int_compliance) {
        this.int_compliance = int_compliance;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getWorkout_mapping_id() {
        return workout_mapping_id;
    }

    public void setWorkout_mapping_id(String workout_mapping_id) {
        this.workout_mapping_id = workout_mapping_id;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }
}