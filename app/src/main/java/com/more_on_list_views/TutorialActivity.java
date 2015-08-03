package com.more_on_list_views;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.matrimonysense.android_tutorial_06.R;

import java.util.ArrayList;
import java.util.Arrays;

public class TutorialActivity extends ListActivity {

    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mArrayList;
    ListView mListView;
    Spinner mDyanamicSpinner;
    SpinnerAdapter mSpinnerAdapter;
    ArrayList<String> mSpinnerList;
    EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        // Initialize the ListView and assign adapter
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
