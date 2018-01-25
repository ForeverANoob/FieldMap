package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
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

    private Paint background;
    private Paint barriers;
    private Paint path;
    private Paint user;

    public CustomView(Context context, ArrayList<Rect> r, Stack<Node> s, FindUser us){
        super(context);

        this.u = us;
        this.rs = r;
        stack = s;
        background = new Paint();
        barriers = new Paint();
        path = new Paint();
        user = new Paint();

        background.setColor(Color.parseColor("#3394cc")); // 0x3394cc
        barriers.setColor(Color.parseColor("#333333")); // 0xaaaaaa
        path.setColor(Color.parseColor("#990099"));
        user.setColor(Color.parseColor("#990011"));

        path.setStrokeWidth(15);        // making lines THICC again
        path.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.parseColor("#3394cc"));
        for (int i = 0; i < rs.size(); i++){
            canvas.drawRect(rs.get(i), barriers);
        }
        if (!stack.empty()){
            Node n = stack.pop();
            while ( !stack.empty() ){
                Node m = stack.pop();
                canvas.drawLine(n.getX()*Values.TILESIZE+20, n.getY()*Values.TILESIZE+20, m.getX()*Values.TILESIZE+20, m.getY()*Values.TILESIZE+20, path);
                n = m;
            }
        }
        canvas.drawCircle(u.getLoc()[0]+10, u.getLoc()[1], 15, user);
    }

}
