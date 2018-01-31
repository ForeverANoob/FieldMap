package com.example.andresbarreiros.fieldmap;
/**
 * Created by Nyashadzaishe Bryan on 26/1/2018.
 */

import java.util.List;
import java.util.ArrayList;

public class Floor{

    private List<Room> listOfRooms;
    private int floorID;
    private String floorName;

    public Floor(){
        this.listOfRooms = new ArrayList<Room>();
    }

    /* Setter methods */

    public void addRoom(Room newRoom){ this.listOfRooms.add(newRoom);  }

    public void setFloorID(int ID){
        this.floorID = ID;
    }

    public void setFloorName(String name){
        this.floorName = name;
    }

    /*Getter Methods */

    public List<Room> getRooms(){
        return this.listOfRooms;
    }

    public int getFloorID(){
        return this.floorID;
    }

    public String getFloorName(){
        return this.floorName;
    }

    /* Other Methods */

    public boolean contains(Room room){
        return this.listOfRooms.contains(room);
    }

    public String toString(){
        return this.getFloorName();
    }
    /* Room Class */

    class Room{

        private List<Area> listOfAreas = new ArrayList<Area>();
        private int roomID;
        private String roomName;

        Room(){
        }

        Room(int ID){
            this.roomID = ID;
        }

        Room(int ID, String name){
            this.roomID = ID;
            this.roomName = name;
        }

        /* Setter methods */

        public void addArea(Area newArea){
            this.listOfAreas.add(newArea);
        }

        public void setRoomID(int ID){
            this.roomID = ID;
        }

        public void setRoomName(String name){
            this.roomName = name;
        }

        /* Getter Methods */

        public List<Area> getAreas(){
            return this.listOfAreas;
        }

        public int getRoomID(){
            return this.roomID;
        }

        public String getRoomName(){
            return this.roomName;
        }

        /* Other methods */

        public boolean equals(Object other){
            if (this.getClass() == other.getClass()){

                if(this.roomID == ((Room)other).roomID) return true;
                else return false;
            }
            else return false;
        }

    }


    class Area{

        private List<Mac> listOfMacs = new ArrayList<Mac>();
        private int areaID;

        Area(){
        }

        Area(int ID){
            this.areaID = ID;
        }

        /* Setter Methods */

        public void addMac(Mac newMac){
            this.listOfMacs.add(newMac);
        }

        public void setAreaID(int ID){
            this.areaID = ID;
        }

        /* Getter Methods */

        public List<Mac> getMacs(){
            return this.listOfMacs;
        }

        public int getAreaID(){
            return this.areaID;
        }

        /* Other Methods */

        public boolean equals (Object other){
            if (this.getClass() == other.getClass()){
                if (this.areaID == ((Area)other).areaID) return true;
                else return false;
            }
            else return false;
        }
    }

    class Mac{

        private String address;
        private int highRead;
        private int lowRead;

        Mac(String address,int highRead,int lowRead){
            this.address = address;
            this.highRead = highRead;
            this.lowRead = lowRead;
        }

        Mac(){

        }

        /*Getter Methods */

        public String getAddress(){
            return this.address;
        }

        public int getHighRead(){
            return this.highRead;
        }

        public int getLowRead(){
            return this.lowRead;
        }

        /* Setter Methods */

        public void setAddress(String address) {
            this.address = address;
        }

        public void setHighRead(int highRead) {
            this.highRead = highRead;
        }

        public void setLowRead(int lowRead) {
            this.lowRead = lowRead;
        }
    }
}