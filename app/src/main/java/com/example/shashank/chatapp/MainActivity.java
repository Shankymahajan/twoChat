package com.example.shashank.chatapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "mainAcitivty";

    Button sendBtn;
    TextView sendMessage;
    ListView chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.btn_Send);
        sendMessage = (TextView) findViewById(R.id.message_txt);
        chatList = (ListView) findViewById(R.id.chat_list);

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase
                ("https://chatapp-2e2a0.firebaseio.com/");
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "on click listener");
                ChatMessage chat = new ChatMessage("Shashank", sendMessage.getText().toString());
                ref.push().setValue(chat);
                sendMessage.setText("");

            }
        });


       Query recent=ref.equalTo(6).();

       // com.firebase.client.Query recent=ref.equalTo(3);
        FirebaseListAdapter<ChatMessage> adapter = new FirebaseListAdapter<ChatMessage>(
                this,ChatMessage.class,android.R.layout.two_line_list_item,
                recent) {
            @Override
            protected void populateView(View v, ChatMessage chat, int i) {
                ((TextView) v.findViewById(android.R.id.text1)).setText(chat.getName());
                ((TextView) v.findViewById(android.R.id.text2)).setText(chat.getMessage());


            }
        };
        chatList.setAdapter(adapter);


    }
}
