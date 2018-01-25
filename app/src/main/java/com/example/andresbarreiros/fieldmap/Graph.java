package com.example.andresbarreiros.fieldmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Andre S Barreiros on 1/23/2018.
 */

/* A graph representation of the room, may need Map.java */

public class Graph {

    ArrayList<Node> graph = new ArrayList<Node>();  // store graph
    Node root = null;                               // store current point
    Node end = null;                                // store end-point - may be null
    int walls = 0;


    public Graph(Map map){   // this one
        // generate graph

        int[][] tmp = map.getMap();
        for (int i = 0; i < map.getWidth(); i++){ // x
            for (int j = 0; j < map.getHeight(); j++){  // y

                    String id = Integer.toString(i)+" "+Integer.toString(j);
                    Node n = new Node(id);
                    //System.err.println(i+" " + j);
                    if (i != 0 && tmp[i-1][j] != 1){ // left
                        Node left = graph.get(graph.size() - map.getHeight()); //
                        left.addNode(n);
                        n.addNode(left);
                    }
                    if (j != 0 && tmp[i][j-1] != 1){ // above
                        Node top = graph.get(graph.size() - 1); // the last element currently in the arraylist
                        top.addNode(n);
                        n.addNode(top);
                    }
                    graph.add(n); // should it matter where this is added?
                    if(tmp[i][j] == 1){
                        walls++;
                    }
            }
        }

        //for (int i = 0; i < graph.size(); i++){
          //  System.err.println(graph.get(i).toString());
        //}
    }

    private Node searchNode(String id){
        for (int i = 0; i < graph.size(); i++){
            if (graph.get(i).getId().equals(id)){
                return graph.get(i);
            }
        }
        return null;    // node does not exist
    }

    public void setLocation(String id){
        // set user location on the map
        root = searchNode(id);
        assert(root!=null); // shouldn't trigger
    }


    public Stack<Node> setEndPoint(String id){
        // set end point
        end = searchNode(id);
        assert(end!=null);  // shouldn't trigger
        return getPath();
    }

    public Stack<Node> getPath(){       // should work
        // apply djkistra's algorithm (or something like that sort of) to find path
        Stack<Node> path = new Stack<Node>();
        ArrayList<Leaf> level = new ArrayList<Leaf>();
        ArrayList<Leaf> nextlevel = new ArrayList<Leaf>();
        HashSet<String> doneNode = new HashSet<String>();

        level.add(new Leaf(root, null));
        doneNode.add(root.getId());
        //path.add(root);
        //System.out.println(root.toString() + " " + end.toString());

        while (doneNode.size() < (graph.size()-walls-10) ) { // also exits when nextlevel.size() == 0

            for (int i = 0; i < level.size(); i++) { // iterate throught the level

                ArrayList<Node> tmp = level.get(i).getConnection();
                for (int j = 0; j < tmp.size(); j++) { // check children

                    if (tmp.get(j).getId().equals(end.getId())){ // check if child is end node
                        // end point found
                        // now find the path
                        System.err.println("Found it!!!!!!!!!!!!!!!");
                        return this.getParents(new Leaf(tmp.get(j), level.get(i)), path);
                    }

                    if (!doneNode.contains(tmp.get(j).getId())) {   // prepare the next level
                        nextlevel.add(new Leaf(tmp.get(j), level.get(i)));
                        doneNode.add(tmp.get(j).getId());
                    }
                }
            }
            // nextlevel becomes current level
            level = nextlevel;
            nextlevel = new ArrayList<Leaf>();
        }
        return path;
    }

    private Stack<Node> getParents(Leaf l, Stack<Node> path){ // not that path is intially empty

        path.push(l);
        while (l.getParent() != null){
            l = l.getParent();
            path.push(l);
        }
        return path;
    }


}
