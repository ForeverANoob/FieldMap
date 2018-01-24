package com.example.andresbarreiros.fieldmap;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Andre S Barreiros on 1/23/2018.
 */

/* basic rectangle used by Map.java, may not be needed */

public class Rectangle {

    private int x;
    private int y;
    private int height;
    private int width;
    private float rotation; // should not be used

    public Rectangle(int x, int y, int h, int w){
        this.x = x;
        this.y = y;
        height = h;
        width = w;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

}
