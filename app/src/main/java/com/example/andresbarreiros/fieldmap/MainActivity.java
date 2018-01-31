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
    Stack<Node> path;
    private Handler handler;
    private int interval = 2000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // 1080 x 1920
        super.onCreate(savedInstanceState);

        handler = new Handler();
        Display display = getWindowManager().getDefaultDisplay(); // these few lines of code is to hoping for proper screen resolution
        Point size = new Point();
        display.getSize(size);
        Values.SCREEN_WIDTH = size.x;
        Values.SCREEN_HEIGHT = size.y;

        save = new SaveFile(this,"testFieldMap.txt");
        rect = save.getTestRoom();
        rooms = save.getLookUp();

        /* User info */
        Floor floor = LoadUpActivity.newFloor;// TODO: do this properly (gettting from the database)
        System.out.println(floor.getRooms());
        Classifier cls = new Classifier(floor, getApplicationContext());
        user = new FindUser();  // TODO: delete this line (when it doesn't break stuff)
        Floor.Room room = cls.getRoomID(); // user is in here
        System.err.println("++++++++++++++++++++++++++++++++++ "+room);
        user.setLoc(room, rooms);

        System.err.println(" --------------- " + user.getLoc());
        /* Floor info */
        Map map = new Map(51, 96);
        map.makeMap(rect);
        graph = new Graph(map);
        graph.setLocation(Integer.toString(user.getLoc()[0]/Values.TILESIZE)+" "+Integer.toString(user.getLoc()[1]/Values.TILESIZE)); // set user location

        setContentView(new CustomView(this, rect, rooms, path, user, graph));

    }

    public void seekBar(){
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        progress_value = i;

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
        // System.out.println(seekbar.get);
    }

    // does nothing
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    private Runnable runnableCode = new Runnable() { // try this
        @Override
        public void run() {
            // Do something here on the main thread
            System.err.println("this alarm should run maybe");
            handler.postDelayed(this, interval);
        }
    };

}
