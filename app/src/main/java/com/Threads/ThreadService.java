package com.Threads;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadService extends Service {

    Timer mTimer;
    Handler mHandler;
    int mCount = 0;
    private final IBinder mBinder = new LocalBinder();

    public ThreadService() {

    }

    public class LocalBinder extends Binder {
        ThreadService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ThreadService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("ON CREATE", "ON CREATE");
        mHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.v("ON START COMMAND","ON START COMMAND");
        Log.v("FLAGS",Integer.toString(flags));
        Log.v("START ID",Integer.toString(startId));

        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {

                mCount++;
                Log.v("TIMER TASK",Integer.toString(mCount));
            }
        },2000,3000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        Log.v("ON BIND","ON BIND");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.v("ON UNBIND","ON UNBIND");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        Log.v("ON DESTROY","ON DESTROY");
    }
}
