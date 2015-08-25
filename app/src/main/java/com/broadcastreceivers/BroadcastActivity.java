package com.broadcastreceivers;


import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.matrimonysense.android_tutorial_06.R;


public class BroadcastActivity extends AppCompatActivity
        implements BroadcastFragment.OnFragmentInteractionListener,
        ReceiverOne.ReceiverOneListener,
        ReceiverTwo.ReceiverTwoListener,
        ReceiverThree.ReceiverThreeListener {


    ReceiverOne mReceiverOne;
    ReceiverTwo mReceiverTwo;
    ReceiverThree mReceiverThree;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // intialize broadcast receivers using custom constructors
        mReceiverOne = new ReceiverOne(this);
        mReceiverTwo = new ReceiverTwo(this);
        mReceiverThree = new ReceiverThree(this);



        // adds Fragment to activity
        populateFragment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_broadcast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populateFragment(){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        BroadcastFragment fragment = new BroadcastFragment();
        // add receivers to bundle so that they can be accessed inside Fragment

        Bundle bundle = new Bundle();
        bundle.putSerializable("FRAGMENT_ONE",mReceiverOne);
        bundle.putSerializable("FRAGMENT_TWO",mReceiverTwo);
        bundle.putSerializable("FRAGMENT_THREE",mReceiverThree);

        // data obtained from bundle using getArguments method inside Fragment
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment, "FRAGMENT");
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        Log.v("inside fragment", uri.toString());
    }

    @Override
    public void onResume(){
        super.onResume();
        // register receivers in onResume()

        IntentFilter intentFilterOne = new IntentFilter(Intent.ACTION_DEFAULT/*ReceiverOne.RECEIVER_ONE_KEY*/);
        intentFilterOne.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mReceiverOne, intentFilterOne);

        IntentFilter intentFilterTwo = new IntentFilter(Intent.ACTION_DEFAULT/*ReceiverTwo.RECEIVER_TWO_KEY*/);
        intentFilterTwo.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mReceiverTwo,intentFilterTwo);

        IntentFilter intentFilterThree = new IntentFilter(Intent.ACTION_DEFAULT/*ReceiverThree.RECEIVER_THREE_KEY*/);
        intentFilterThree.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mReceiverThree,intentFilterThree);
    }

    @Override
    public void onStop(){
        super.onStop();
        // unregister receivers in onStop
        unregisterReceiver(mReceiverOne);
        unregisterReceiver(mReceiverTwo);
        unregisterReceiver(mReceiverThree);
    }


    @Override
    public void getStringOne(String input) {

        FragmentManager manager = getSupportFragmentManager();
        BroadcastFragment fragment = (BroadcastFragment)manager.findFragmentByTag("FRAGMENT");
        fragment.changeTextOne(input);

        Toast.makeText(this,"ED1",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getStringTwo(String input) {

        FragmentManager manager = getSupportFragmentManager();
        BroadcastFragment fragment = (BroadcastFragment)manager.findFragmentByTag("FRAGMENT");
        fragment.changeTextTwo(input);

        Toast.makeText(this,"ED2",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getStringThree(String input) {


        FragmentManager manager = getSupportFragmentManager();
        BroadcastFragment fragment = (BroadcastFragment)manager.findFragmentByTag("FRAGMENT");
        fragment.changeTextThree(input);

    }
}


