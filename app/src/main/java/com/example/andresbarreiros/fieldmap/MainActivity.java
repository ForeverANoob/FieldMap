package com.example.andresbarreiros.fieldmap;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // 1080 x 1920
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Values.SCREEN_WIDTH = size.x;
        Values.SCREEN_HEIGHT = size.y;

        save = new SaveFile(this,"testFieldMap.txt");
        rect = save.getTestRoom();
        rooms = save.getLookUp();

        /* User info */
        Floor floor = new Floor(); // TODO: do this properly (gettting from the database)
        Classifier cls = new Classifier(floor, this);
        user = new FindUser();  // TODO: delete this line (when it doesn't break stuff)
        Floor.Room room = cls.getRoomID(); // user is in here
        user.setLoc(room, rooms);

        /* Floor info */
        //System.err.println("In it, this is written "+save.readFromFile(this));
        Map map = new Map(51, 96);
        map.makeMap(rect);

        graph = new Graph(map);

        graph.setLocation(Integer.toString(user.getLoc()[0]/Values.TILESIZE)+" "+Integer.toString(user.getLoc()[1]/Values.TILESIZE)); // set user location
        //graph.setEndPoint("1 2");

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

}
