package com.tatro.thomas.liftrpg.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.tatro.thomas.liftrpg.items.SessionItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SLProgress extends SugarRecord {
    private String workoutType;

    private AtomicInteger squatWeight;
    private AtomicInteger benchPressWeight;
    private AtomicInteger barbellRowWeight;
    private AtomicInteger overheadPressWeight;
    private AtomicInteger deadliftWeight;

    private AtomicInteger squatFailures;
    private AtomicInteger benchPressFailures;
    private AtomicInteger barbellRowFailures;
    private AtomicInteger overheadPressFailures;
    private AtomicInteger deadliftFailures;

    private AtomicInteger squatDeloads;
    private AtomicInteger benchPressDeloads;
    private AtomicInteger barbellRowDeloads;
    private AtomicInteger overheadPressDeloads;
    private AtomicInteger deadliftDeloads;

    private AtomicInteger workoutAWeights[] = {squatWeight, benchPressWeight, barbellRowWeight};
    private AtomicInteger workoutBWeights[] = {squatWeight, overheadPressWeight, deadliftWeight};
    private AtomicInteger workoutAFailures[] = {squatFailures, benchPressFailures, barbellRowFailures};
    private AtomicInteger workoutBFailures[] = {squatFailures, overheadPressFailures, deadliftFailures};
    private AtomicInteger workoutADeloads[] = {squatDeloads, benchPressDeloads, barbellRowDeloads};
    private AtomicInteger workoutBDeloads[] = {squatDeloads, overheadPressDeloads, deadliftDeloads};

    public String getWorkoutType() {
        return workoutType;
    }

    public double[] getWorkoutAWeights() {
        double arr[] = new double[workoutAWeights.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = workoutAWeights[i].doubleValue();
        }
        return arr;
    }

    public double[] getWorkoutBWeights() {
        double arr[] = new double[workoutBWeights.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = workoutBWeights[i].doubleValue();
        }
        return arr;
    }
    }

    public int[] getWorkoutADeloads() {
        int arr[] = new int[workoutADeloads.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = workoutADeloads[i].get();
        }
        return arr;
    }

    public int[] getWorkoutBDeloads() {
        int arr[] = new int[workoutBDeloads.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = workoutBDeloads[i].get();
        }
        return arr;
    }

    public SLProgress() { }

    public SLProgress(String workoutType, boolean isExercise1Successful, boolean isExercise2Successful,
                      boolean isExercise3Successful) {
        List<SLProgress> list = SLProgress.find(SLProgress.class, null, null, null, "DESC", "1");
        SLProgress lastProgress = list.get(0);
        boolean exerciseSuccess[] = {isExercise1Successful, isExercise2Successful, isExercise3Successful};
        if (workoutType.equals(SessionItem.STRONGLIFTS_WORKOUT_A)) {
            this.workoutType = SessionItem.STRONGLIFTS_WORKOUT_B;
            this.overheadPressWeight = lastProgress.overheadPressWeight;
            this.deadliftWeight = lastProgress.deadliftWeight;
            for (int i = 0; i < 3; i++) {
                if (exerciseSuccess[i]) {
                    this.workoutAWeights[i].set(lastProgress.workoutAWeights[i].doubleValue() + 5.0);
                    this.workoutAFailures[i].set(0);
                } else if (lastProgress.workoutAFailures[i].get() == 2) {
                    this.workoutAWeights[i].set(0);
                    this.workoutAFailures[i].set(0);
                    this.workoutADeloads[i] = lastProgress.workoutADeloads[i] + 1;
                } else {
                    this.workoutAWeights[i] = lastProgress.workoutAWeights[i];
                    this.workoutAFailures[i] = lastProgress.workoutAFailures[i] + 1;
                }
            }
        }
        else {
            this.workoutType = SessionItem.STRONGLIFTS_WORKOUT_A;
            this.benchPressWeight = lastProgress.benchPressWeight;
            this.barbellRowWeight = lastProgress.barbellRowWeight;
            for (int i = 0; i < 3; i++) {
                if (exerciseSuccess[i] && i == 2) {
                    this.workoutBWeights[i] = lastProgress.workoutBWeights[i] + 10;
                    this.workoutBFailures[i] = 0;
                } else if (exerciseSuccess[i]) {
                    this.workoutBWeights[i] = lastProgress.workoutBWeights[i] + 5;
                    this.workoutBFailures[i] = 0;
                }
                else if (lastProgress.workoutBFailures[i] == 2) {
                    this.workoutBWeights[i] = 0;
                    this.workoutBFailures[i] = 0;
                    this.workoutBDeloads[i] = lastProgress.workoutBDeloads[i] + 1;
                } else {
                    this.workoutBWeights[i] = lastProgress.workoutBWeights[i];
                    this.workoutBFailures[i] = lastProgress.workoutBFailures[i] + 1;
                }
            }
        }
    }

    public SLProgress(double squatWeight, double benchPressWeight, double barbellRowWeight,
                      double overheadPressWeight, double deadliftWeight) {
        this.workoutType = SessionItem.STRONGLIFTS_WORKOUT_A;

        this.squatWeight = squatWeight;
        this.benchPressWeight = benchPressWeight;
        this.barbellRowWeight = barbellRowWeight;
        this.overheadPressWeight = overheadPressWeight;
        this.deadliftWeight = deadliftWeight;

        this.squatFailures = 0;
        this.benchPressFailures = 0;
        this.barbellRowFailures = 0;
        this.overheadPressFailures = 0;
        this.deadliftFailures = 0;

        this.squatDeloads = 0;
        this.benchPressDeloads = 0;
        this.barbellRowDeloads = 0;
        this.overheadPressDeloads = 0;
        this.deadliftDeloads = 0;
    }
}
