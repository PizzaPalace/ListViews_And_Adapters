package com.matrimonysense.android_tutorial_06;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
                startActivity(intent);

            }
        });

        // Declare and assign variables, a String Array and a string variable
        //String[] stringArray = {"alpha","beta","gamma","delta","epsilon","phi"};
        //String element = "testString";

        //String output = getGenericElement(element,stringArray);
        //Log.v("OUTPUT",output);

        // Don't do this, assigning different types to the same generic type
        //Integer sampleInteger = 100;
        //String output2 = getGenericElement(sampleInteger,stringArray);

        int[] x = {1,2,3,4};
        int[] y = {1,2,3,4};

        // comaparing arrays using == operator
        /*if(x == x){

            Log.v("equal","equal");
        }
        else
        {
            Log.v("unequal","unequal");
        }*/

        // comparing arrays using Arrays.equals() method
        /*if(Arrays.equals(x,y)){

            Log.v("x equals y","x equals y");
        }
        else{

            Log.v("x not equals y","x not equals y");
        }*/

        /***********************************************************/
        Integer[] sampleIntegerArray = {23,45,67,89,23,56};
        Animals[] animals = {new Animals(), new Animals(), new Animals(), new Animals()};

        // returns unique hashcode of each object, isn't always guaranteed to be unique
        /*for(Animals animal : animals){

            Log.v("HASHCODE",Integer.toString(animal.hashCode()));
        }*/

        // check equality when same object array is passed as arguments,will be equal
        //getGenericElements(sampleIntegerArray, sampleIntegerArray);

        Integer[] outputArray = getGenericElements(sampleIntegerArray,animals);
        //Log.v("OUTPUT ARRAY", outputArray.toString());
        //Log.v("ORIGNAL ARRAY", sampleIntegerArray.toString());

        // initialize array of ints, manipulating arrays inside functions
        int[] arrayOfInts = new int[10];
        int size = arrayOfInts.length;

        // assign values to arrays
        for(int i=0; i<size; i++){

            arrayOfInts[i] = i+2;
        }

        Log.v("INPUT ARRAY BEFORE",printArray(arrayOfInts));

        int[] outputIntArray = manipulateArray(arrayOfInts);

        Log.v("OUTPUT ARRAY",printArray(outputIntArray));
        Log.v("INPUT ARRAY AFTER",printArray(arrayOfInts));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    // passing generic arguments to functions - example 1
    public <T> T getGenericElement(T element, T[] groupOfElements){
        int size = groupOfElements.length;
        for(int index=0; index < size; index++){

            Log.e("INDEX "+ Integer.toString(index),     groupOfElements[index].toString());
        }
        return element;
    }

    // passing generic arguments to functions -example 2
    public <E,T> E[] getGenericElements(E[] arrayOfElements, T[] targetArray){

        if(targetArray == arrayOfElements){
            Log.v("Arrays are equal","Arrays are equal");
        }
        else {
            Log.v("Unequal arrays", "Unequal arrays");
        }

        return arrayOfElements;
    }

    // manipulating arrays

    public int[] manipulateArray(int[] inputArray){

        int size = inputArray.length;
        for(int i=0; i<size; i++){

           inputArray[i] = inputArray[i] + 10;

        }
        return inputArray;
    }


    // print array
    public String printArray(int[] array){

        StringBuilder stringBuilder = new StringBuilder();
        int lengthInput = array.length;

        for(int i=0; i<lengthInput; i++ ){

            String str = Integer.toString(array[i]) + " ";
            stringBuilder.append(str);

        }

        return stringBuilder.toString();
    }
}
