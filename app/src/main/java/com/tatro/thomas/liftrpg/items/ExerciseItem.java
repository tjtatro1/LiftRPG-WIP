package com.tatro.thomas.liftrpg.items;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.rey.material.widget.Button;
import com.tatro.thomas.liftrpg.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import nl.dionsegijn.steppertouch.StepperTouch;

public class ExerciseItem extends AbstractItem<ExerciseItem, ExerciseItem.ViewHolder> {
    private String exerciseName;
    private double weight;
    private int numberOfSets;
    private int repsPerSet;
    private View view;
    private ArrayList<SetItem> setItems;

    private int setsAddedCount = 0;

    public ExerciseItem(String exerciseName, double weight, int numberOfSets, int repsPerSet) {
        this.exerciseName = exerciseName;
        this.weight = weight;
        this.numberOfSets = numberOfSets;
        this.repsPerSet = repsPerSet;
        this.setItems = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            setItems.add(new SetItem(weight, repsPerSet));
        }
    }

    public void setView(View view) {

    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.exercise_layout;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_workout;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(final ViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);

        //bind our data
        final String weightWithDecimal = "" + weight;
        final String weightWithoutDecimal = "" + (int) weight;
        final boolean weightHasDecimal = weight % 1 != 0;

        viewHolder.workoutName.setText(exerciseName);
        viewHolder.setsRepsWeight.setText(numberOfSets + "x" + repsPerSet + " " + weightWithDecimal + " lbs");
        viewHolder.currentSetWeight.setText("" + weightWithDecimal + " lbs");

        viewHolder.stepperTouch.enableSideTap(true);
        viewHolder.stepperTouch.stepper.setMax(repsPerSet);
        viewHolder.stepperTouch.stepper.setMin(0);
        viewHolder.stepperTouch.stepper.setValue(repsPerSet);

        viewHolder.exerciseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.expandableLayout.toggle();
            }
        });

        for (int i = 0; i < numberOfSets; i++) {
            viewHolder.setsTextViews[i].setVisibility(View.VISIBLE);
            if (!weightHasDecimal)
                viewHolder.setsTextViews[i].setText(weightWithoutDecimal + " x " + repsPerSet);
            else
                viewHolder.setsTextViews[i].setText(weightWithDecimal + " x " + repsPerSet);
        }
        // TODO: Pass on view so snackbar works
        //final Snackbar snackBar = Snackbar.make(view, "Hello world", Snackbar.LENGTH_SHORT);

        viewHolder.addSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setsAddedCount < numberOfSets) {
                    String weightByReps;
                    int stepperValue = viewHolder.stepperTouch.getStepper().getValue();
                    if (!weightHasDecimal) {
                        weightByReps = "" + weightWithoutDecimal + " x " + stepperValue;
                    }
                    else {
                        weightByReps = "" + weightWithDecimal + " x " + stepperValue;
                    }
                    viewHolder.setsTextViews[setsAddedCount].setText(weightByReps);
                    viewHolder.setsTextViews[setsAddedCount].setBackgroundColor(Color.parseColor("#009688"));
                    setItems.get(setsAddedCount).setActualReps(stepperValue);
                    setItems.get(setsAddedCount).setCompleted(true);
                    setsAddedCount++;
                    if (setsAddedCount >= numberOfSets) {
                        viewHolder.addSetButton.setBackgroundColor(Color.parseColor("#bdbdbd"));
                    }
                    //snackBar.show();
                }
            }
        });
    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.workoutName.setText(null);
        holder.setsRepsWeight.setText(null);
    }

    //Init the viewHolder for this Item
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    //The viewHolder used for this item. This viewHolder is always reused by the RecyclerView so scrolling is blazing fast
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView workoutName;
        protected TextView setsRepsWeight;
        protected TextView currentSetWeight;
        protected StepperTouch stepperTouch;
        protected LinearLayout exerciseLayout;
        protected ExpandableLayout expandableLayout;
        protected ExpandableLayout setsExpandableLayout;
        protected Button addSetButton;
        protected TextView[] setsTextViews;

        public ViewHolder(View view) {
            super(view);
            this.workoutName = view.findViewById(R.id.workout_name);
            this.setsRepsWeight = view.findViewById(R.id.sets_reps_weight);
            this.stepperTouch = view.findViewById(R.id.stepper_touch);
            this.currentSetWeight = view.findViewById(R.id.weight_before_stepper);
            this.exerciseLayout = view.findViewById(R.id.exercise_layout);
            this.expandableLayout = view.findViewById(R.id.expandable_layout);
            this.setsExpandableLayout = view.findViewById(R.id.completed_sets);
            this.addSetButton = view.findViewById(R.id.add_set_button);
            setsTextViews = new TextView[] {view.findViewById(R.id.saved_set1), view.findViewById(R.id.saved_set2),
                    view.findViewById(R.id.saved_set3), view.findViewById(R.id.saved_set4),
                    view.findViewById(R.id.saved_set5)};
        }
    }
}
