package com.example.andresbarreiros.fieldmap;

import java.util.ArrayList;

/**
 * Created by Andre S Barreiros on 1/23/2018.
 */

/* This name is a bit wrong, the Leaf class represents a node of a tree (convesional leaves or not) */

public class Leaf extends Node{

    private Leaf parent = null;

    public Leaf(String id, Leaf p){
        super(id);
        parent = p;
    }
    public Leaf(Node n, Leaf p){
        super(n.getId(), n.getConnection());
        parent = p;
    }
    public Leaf(Node n){
        super(n.getId(), n.getConnection());
    }

    public Leaf getParent(){
        return parent;
    }

}
