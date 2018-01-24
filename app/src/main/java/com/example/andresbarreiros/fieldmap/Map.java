package com.example.andresbarreiros.fieldmap;
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
        map = new int[WIDTH][HEIGHT];
    }

    public int getHeight(){
        return this.HEIGHT;
    }
    public int getWidth(){
        return this.WIDTH;
    }

    public Map(List<Rectangle> lst){
        for (int a = 0; a < lst.size(); a++){
            for (int i = lst.get(a).getX(); i < lst.get(a).getWidth(); i++){
                for (int j = lst.get(a).getY(); j < lst.get(a).getHeight(); j++){
                    map[i][j] = 1;
                }
            }
        }
    }

    public int[][] getMap(){
        return this.map;
    }


}
