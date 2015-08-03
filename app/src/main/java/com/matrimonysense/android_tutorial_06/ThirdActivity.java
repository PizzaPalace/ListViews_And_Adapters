package com.matrimonysense.android_tutorial_06;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ThirdActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Boolean> mArrayboolean;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> mArrayList;
    LayoutInflater mLayoutInflater;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_third);

           setTitle("ListView basic example");

            mLayoutInflater = getLayoutInflater();

            // try putting this in a static ArrayList in another class
            mArrayList = new ArrayList<String>();
            mArrayList.add("assaam");
            mArrayList.add("bihar");
            mArrayList.add("Cochin");
            mArrayList.add("Delhi");
            mArrayList.add("Elephanta");
            mArrayList.add("Faridabad");
            mArrayList.add("Gaziabad");
            mArrayList.add("Harayana");

            mArrayList.add("Itanagar");
            mArrayList.add("Jaipur");
            mArrayList.add("Kashmir");
            mArrayList.add("Lucknow");
            mArrayList.add("Mumbai");
            mArrayList.add("Nagpur");

            mArrayList.add("Ooty");
            mArrayList.add("Pondicheery");
            mArrayList.add("Quaid-e-millat");
            mArrayList.add("Raipur");
            mArrayList.add("Shilliong");
            mArrayList.add("Telangana");


            mArrayList.add("Udaipur");
            mArrayList.add("Vellore");
            mArrayList.add("Warangal");
            mArrayList.add("X-mas nagar");
            mArrayList.add("Yewatmal");
            mArrayList.add("miZoram");

            mArrayList.add("0");
            mArrayList.add("1");
            mArrayList.add("2");
            mArrayList.add("3");
            mArrayList.add("4");
            mArrayList.add("5");


            mArrayList.add("Quaid-e-millat");
            mArrayList.add("Raipur");
            mArrayList.add("Shilliong");
            mArrayList.add("Telangana");


            mArrayList.add("Udaipur");
            mArrayList.add("Vellore");
            mArrayList.add("Warangal");
            mArrayList.add("X-mas nagar");
            mArrayList.add("Yewatmal");
            mArrayList.add("miZoram");

            mArrayList.add("0");
            mArrayList.add("1");
            mArrayList.add("2");
            mArrayList.add("3");
            mArrayList.add("4");
            mArrayList.add("5");

          int size  =mArrayList.size();
        mArrayboolean= new ArrayList<Boolean>();
            this.mListView = (ListView) findViewById(R.id.list_view);

        for(int i=0;i<size;i++)
        {
            mArrayboolean.add(false);
        }


            // Uncomment to use ArrayAdapter, comment MyAdapter while using this
            //this.mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,mArrayList);
            //mListView.setAdapter(mArrayAdapter);

            // Assign MyAdapter to ListView
            myAdapter = new MyAdapter();
            mListView.setAdapter(myAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.v("String","Listitemclicked");
                    Log.v("PARENT",parent.toString());
                    view.setBackgroundColor(Color.BLUE);
                    Boolean status=mArrayboolean.get(position);
                    if (status)
                    {
                    mArrayboolean.set(position,false);

                    }
                    else
                    {
                    mArrayboolean.set(position,true);


                    }

                   myAdapter.notifyDataSetChanged();
                }
            });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_third, menu);
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
            return mArrayList.size();
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

            Log.v("POSITION",Integer.toString(position));
            if(convertView == null){

                convertView = mLayoutInflater.inflate(R.layout.list_element,parent,false);
            }

            final TextView textView = (TextView)convertView.findViewById(R.id.text_view);
            textView.setText(mArrayList.get(position));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setTextColor(Color.RED);
                    Log.v("TextView","textviewclicked");
                }
            });
            Boolean newStatus= mArrayboolean.get(position);
            if(newStatus==true){
                convertView.setBackgroundColor(Color.BLUE);
            }
            else{
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }

            return convertView;
        }
    }
}
