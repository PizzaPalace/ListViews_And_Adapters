package com.broadcastreceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;

public class ReceiverTwo extends BroadcastReceiver implements Serializable {

    public final static String RECEIVER_TWO_KEY ="RECEIVER_TWO_KEY";

    public interface ReceiverTwoListener{

        public void getStringTwo(String input);
    }

    ReceiverTwoListener mListener;

    public ReceiverTwo() {

    }
    public ReceiverTwo(Activity activity){

        try{

            mListener = (ReceiverTwoListener)activity;
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

        String result = intent.getStringExtra("TWO");

        mListener.getStringTwo("receiver two");

    }
}
