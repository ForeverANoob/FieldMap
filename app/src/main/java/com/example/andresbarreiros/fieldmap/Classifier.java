package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.*;

public class Classifier {

    java.util.Map<String, Integer> finalprint =  new HashMap();
    Floor workingFloor;
    Context appContext;

    public Classifier(Floor source, Context context){
        workingFloor = source;
        appContext = context;

    }

    public void getScan (){

        WifiManager wifi = (WifiManager) appContext.getSystemService(appContext.WIFI_SERVICE);
        List<ScanResult> results;
        wifi.startScan();

        finalprint.clear();

        results = wifi.getScanResults();

        for (ScanResult iter: results) {
            finalprint.put(iter.BSSID, iter.level);
        }

    }

    public Floor.Room getRoomID(){

        this.getScan();
        List<Floor.Room> allRooms = workingFloor.getRooms();

        Floor.Room likelyRoom = null;

        float roomMax = -40;
        System.out.println("shit "+workingFloor+"         "+workingFloor.getRooms());
        for (Floor.Room room: allRooms){

            float roomscore = 0;
            int divcount = 0;

            List<Floor.Area> roomAreas = room.getAreas();
            for (Floor.Area area: roomAreas){

                List<Floor.Mac> areaMacs = area.getMacs();
                for (Floor.Mac mac: areaMacs){

                    String address = mac.getAddress();

                    if (finalprint.containsKey(address)) {
                        FingerprintEntry checkAgainst = new FingerprintEntry(address,mac.getHighRead(),mac.getLowRead());
                        roomscore += checkAgainst.scoreMac(finalprint.get(address));
                        divcount += 1;

                    }
                    else {
                        roomscore -= 0.1;

                    }


                }

                roomscore = roomscore/divcount;

            }

            if (roomscore > roomMax) {
                roomMax = roomscore;
                likelyRoom = room;
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::: "+likelyRoom);

            }


        }
        System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}} "+likelyRoom);
        return likelyRoom;

    }

}
