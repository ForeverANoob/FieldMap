package com.example.andresbarreiros.fieldmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.View;

/**
 * Created by Andre S Barreiros on 1/24/2018.
 */

public class CustomView extends View {

    private Rect rectangle;
    private Rect rect2;
    private Paint paint;

    private Paint background;
    private Paint barriers;
    private Paint path;
    private Paint user;

    public CustomView(Context context){
        super(context);
        int x = 100;
        int y = 100;
        int sideLength = 200;
        int xp = 400;
        int yp = 600;

        rectangle = new Rect(x, y, xp, yp);
        rect2 = new Rect(700,700,800,800);

        paint = new Paint();
        paint.setColor(Color.GRAY);

        background = new Paint();
        barriers = new Paint();
        path = new Paint();
        user = new Paint();

        background.setColor(Color.parseColor("#3394cc")); // 0x3394cc
        barriers.setColor(Color.parseColor("#333333")); // 0xaaaaaa
        path.setColor(Color.parseColor("#990099"));
        user.setColor(Color.parseColor("#990011"));

        path.setStrokeWidth(10);        // making lines THICC agsin
        path.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.parseColor("#3394cc"));
        canvas.drawRect(rectangle, barriers);
        canvas.drawRect(rect2, barriers);
        canvas.drawLine(350,350,350,800, path);
        canvas.drawCircle(800,800,20,user);
    }

}
