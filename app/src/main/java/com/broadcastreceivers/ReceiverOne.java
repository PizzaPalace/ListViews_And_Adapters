package com.broadcastreceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;

public class ReceiverOne extends BroadcastReceiver implements Serializable {

    public final static String RECEIVER_ONE_KEY ="RECEIVER_ONE_KEY";

    ReceiverOneListener mListener;

    public interface ReceiverOneListener{

        public void getStringOne(String input);
    }

    public ReceiverOne() {


    }


    public ReceiverOne(Activity activity){

        try{

            mListener = (ReceiverOneListener)activity;
        }
        catch(Exception exception){

            exception.printStackTrace();
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        String result = intent.getStringExtra("ONE");

        mListener.getStringOne(result);

    }


}