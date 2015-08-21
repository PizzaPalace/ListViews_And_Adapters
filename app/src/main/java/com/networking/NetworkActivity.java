package com.networking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.matrimonysense.android_tutorial_06.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class NetworkActivity extends AppCompatActivity {

    Receiver receiver;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        receiver = new Receiver();

        mImageView = (ImageView)findViewById(R.id.image_view);

        Intent intent = new Intent(this,HTTPService.class);
        startService(intent);

String str="a";
        Log.v("a",Integer.toString(str.hashCode()));
        str=str+"b";
        Log.v("b",Integer.toString(str.hashCode()));
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



            try{
                final ArrayList<String> list = (ArrayList<String>)intent.getSerializableExtra(HTTPService.KEY);
                Log.v("FROM ACT",list.toString());
                new MyAsyncTask().execute(list.get(0));
            }
            catch(ClassCastException exception){
                exception.toString();
            }


        }

    }// end of Receiver

    public Bitmap loadBitmap(String url) {

        try {
            URL photolink = new URL(url);
            Bitmap bitmap = BitmapFactory.decodeStream(photolink.openConnection().getInputStream());
            return bitmap;
        }
        catch(MalformedURLException exception){
            exception.printStackTrace();
            return null;
        }
        catch(Exception exception){
            exception.printStackTrace();




            return null;


        }
    }

    private class MyAsyncTask extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = loadBitmap(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null)
                mImageView.setImageBitmap(bitmap);
        }
    }
}
