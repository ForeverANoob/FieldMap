package com.example.andresbarreiros.fieldmap;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends Activity implements View.OnTouchListener {

    private static SeekBar seekbar;
    AnimationDrawable anime;
    private  SaveFile save;;
    private boolean isTouch = false;
    private Graph graph;
    ArrayList<Rect> rect;
    ArrayList<Rect> rooms;
    FindUser user;
    Floor floor;
    Classifier cls;
    Stack<Node> path;
    private Handler handler;
    private int interval = 2000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // 1080 x 1920
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay(); // these few lines of code is to hoping for proper screen resolution
        Point size = new Point();
        display.getSize(size);
        Values.SCREEN_WIDTH = size.x;
        Values.SCREEN_HEIGHT = size.y;
        Values.multi = (float) (Values.SCREEN_WIDTH/720.0);
        System.out.println("====================== " + size.x + " "+ size.y + " " + Values.multi);

        save = new SaveFile(this,"testFieldMap.txt");
        rect = save.getTestRoom();
        rooms = save.getLookUp();

        /* User info */
        floor = LoadUpActivity.newFloor;// TODO: do this properly (gettting from the database)
        cls = new Classifier(floor, getApplicationContext());
        user = new FindUser();  // TODO: delete this line (when it doesn't break stuff)
        this.setUserLoc();

        /* Floor info */
        Map map = new Map(51, 96);
        map.makeMap(rect);
        graph = new Graph(map);
        graph.setLocation(Integer.toString(user.getLoc()[0]/Values.TILESIZE)+" "+Integer.toString(user.getLoc()[1]/Values.TILESIZE)); // set user location

        setContentView(new CustomView(this, rect, rooms, path, user, graph, cls));
        //handler = new Handler();
        //startRepeatingTask();

    }

    protected void setUserLoc(){
        Floor.Room room = cls.getRoomID(); // user is in here
        user.setLoc(room, rooms);
    }

    // does nothing
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }



/*
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus(); //this function can change value of mInterval.
                setUserLoc();
                graph.setLocation(Integer.toString(user.getLoc()[0]/Values.TILESIZE)+" "+Integer.toString(user.getLoc()[1]/Values.TILESIZE));
                System.out.println("+++++++++++++++++++++++++++++++");


                //recreate();

            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                handler.postDelayed(mStatusChecker, interval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        handler.removeCallbacks(mStatusChecker);
    }
*/
}
