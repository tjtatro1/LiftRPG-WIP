package com.tatro.thomas.liftrpg.items;

public class SetItem {

    private double weight;
    private int goalReps;
    private int actualReps;
    private boolean isCompleted;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getGoalReps() {
        return goalReps;
    }

    public void setGoalReps(int goalReps) {
        this.goalReps = goalReps;
    }

    public int getActualReps() {
        return actualReps;
    }

    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) { isCompleted = completed; }

    public void setActualReps(int actualReps) {
        this.actualReps = actualReps;
    }

    public SetItem(double weight, int goalReps) {
        this.weight = weight;
        this.goalReps = goalReps;
        this.actualReps = 0;
        this.isCompleted = false;
    }

}
