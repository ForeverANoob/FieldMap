package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Classifier {

    List<FingerprintEntry> fingerprint = new ArrayList<>();

    public Classifier(){

        fingerprint = new ArrayList<>();

    }

    public Classifier(Floor source){



    }

    public List<ScanResult> getScan (Context appContext){

        WifiManager wifi = (WifiManager) appContext.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> results;
        wifi.startScan();

        results = wifi.getScanResults();

        return results;

    }

    public void setFingerprint(ArrayList<String> list){

        for (int k = 0; k<=list.size();k++) {

            ArrayList<String> set = new ArrayList<String>();
            set = (ArrayList<String>) Arrays.asList(list.get(k).split(","));

        }

    }

    public float getWholeScore(ArrayList<ScanResult> scan){

        for (int k=0;k<scan.size();k++) {



        }

        return(30);

    }

}
