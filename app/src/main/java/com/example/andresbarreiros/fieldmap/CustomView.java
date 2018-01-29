package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Andre S Barreiros on 1/24/2018.
 */

public class CustomView extends View {

    private Stack<Node> stack;
    private FindUser u;
    private ArrayList<Rect> rs;
    private Graph graph;
    private Canvas c;

    private Paint background;
    private Paint barriers;
    private Paint path;
    private Paint user;
    private ScaleGestureDetector mScaleDetector;
    private boolean drawPath = true;


    public CustomView(Context context, ArrayList<Rect> r, Stack<Node> s, FindUser us, Graph g){
        super(context);

        graph = g;
        this.u = us;
        this.rs = r;
        stack = s;
        background = new Paint();
        barriers = new Paint();
        path = new Paint();
        user = new Paint();

        background.setColor(Color.parseColor("#3394cc")); // 0x3394cc
        barriers.setColor(Color.parseColor("#00007F")); // 0xaaaaaa
        path.setColor(Color.parseColor("#FFA500"));
        user.setColor(Color.parseColor("#670000"));

        path.setStrokeWidth(15);        // making lines THICC again
        path.setStyle(Paint.Style.STROKE);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
            }
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                //Log.d(LOG_KEY, "zoom ongoing, scale: " + detector.getScaleFactor());
                System.err.println(detector.isQuickScaleEnabled());
                return false;
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas){
        //super.onDraw(canvas);
        c = canvas;
        canvas.drawColor(Color.parseColor("#808080"));
        for (int i = 0; i < rs.size(); i++){
            canvas.drawRect(rs.get(i), barriers);
        }
        stack = graph.getPath();
        if (!stack.empty()){
            Node n = stack.pop();
            while ( !stack.empty() ){
                Node m = stack.pop();
                canvas.drawLine(n.getX()*Values.TILESIZE+20, n.getY()*Values.TILESIZE+0, m.getX()*Values.TILESIZE+20, m.getY()*Values.TILESIZE+0, path);
                n = m;
            }
        }else{ /* should notify that that point does not exist */ }
        canvas.drawCircle(u.getLoc()[0]+10, u.getLoc()[1], 15, user);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        mScaleDetector.onTouchEvent(event);
        int X = (int) event.getX();
        int Y = (int) event.getY();
        graph.setEndPoint(X/Values.TILESIZE + " " + Y/Values.TILESIZE);

        this.setScaleX((float) (1*mScaleDetector.getScaleFactor()));
        this.setScaleY((float) (1*mScaleDetector.getScaleFactor()));

        super.draw(new Canvas());
        invalidate();
        return true;
    }

}
