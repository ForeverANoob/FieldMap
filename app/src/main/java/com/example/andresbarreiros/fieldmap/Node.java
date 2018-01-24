package com.example.andresbarreiros.fieldmap;

import java.util.ArrayList;

/**
 * Created by Andre S Barreiros on 1/23/2018.
 */

/* Standard node of the graph (from Graph.java) */

public class Node {

    private ArrayList<Node> connected = new ArrayList<Node>();
    private String id;

    public Node(String id, ArrayList conn){
        connected = conn;
        id = id;
    }
    public Node(String id){
        id = id;
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
}
