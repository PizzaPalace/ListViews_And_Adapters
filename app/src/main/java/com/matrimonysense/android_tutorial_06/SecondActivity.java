package com.matrimonysense.android_tutorial_06;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {


    ListView mListView;
    ArrayList<Integer> mArrayList;
    ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int i = intent.getIntExtra("KEY",-1);
        Log.v("integer", Integer.toString(i));

        mListView = (ListView)findViewById(R.id.list_view);
        this.mArrayList = new ArrayList<Integer>();
        this.mArrayList.add(R.drawable.brownies);
        this.mArrayList.add(R.drawable.chocolate_chips);
        this.mArrayList.add(R.drawable.gumdrops);
        this.mArrayList.add(R.drawable.jellybeans);

        this.mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,mArrayList);
        this.mListView.setAdapter(mArrayAdapter);





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
}
