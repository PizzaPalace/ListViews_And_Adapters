package com.intents;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.matrimonysense.android_tutorial_06.R;

public class IntentActivity extends AppCompatActivity implements
                                   IntentFragment.OnFragmentInteractionListener{


    ImageView mImageView;
    public static final int PHOTO_CHOOSER_REQUEST_CODE = 1;
    public static final int CAMERA_CHOOSER_REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        addFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void addFragment(){

        FragmentManager manager = getSupportFragmentManager();
        IntentFragment fragment = IntentFragment.newInstance("","");
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container,fragment,"FRAGMENT");
        transaction.commit();
    }
}
