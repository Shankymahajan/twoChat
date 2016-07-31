package com.example.shashank.chatapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    TextView mConditionTextView ;
    Button mButtonSunny;
    Button mButtonFoggy;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mconditionRef = mRootRef.child("condition");

    public static final String TAG="mainAcitivty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSunny  = (Button) findViewById(R.id.button_Sunny);
        mButtonFoggy = (Button) findViewById(R.id.button_Foggy);
        mConditionTextView = (TextView) findViewById(R.id.check_view_condition);



    }

    @Override
    protected void onStart() {
        super.onStart();

        mconditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Realtime data which has been changed is coming through dataSnapshot

                String text= dataSnapshot.getValue(String.class);
                mConditionTextView.setText(text);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mButtonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mconditionRef.setValue(" I  love u ! :*");
            }

        });

        mButtonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mconditionRef.setValue("I hate u ! :(");
            }
        });
    }

}
