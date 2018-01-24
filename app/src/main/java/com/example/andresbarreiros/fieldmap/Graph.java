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


    public Graph(Map map){   //
        // generate graph
        int[][] tmp = map.getMap();
        for (int i = 0; i < map.getWidth(); i++){
            for (int j = 0; j < map.getHeight(); j++){
                if (tmp[i][j] != 1){
                    String id = Integer.toString(i)+Integer.toString(j);
                    Node n = new Node(id);
                    if (i != 0 || tmp[i-1][j] != 1){
                        Node left = graph.get((j*map.getWidth())+i-1); // the last element currently in the arraylist
                        left.addNode(n);
                        n.addNode(left);
                    }
                    if (j != 0 || tmp[i][j-1] != 1){
                        Node top = graph.get((j-1)*map.getWidth()+i); //
                        top.addNode(n);
                        n.addNode(top);
                    }
                    graph.add(n); // should it matter where this is added?
                }
            }
        }

    }

    public void Graph(Node n, int h, int w){
        if (h >= 25 || w >= 25){    // don't hard code it
            return;
        }
        Node right = new Node(Integer.toString(w+1)+Integer.toString(h));
        Node bottom = new Node(Integer.toString(w)+Integer.toString(h+1));

        n.addNode(right);
        n.addNode(bottom);
        bottom.addNode(n);
        right.addNode(n);

        Graph(right, h, w+1);
        Graph(bottom, h+1, w);
    }

    private Node searchNode(String id){
        for (int i = 0; i < graph.size(); i++){
            if (graph.get(i).equals(id)){
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

    public void setEndPoint(String id){
        // set end point
        end = searchNode(id);
        assert(end!=null);  // shouldn't trigger
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

        while (doneNode.size() < graph.size()) { // also exits when nextlevel.size() == 0

            for (int i = 0; i < level.size(); i++) { // iterate throught the level

                ArrayList<Node> tmp = level.get(i).getConnection();
                for (int j = 0; j < tmp.size(); j++) { // check children

                    if (tmp.get(j).getId().equals(end.getId())){ // check if child is end node
                        // end point found
                        // now find the path
                        this.getParents(new Leaf(tmp.get(j), level.get(i)), path);
                    }

                    if (!doneNode.contains(tmp.get(j).getId())) {   // prepare the next level
                        nextlevel.add(new Leaf(tmp.get(j), level.get(i)));
                        doneNode.add(tmp.get(j).getId());
                    }
                }
            }
            // nextlevel becomes current level
            level = nextlevel;
            nextlevel.clear();
        }
        return path;
    }

    private Stack<Node> getParents(Leaf l, Stack<Node> path){

        path.push(l);
        while (l.getParent() != null){
            l = l.getParent();
            path.push(l);
        }
        return path;
    }


}
