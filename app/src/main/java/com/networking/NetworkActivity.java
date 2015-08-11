package com.networking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.matrimonysense.android_tutorial_06.R;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkActivity extends AppCompatActivity {

    Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new Receiver();

        Intent intent = new Intent(this,HTTPService.class);
        startService(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network, menu);
        return true;
    }


    @Override
    protected void onResume(){
        super.onResume();

        IntentFilter filterReceiver = new IntentFilter(Receiver.RECEIVER_KEY);
        filterReceiver.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver,filterReceiver);

    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(receiver);

    }

    public class Receiver extends BroadcastReceiver {

        public static final String RECEIVER_KEY = "com.ipw.rahul.RECEIVER_KEY";

        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

            try{
                list = (ArrayList<HashMap<String,String>>)intent.getSerializableExtra(HTTPService.KEY);
            }
            catch(ClassCastException exception){
                exception.toString();
            }

            Log.d("STRINGIFIED", list.toString());
        }

    }// end of Receiver
}
