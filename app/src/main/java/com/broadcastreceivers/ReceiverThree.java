package com.broadcastreceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

public class ReceiverThree extends BroadcastReceiver implements Serializable {

    public final static String RECEIVER_THREE_KEY ="RECEIVER_THREE_KEY";

    public interface ReceiverThreeListener{

        public void getStringThree(String input);
    }

    ReceiverThreeListener mListener;

    public ReceiverThree() {

    }

    public ReceiverThree(Activity activity){

        try{

            mListener = (ReceiverThreeListener)activity;
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

        String result = intent.getStringExtra("THREE");

        mListener.getStringThree("receiver three");
    }
}
