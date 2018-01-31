package com.example.andresbarreiros.fieldmap;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andre S Barreiros on 1/25/2018.
 */

public class FindUser {

    HashMap<String, String> hm = new HashMap<String, String>(); // <id, location>
    private int[] loc = new int[2];

    public FindUser(){
        loc[0] = 450;
        loc[1] = 1000;

    }

    public int[] getLoc(){
        return loc;
    }

    public int[] setLoc(Floor.Room r, ArrayList<Rect> rooms){ // TODO: make this work as non-hardcoded
        loc[0] = rooms.get(r.getRoomID()-1).centerX();
        loc[1] = rooms.get(r.getRoomID()-1).centerY();
        return loc;
    }

    private void lookUp(){

    }
}
