package com.com.training.android_tutorial_07;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.matrimonysense.android_tutorial_06.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Tutorial_7Activity extends AppCompatActivity{

    ArrayList<Integer> mContinentImageList;
    ArrayList<String> mContinentNameList;
    LayoutInflater mInflater;
    ListView mListView;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_7);

        setTitle("ListViews with Images");

        mContinentImageList = new ArrayList<Integer>(Arrays.asList(DataClass.imageData));
        mContinentNameList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.continents)));
        mInflater = getLayoutInflater();
        mListView = (ListView)findViewById(R.id.list_view);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial_7, menu);
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

    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            // get size of array to populate from DataClass, no initializations required because
            // ARRAY_LENGTH is a static variable
            return DataClass.ARRAY_LENGTH;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){

                convertView = mInflater.inflate(R.layout.list_element_tutorial_7,parent,false);

            }

            TextView textView = (TextView)convertView.findViewById(R.id.text_view);
            textView.setText(mContinentNameList.get(position));

            ImageView imageView = (ImageView)convertView.findViewById(R.id.image_view);
            imageView.setImageResource(mContinentImageList.get(position));

            return convertView;
        }
    }
}
