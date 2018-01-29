package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FingerprintTester {

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile (String filePath){
        File sdcard = new Environment().getExternalStorageDirectory();
        File fl = new File(sdcard,filePath);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fl);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String ret = null;
        try {
            ret = convertStreamToString(fin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Make sure you close all streams.
        try {
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void getComparison(View v, Context context){

        Classifier classify = new Classifier();

        //String sourceString = getStringFromFile("print.txt");

        //System.err.println(sourceString);

        List<ScanResult> results = classify.getScan(context.getApplicationContext());

        String line = "";
        for (ScanResult result : results)
        {
            line = line + "\n"+(result.SSID + "|" + result.BSSID + "|" + result.level);
        }

    }

}
