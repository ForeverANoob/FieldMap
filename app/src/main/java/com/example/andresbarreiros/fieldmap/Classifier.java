package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.net.wifi.ScanResult; //This stores scan results
import android.net.wifi.WifiManager; //This allows you to scan

// You need the WiFi permissions in your app permissions to use this right

import java.util.*;

public class Classifier {

    /*
     *
     * A classifier operates on a single floor of a building, which is passed
     * in during creation. On request, it will scan for wifi signals and do the
     * fingerprinting operation on it, returning the Room which it thinks the
     * wifi scan was taken in. It uses the scoring function of FingerprintEntry
     * for all mac addresses in each area of a room.
     *
     */

    java.util.Map<String, Integer> finalprint =  new HashMap();
    Floor workingFloor;
    Context appContext;
    double punish = 0.08; //This value is subtracted from a room total for every
                          //Mac Address expected but not found in the areas.
                          //See the Floor object for how things are divided up

    public void setPunish(double value){

        this.punish = value;

    }

    public Classifier(Floor source, Context context){ //Constructor
        workingFloor = source;
        appContext = context;

    }

    public void getScan (){ //Uses the Wifi Android API to get a list of wifi networks

        WifiManager wifi = (WifiManager) appContext.getSystemService(appContext.WIFI_SERVICE);
        List<ScanResult> results;
        wifi.startScan();

        finalprint.clear(); //I don't know who made this global and I don't know how git blame works

        results = wifi.getScanResults();

        for (ScanResult iter: results) {
            finalprint.put(iter.BSSID, iter.level); //Simplified to only what we care about
        }

    }

    public Floor.Room getRoomID(){ //Returns a Room that it thinks the signal was taken in

        this.getScan();
        List<Floor.Room> allRooms = workingFloor.getRooms();

        Floor.Room likelyRoom = null; // No room expected

        float roomMax = -300; //Massively negative base score, might want a boolean in future
        for (Floor.Room room: allRooms){

            float roomscore = 0;
            int divcount = 0;

            List<Floor.Area> roomAreas = room.getAreas(); //multi-loop drills down to area specifics
            for (Floor.Area area: roomAreas){

                List<Floor.Mac> areaMacs = area.getMacs();
                for (Floor.Mac mac: areaMacs){

                    String address = mac.getAddress(); //Identify each mac address

                    if (finalprint.containsKey(address)) {
                        FingerprintEntry checkAgainst = new FingerprintEntry(address,mac.getHighRead(),mac.getLowRead()); //Generate a FingerPrintEntry for ever mac address in an area
                        roomscore += checkAgainst.scoreMac(finalprint.get(address)); //compare to the relevant mac address, if it exists
                        divcount += 1; //add to average counter

                    }
                    else {
                        roomscore -= punish; //subtract penal value if no mac found
                        //divcount-=1;

                    }


                }

                roomscore = roomscore/divcount; //average

            }

            if (roomscore > roomMax) { //Finding max score room
                roomMax = roomscore;
                likelyRoom = room;

            }


        }
        return likelyRoom;

    }

}
