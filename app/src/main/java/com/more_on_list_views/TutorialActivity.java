package com.more_on_list_views;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.matrimonysense.android_tutorial_06.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TutorialActivity extends ListActivity {

    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mArrayList;
    ListView mListView;
    Spinner mDyanamicSpinner;
    SpinnerAdapter mSpinnerAdapter;
    ArrayList<String> mSpinnerList;
    EditText mEditText;
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        // Initialize the ListView and assign adapter

        Intent intent = getIntent();
        int x = intent.getIntExtra("total",-1);

        mListView = getListView();

        mArrayList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_list)));
        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mArrayList);
        mListView.setAdapter(mArrayAdapter);
        // Initialize the spinner
        mDyanamicSpinner = (Spinner)findViewById(R.id.dynamic_spinner);

        mSpinnerList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.days_of_week)));
        mSpinnerList.addAll(mSpinnerList);
        mSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mSpinnerList);
        mDyanamicSpinner.setAdapter(mSpinnerAdapter);
        // setOnItemSelectedListener for Spinners
        mDyanamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.v("item selected", mSpinnerList.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Log.v("nothing","nothing");
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // setDrawerListener for DrawerLayout
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {

                Log.v("ON SLIDE",view.toString());
                Log.v("FLOAT OFFSET",Float.toString(v));
            }

            @Override
            public void onDrawerOpened(View view) {

                Log.v("ON OPENED",view.toString());
            }

            @Override
            public void onDrawerClosed(View view) {

                Log.v("ON CLOSED",view.toString());
            }

            @Override
            public void onDrawerStateChanged(int i) {

                Log.v("ON STATE CHANGED",Integer.toString(i));
            }
        });

        // addTextChangedListener for EditText
        mEditText = (EditText)findViewById(R.id.edit_text);
        mEditText.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                // Enter your code here
                String text = mEditText.getText().toString();
                Log.v("TEXT",text);

                // perform filtering operation here

                // to be called after you have manipulated your data
                //mArrayAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
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
