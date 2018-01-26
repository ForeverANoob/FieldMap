package com.example.andresbarreiros.fieldmap;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andre S Barreiros on 1/23/2018.
 */

/* Grid represnetation of a room */

public class Map {

    private int HEIGHT;
    private int WIDTH;
    private int[][] map;    // TODO: change to a bitmap maybe?

    public Map(int w, int h){
        HEIGHT = h;
        WIDTH = w;
        map = new int[WIDTH][HEIGHT]; // hard coded to [52][96]

    }

    public int getHeight(){
        return this.HEIGHT;
    }
    public int getWidth(){
        return this.WIDTH;
    }

    public void makeMap(ArrayList<Rect> lst){
        //System.err.println(lst.size() + " 22222222222222222222222222222222222222222222");
        for (int a = 0; a < lst.size(); a++){
            for (int i = lst.get(a).left; i < lst.get(a).right; i+=Values.TILESIZE){
                for (int j = lst.get(a).top; j < lst.get(a).bottom; j+=Values.TILESIZE){
                    if (i/Values.TILESIZE >= 51 || i/Values.TILESIZE >= 95){
                        continue;
                    }
                    //if(a > 13) { System.err.println("stuff now"); }
                    map[(i/Values.TILESIZE)][(j/Values.TILESIZE)] = 1;
                }
                //System.err.println();
            }
        }
        //toString();
    }

    public int[][] getMap(){
        return this.map;
    }

    public String toString(){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.err.println();
        return "";
    }
}
