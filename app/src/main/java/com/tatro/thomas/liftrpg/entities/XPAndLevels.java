package com.tatro.thomas.liftrpg.entities;

import com.orm.SugarRecord;

public class XPAndLevels extends SugarRecord {

    private int xpNeeded;
    private String title;

    public XPAndLevels(int xpNeeded, String title) {
        this.xpNeeded = xpNeeded;
        this.title = title;
    }

    public static void generateNewTable() {
        int xpNeeded = 100;
        String[] titles = {"Tadpole", "Brotége", "Gym Bro", "Gym Rat",  "Monster", "Broseidon", "Freak Beast"};
    }
}
