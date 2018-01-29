package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Classifier {

    List<ScanResult> fingerprint = new ArrayList<>();
    Floor workingFloor;
    Context appContext;

    public Classifier(Floor source, Context context){

        workingFloor = source;
        appContext = context;

    }

    public List<ScanResult> getScan (){

        WifiManager wifi = (WifiManager) appContext.getSystemService(appContext.WIFI_SERVICE);
        List<ScanResult> results;
        wifi.startScan();

        results = wifi.getScanResults();

        return results;

    }

    public Floor.Room getRoomID(){

        fingerprint = getScan();
        List<Floor.Room> allRooms = workingFloor.getRooms();

        for (Floor.Room room: allRooms){

            List<Floor.Area> roomAreas = room.getAreas();
            for (Floor.Area area: roomAreas){

                List<Floor.Mac> areaMacs = area.getMacs();
                for (Floor.Mac mac: areaMacs){



                }

            }


        }

    }

}
