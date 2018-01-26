package com.example.andresbarreiros.fieldmap;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends Activity implements View.OnTouchListener {

    AnimationDrawable anime;
    private  SaveFile save;;
    private boolean isTouch = false;
    private Graph graph;
    ArrayList<Rect> rect;
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
        rect = save.getTestData();

        user = new FindUser();
        Map map = new Map(51, 96);
        map.makeMap(rect);

        graph = new Graph(map);
        graph.setLocation(Integer.toString(user.getLoc()[0]/Values.TILESIZE)+" "+Integer.toString(user.getLoc()[1]/Values.TILESIZE));
        graph.setEndPoint("1 2");
        //path = graph.getPath();
        //save.writeToFile(rect);

        setContentView(new CustomView(this, rect, path, user, graph));

    }

    // these two don't do anything
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getX();
        int Y = (int) event.getY();
        //graph.setEndPoint(X/Values.TILESIZE + " " + Y/Values.TILESIZE);

        //new CustomView(this, rect, path, user).onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int X = (int) event.getX();
        int Y = (int) event.getY();
        System.err.println("another stuff");
        return true;
    }
}
