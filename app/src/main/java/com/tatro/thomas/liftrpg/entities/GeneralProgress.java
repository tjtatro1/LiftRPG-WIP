package com.tatro.thomas.liftrpg.entities;

import com.orm.SugarRecord;

public class GeneralProgress extends SugarRecord {

    private int totalXP;
    private int level;
    private String title;

    public GeneralProgress(int totalXP, int level, String title) {
        this.totalXP = totalXP;
        this.level = level;
        this.title = title;
    }

}
