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
    /*
        private final String vertexShaderCode =
                "attribute vec4 vPosition;" +
                "void main(){ gl_position = vPosition; }";

        private final String fragmentShaderCode =
                "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main(){ gl_FragColor = vColor; }";

        private final int mProgram;

        private int mPositionHandle;
        private int mColorHandle;

        private FloatBuffer vertexBuffer;
        private ShortBuffer drawListBuffer;

        private short drawOrder[] = {0, 1, 2, 0, 2, 3};
        static float rectCoords[] = {
                -0.5f,  0.5f, 0.0f,   // top left
                -0.5f, -0.5f, 0.0f,   // bottom left
                0.5f, -0.5f, 0.0f,   // bottom right
                0.5f,  0.5f, 0.0f }; // top right

        private int COORDS_PER_VERTEX = 3;
        private final int vertexCount = rectCoords.length;
        private final int vertexStride = COORDS_PER_VERTEX*4;
        float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };



        public Rectangle(){

            ByteBuffer bb = ByteBuffer.allocateDirect(rectCoords.length * 4);
            bb.order(ByteOrder.nativeOrder());
            vertexBuffer = bb.asFloatBuffer();
            vertexBuffer.put(rectCoords);
            vertexBuffer.position(0);

            // initialize byte buffer for the draw list
            ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
            dlb.order(ByteOrder.nativeOrder());
            drawListBuffer = dlb.asShortBuffer();
            drawListBuffer.put(drawOrder);
            drawListBuffer.position(0);


            int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
            int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);
            mProgram = GLES20.glCreateProgram();
            GLES20.glAttachShader(mProgram, vertexShader);
            GLES20.glAttachShader(mProgram, fragmentShader);
            GLES20.glLinkProgram(mProgram);
        }
    */
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
        //mProgram = 0;
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
/*
    public void draw(){

        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0); // set colour
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }*/
}
