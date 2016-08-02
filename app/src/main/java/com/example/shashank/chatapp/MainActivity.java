package com.example.shashank.chatapp;

import android.app.Activity;
import android.app.ListActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    public static final String TAG = "mainAcitivty";
    private Firebase mFirebaseRef ;
    EditText mMessageEditText ;
   // FirebaseListAdapter<ChatMessage> mListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageEditText = (EditText) findViewById(R.id.message_text);
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase
                ("https://chatapp-2e2a0.firebaseio.com/");

        FirebaseListAdapter<ChatMessage> mListAdapter = new FirebaseListAdapter<ChatMessage>
                (mFirebaseRef, ChatMessage.class, R.layout.message_layout, this) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                ((TextView) v.findViewById(R.id.username_text_view)).
                        setText(model.getName());
                ((TextView) v.findViewById(R.id.message_text_view)).setText(model.getMessage());
            }
        };
        setListAdapter(mListAdapter);
    }
    public void onSendButtonClick(View v) {
        String message = mMessageEditText.getText().toString();
        Map<String,Object> values = new HashMap<>();
        values.put("name","shashank");
        values.put("message",message);
        mFirebaseRef.push().setValue(values);
        mMessageEditText.setText("");
    }
}
//
//    Button sendBtn;
//    TextView sendMessage;
//    ListView chatList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        sendBtn = (Button) findViewById(R.id.btn_Send);
//        sendMessage = (TextView) findViewById(R.id.message_txt);
//        chatList = (ListView) findViewById(R.id.chat_list);
//
//        Firebase.setAndroidContext(this);
//        final Firebase ref = new Firebase
//                ("https://chatapp-2e2a0.firebaseio.com/");
//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "on click listener");
//                ChatMessage chat = new ChatMessage("Shashank", sendMessage.getText().toString());
//                ref.push().setValue(chat);
//                sendMessage.setText("");
//
//            }
//        });
//
//
//       Query recent=ref.equalTo(6).();
//
//       // com.firebase.client.Query recent=ref.equalTo(3);
//        FirebaseListAdapter<ChatMessage> adapter = new FirebaseListAdapter<ChatMessage>(
//                this,ChatMessage.class,android.R.layout.two_line_list_item,
//                recent) {
//            @Override
//            protected void populateView(View v, ChatMessage chat, int i) {
//                ((TextView) v.findViewById(android.R.id.text1)).setText(chat.getName());
//                ((TextView) v.findViewById(android.R.id.text2)).setText(chat.getMessage());
//
//
//            }
//        };
//        chatList.setAdapter(adapter);
//
//
//    }
//}
