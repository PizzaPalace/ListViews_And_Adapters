package com.json;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.matrimonysense.android_tutorial_06.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JSONActivity extends AppCompatActivity {

    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        String output1 = getJSON(R.raw.json_1);

        String output2 = getJSON(R.raw.json_cricket);
        processOutput2(output2);

        String output3 = getJSON(R.raw.json_primes);
        processOutput3(output3);

        String output4 = getJSON(R.raw.name_height);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_json, menu);
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

        else if(id == android.R.id.home){

            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getJSON(int resourceId){

        char[] buffer = new char[2048];

        try{
            // Obtain an InputStream from the raw resource
            InputStream inputStream = getResources().openRawResource(resourceId);

            // Initialize a BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Create a writer object
            Writer writer = new StringWriter();

            // loop through BufferedReader until it has read all characters
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

            // This is very important! Do not forget to close your BufferedReader
            reader.close();
            // This is again very important ! Do not forget to close InputStream
            inputStream.close();

            // Print JSON to output
            Log.v("JSON", writer.toString());

            return writer.toString();

        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

    // Study the underlying JSON well before using this method
    private void processOutput2(String output){

        try {
            // Since the JSON output begins with curly-braces this implies that it
            // is a JSONObject, hence use the JSONObject class
            JSONObject jsonObject = new JSONObject(output);

            int length = jsonObject.length();

            HashMap<String,String> map = new HashMap<String,String>();
            // Iterating through a JSON Object requires an iterator mapped to its keys
            Iterator<String> iterator = jsonObject.keys();
            while(iterator.hasNext()){

                String key = iterator.next();
                // Obtain values from keys
                map.put(key,jsonObject.getString(key));
            }

            Log.v("MAP",map.toString());
        }
        // Has to be implemented, otherwise compilation will fail - remove the try and catch-blocks
        catch(JSONException jsonException){

            jsonException.printStackTrace();
        }
        catch(Exception exception){

            exception.printStackTrace();
        }
    }

    // Study the underlying JSON to parse well before using this method
    private void processOutput3(String output){

        try{

            // Since the JSON output begins with curly-braces this implies that it
            // is a JSONObject, hence use the JSONObject class
            JSONObject jsonObject = new JSONObject(output);

            // Square braces imply that contents are in an array, hence use the JSONArray class
            JSONArray jsonArray = jsonObject.getJSONArray("primes");

            int length = jsonArray.length();
            // initialize an ArrayList and a HashMap to store values
            ArrayList<Integer> list = new ArrayList<Integer>();
            HashMap<String,Integer> map = new HashMap<String,Integer>();

            for(int index=0; index<length; index++){

                JSONObject jObject = jsonArray.getJSONObject(index);

                Iterator<String> iterator =jObject.keys();
                while(iterator.hasNext()){
                    String key = iterator.next();
                    Integer value = jObject.getInt(key);
                    map.put(key,value);
                }
            }

            Log.v("PRIMES",map.toString());



        }
        catch(JSONException jsonException){

            jsonException.printStackTrace();
        }
    }
}
