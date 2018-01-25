package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.graphics.Rect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Andre S Barreiros on 1/25/2018.
 */

public class SaveFile {

    private File f;
    private FileOutputStream output = null;
    private BufferedReader input = null;
    private PrintWriter writer = null;

    public SaveFile(Context context, String filename) {


        try {
            f = new File(context.getFilesDir(), filename);
            f.createNewFile();

            /*output = context.openFileOutput(filename, Context.MODE_PRIVATE); // not needed?
            byte[] data1={1,1,0,0};
            OutputStream fo = new FileOutputStream(f);
            fo.write(data1);
            fo.close();
            System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            writer = new PrintWriter(filename, "UTF-8"); // this doesn't work
            input = new BufferedReader(new FileReader(filename));*/

        } catch (Exception e) {
            System.out.println("Creating a file didnt work");
            e.printStackTrace();
        }
    }

    public void writeToFile(ArrayList<Rect> tmp){
        for (int i = 0; i < tmp.size(); i++){
            System.err.println(tmp.get(i).toString());
            writer.write(tmp.get(i).toString());
        }
    }

    public ArrayList<Rect> readFile(){
        ArrayList<Rect> rects = new ArrayList<Rect>();
        String[] tmp;

        try {
            String line = input.readLine();
            while(line != null){    // or something like that
                line = line.replace("\n", ""); //not sure if i need this
                tmp = line.split(" ");
                rects.add(new Rect(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3])));
                line = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rects;
    }


    public ArrayList<Rect> getTestData(){
        ArrayList<Rect> data = new ArrayList<Rect>();
        data.add(new Rect(0,100,400,800));
        data.add(new Rect(500,400,1080,1000));
        data.add(new Rect(300,1300,600,1500));

        return data;
    }
}
