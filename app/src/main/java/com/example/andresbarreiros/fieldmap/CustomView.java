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
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rect2, paint);
    }

}
