package com.tatro.thomas.liftrpg.items;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.tatro.thomas.liftrpg.entities.GeneralProgress;
import com.tatro.thomas.liftrpg.entities.SLProgress;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Parcel
public class SessionItem extends SugarRecord {

    @Ignore
    public final static String STRONGLIFTS_WORKOUT_A = "StrongLifts Workout A";
    @Ignore
    public final static String STRONGLIFTS_WORKOUT_B = "StrongLifts Workout B";
    @Ignore
    public final static String[] SL_WORKOUT_A_EXERCISES = {"Squat", "Bench Press", "Barbell Row"};
    @Ignore
    public final static String[] SL_WORKOUT_B_EXERCISES = {"Squat", "Overhead press", "Deadlift"};

    private String date;
    private String workoutType;
    private int xp;
    private String notes;
    private ArrayList<ExerciseItem> mainExerciseItems = new ArrayList<>();
    private ArrayList<ExerciseItem> assistanceExerciseItems = new ArrayList<>();

    public ArrayList<ExerciseItem> getMainExerciseItems() {
        return mainExerciseItems;
    }

    public ArrayList<ExerciseItem> getAssistanceExerciseItems() {
        return assistanceExerciseItems;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public SessionItem() {
        date = SimpleDateFormat.getDateInstance().format(new Date());
        xp = 0;
        notes = "";

        List<SLProgress> list = SLProgress.find(SLProgress.class, null, null, null, null, "1");
        SLProgress currentProgress = list.get(0);
        workoutType = currentProgress.getWorkoutType();

        if (workoutType.equals(STRONGLIFTS_WORKOUT_A)) {
            for (int i = 0; i < 3; i++) {
                double weight = currentProgress.getWorkoutAWeights()[i];
                int deloads = currentProgress.getWorkoutADeloads()[i];
                int numberOfSets;
                int repsPerSet;
                String exercise = SL_WORKOUT_A_EXERCISES[i];
                if (exercise.equals("Barbell Row")) {
                    if (deloads <= 1) {
                        numberOfSets = 5;
                        repsPerSet = 5;
                    }
                    else {
                        numberOfSets = 3;
                        repsPerSet = 5;
                    }
                }
                else if (deloads <= 1) {
                    numberOfSets = 5;
                    repsPerSet = 5;
                }
                else if (deloads == 2) {
                    numberOfSets = 3;
                    repsPerSet = 5;
                }
                else {
                    numberOfSets = 3;
                    repsPerSet = 3;
                }
                mainExerciseItems.add(new ExerciseItem(exercise, weight, numberOfSets, repsPerSet));
            }
        }
        else {
            for (int i = 0; i < 3; i++) {
                double weight = currentProgress.getWorkoutBWeights()[i];
                int deloads = currentProgress.getWorkoutBDeloads()[i];
                int numberOfSets;
                int repsPerSet;
                String exercise = SL_WORKOUT_B_EXERCISES[i];
                if (exercise.equals("Deadlift")) {
                    numberOfSets = 1;
                    repsPerSet = 5;
                }
                else if (deloads <= 1) {
                    numberOfSets = 5;
                    repsPerSet = 5;
                }
                else if (deloads == 2) {
                    numberOfSets = 3;
                    repsPerSet = 5;
                }
                else {
                    numberOfSets = 3;
                    repsPerSet = 3;
                }
                mainExerciseItems.add(new ExerciseItem(exercise, weight, numberOfSets, repsPerSet));
            }
        }
    }
}
