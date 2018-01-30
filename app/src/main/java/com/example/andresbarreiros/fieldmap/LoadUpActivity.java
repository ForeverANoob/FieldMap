package com.example.andresbarreiros.fieldmap;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoadUpActivity extends AppCompatActivity {

    public interface Callback{

        void afterGettingData();
    }

    public static Floor newFloor = new Floor();

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

                Log.w("Database", "Connected!");
                LoadUpActivity.newFloor = dataSnapshot.getValue(Floor.class);
                Log.w("Database", dataSnapshot.getValue(Floor.class).toString());
                callback.afterGettingData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
