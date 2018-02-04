package com.example.andresbarreiros.fieldmap;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Andre S Barreiros on 1/25/2018.
 *
 * ALSO I THINK THIS IS NO-LONGER NECESSARY, DELETE IF CLEAR @ForeverANoob
 *
 * -@KaliumPuceon
 */

public class SaveFile extends Activity{

    private String filename = "";
    private File file;
    private FileOutputStream output = null;
    private BufferedReader input = null;
    private PrintWriter writer = null;

    public SaveFile(Context context, String name) {
        filename = name;

    }

    public void writeToFile(String localops){
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
        file.getParentFile().mkdirs();
        try {
            if(!file.exists()) // Create the file if it does not exist.
                file.createNewFile();
            file.setReadable(true, false);
            System.err.println(file.exists());
            output = new FileOutputStream(file);
            output.write(localops.getBytes());
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(Context context){

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

            // do reading, usually loop until end of file reading
            StringBuilder sb = new StringBuilder();
            String mLine = reader.readLine();
            while (mLine != null) {
                sb.append(mLine); // process line
                mLine = reader.readLine();
            }
            reader.close();
            return sb.toString();
        }catch(IOException e){ e.printStackTrace(); return "Error"; }
    }









    public ArrayList<Rect> readFile(){
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);

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
        data.add(new Rect(500,400,1100,1000));
        data.add(new Rect(300,1300,600,1500));

        return data;
    }

    public ArrayList<Rect> getTestRoom(){
        ArrayList<Rect> data = new ArrayList<Rect>();
        data.add(new Rect(0,0,160,120));
        data.add(new Rect(240,0,600,80));
        data.add(new Rect(0,240,160,320));
        data.add(new Rect(240,200,600,280));
        data.add(new Rect(640,80,720,560));
        data.add(new Rect(0,440,160,520));
        data.add(new Rect(240,400,600,480));
        data.add(new Rect(0,600,160,680));
        data.add(new Rect(240,600,600,960));
        data.add(new Rect(640,600,680,920));
        data.add(new Rect(0,760,160,840));
        data.add(new Rect(0,920,160,1080));
        data.add(new Rect(640,960,720,1000));
        data.add(new Rect(600,1000,720,1080));

        data.add(new Rect(0,1040,720,1080));
        data.add(new Rect(720,0,760,1080));

        return data;
    }

    public ArrayList<Rect> getLookUp(){ // location of the room
        ArrayList<Rect> rooms = new ArrayList<Rect>();
        rooms.add(new Rect(0,0,240,240));         // vr
        rooms.add(new Rect(240,0,600,240));       // designers
        rooms.add(new Rect(600, 0, 720, 240));  // escape
        rooms.add(new Rect(0,240,240,480));       // Window 1
        rooms.add(new Rect(240,240, 600,480));    // Desks
        rooms.add(new Rect(600, 240, 720, 480));  // wall
        rooms.add(new Rect(0,480,240,720));       // window 2
        rooms.add(new Rect(240,480,600,600));     // benches
        rooms.add(new Rect(600,480,720,720));     //kitchen
        rooms.add(new Rect(0,720,240,960));       // window 3
        rooms.add(new Rect(600, 720,720,960));    // lounge
        rooms.add(new Rect(0, 960, 240, 1080));   // reception
        rooms.add(new Rect(240, 960, 480, 1080)); // entrance
        rooms.add(new Rect(480, 960, 720, 1080)); // Brainroom

        return rooms;
    }

    public String toString(ArrayList<Rect> rects){
        String data = "";
        for (int i = 0; i < rects.size(); i++){
            data += rects.get(i).toString() + '\n';
        }
        return data;
    }
}
