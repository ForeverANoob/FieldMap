package com.example.andresbarreiros.fieldmap;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends Activity {

    AnimationDrawable anime;
    private  SaveFile save;;
    private boolean isTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // 1080 x 1920
        super.onCreate(savedInstanceState);

        ArrayList<Rect> rect;

        save = new SaveFile(this,"testFieldMap.txt");
        rect = save.getTestData();

        FindUser user = new FindUser();
        Map map = new Map(51, 96);
        map.makeMap(rect);

        Graph graph = new Graph(map);
        graph.setLocation(Integer.toString(user.getLoc()[0]/Values.TILESIZE)+" "+Integer.toString(user.getLoc()[1]/Values.TILESIZE));
        Stack<Node> path = graph.setEndPoint("1 2");
        //save.writeToFile(rect);

        setContentView(new CustomView(this, rect, path, user));

    }
    

}
