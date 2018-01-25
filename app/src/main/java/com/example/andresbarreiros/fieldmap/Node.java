package com.example.andresbarreiros.fieldmap;

import java.util.ArrayList;

/**
 * Created by Andre S Barreiros on 1/23/2018.
 */

/* Standard node of the graph (from Graph.java) */

public class Node {

    private ArrayList<Node> connected = new ArrayList<Node>();
    private String id;

    public Node(String i, ArrayList conn){
        connected = conn;
        this.id = i;
    }
    public Node(String i){
        this.id = i;
    }
    public int getX(){
        String[] tmp = id.split(" ");
        return Integer.parseInt(tmp[0]);
    }
    public int getY(){
        String[] tmp = id.split(" ");
        return Integer.parseInt(tmp[1]);
    }
    public void setConnected(ArrayList conn){
        connected = conn;
    }
    public void  addNode(Node n){
        connected.add(n);
    }

    public String getId(){
        return id;
    }
    public ArrayList<Node> getConnection(){
        return connected;
    }

    public String toString(){
        String string = "["+this.id+"]: ";
        for (int i = 0; i < connected.size(); i++){
            string += "["+connected.get(i).getId()+"] ";
        }
        return string;
    }
}
