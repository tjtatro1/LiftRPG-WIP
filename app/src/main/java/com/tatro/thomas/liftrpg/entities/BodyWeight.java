package com.tatro.thomas.liftrpg.entities;

import com.orm.SugarRecord;

public class BodyWeight extends SugarRecord {

    private String date;
    private int bodyWeight;

    public BodyWeight(int bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

}
