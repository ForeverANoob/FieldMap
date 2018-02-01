package com.example.andresbarreiros.fieldmap;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoadUpActivity extends AppCompatActivity {

    ProgressBar progressBar;
    public interface Callback{

        void afterGettingData();
    }

    protected static Floor newFloor = new Floor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadupactivity);

        readData(new Callback() {
            @Override
            public void afterGettingData() {

                //Switch to new Intent after CallBack

                Log.w("Database", LoadUpActivity.newFloor.toString());

                startActivity(new Intent(LoadUpActivity.this, MainActivity.class));
            }
        });

    }


    public void readData(final Callback callback){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                LoadUpActivity.newFloor = makeFloorFromDatabase(dataSnapshot);
                callback.afterGettingData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public Floor makeFloorFromDatabase(DataSnapshot dataSnapshot){

        Floor aFloor = new Floor();

        String floorName;
        int floorID;
        DataSnapshot roomSnapshot = dataSnapshot.child("Room");

        String roomName;
        int roomID;
        Floor.Area anArea;
        Floor.Room aRoom;
        DataSnapshot areaSnapshot;
        Iterable<DataSnapshot> areasSnapshots;
        int areaID;

        DataSnapshot macSnapshot;
        Iterable<DataSnapshot> macsSnapshots;

        String macID;
        int highRead;
        int lowRead;

        floorName = dataSnapshot.child("floorName").getValue(String.class);
        floorID = dataSnapshot.child("floorID").getValue(Integer.class);

        aFloor.setFloorID(floorID);
        aFloor.setFloorName(floorName);
        Iterable<DataSnapshot> roomsSnapshots = roomSnapshot.getChildren();
        for (DataSnapshot aSingleRoomSnapShot: roomsSnapshots ) {

            aRoom = aFloor.new Room();
            roomName = aSingleRoomSnapShot.child("roomName").getValue(String.class);
            roomID = aSingleRoomSnapShot.child("roomID").getValue(Integer.class);

            areaSnapshot = aSingleRoomSnapShot.child("Area");
            for (DataSnapshot aSingleAreaSnapshot : areaSnapshot.getChildren()) {

                anArea = aFloor.new Area();
                areaID = aSingleAreaSnapshot.child("areaID").getValue(Integer.class);

                macSnapshot = aSingleAreaSnapshot.child("Mac");

                for (DataSnapshot aSingleMacSnapshot : macSnapshot.getChildren()) {

                    macID = aSingleMacSnapshot.child("address").getValue(String.class);
                    lowRead = aSingleMacSnapshot.child("lowRead").getValue(Integer.class);

                    highRead = aSingleMacSnapshot.child("highRead").getValue(Integer.class);
                    anArea.addMac(aFloor.new Mac(macID, lowRead, highRead));
                }

                anArea.setAreaID(areaID);
                aRoom.addArea(anArea);
            }

            aRoom.setRoomID(roomID);
            aRoom.setRoomName(roomName);
            aFloor.addRoom(aRoom);
        }
        return aFloor;
    }
}
