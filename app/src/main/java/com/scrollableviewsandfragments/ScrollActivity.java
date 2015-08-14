package com.scrollableviewsandfragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.matrimonysense.android_tutorial_06.R;

import java.util.ArrayList;
import java.util.Iterator;

public class ScrollActivity extends AppCompatActivity
                            implements VerticalScrollFragment.OnVerticalFragmentInteractionListener,
                                       HorizontalScrollFragment.OnHorizontalFragmentInteractionListener,
                                       CombinedFragment.OnCombinedFragmentInteractionListener
                               {

    ArrayList<String> mFragmentTagList;
    String mSampleStringToStore;
    // Key to pass to savedInstanceState
    private static final String KEY = "SAVED_INSTANCE_STATE_KEY";

    // tags for fragments created dynamically
    private static final String VERTICAL_SCROLL_TAG = "VERTICAL_SCROLL_TAG";
    private static final String HORIZONTAL_SCROLL_TAG = "HORIZONTAL_SCROLL_TAG";
    private static final String COMBINED_SCROLL_TAG = "COMBINED_SCROLL_TAG";

    // tags for static fragments
    private String STATIC_FRAGMENT_1_TAG;
    private String STATIC_FRAGMENT_2_TAG;
    private String STATIC_FRAGMENT_3_TAG;

    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        Log.v("ON CREATE ACTIVITY","ON CREATE ACTIVITY");

        mSampleStringToStore = "This is a sample string to store";

        // gets tags of static Fragments from resources
        STATIC_FRAGMENT_1_TAG = getResources().getString(R.string.static_fragment_1);
        STATIC_FRAGMENT_2_TAG = getResources().getString(R.string.static_fragment_2);
        STATIC_FRAGMENT_3_TAG = getResources().getString(R.string.static_fragment_3);

        // initialize array list and store tags in the list
        mFragmentTagList = new ArrayList<String>();

        // store string resources assigned as tags to each Fragment.
        // have a look at the R.layout.activity_scroll to see how to set tags statically
        // storing fragments tags is good practice necessary to add and remove Fragments dynamically, if required

        mFragmentTagList.add(STATIC_FRAGMENT_1_TAG/*getResources().getString(R.string.static_fragment_1)*/);
        mFragmentTagList.add(STATIC_FRAGMENT_2_TAG/*getResources().getString(R.string.static_fragment_2)*/);
        mFragmentTagList.add(STATIC_FRAGMENT_3_TAG/*getResources().getString(R.string.static_fragment_3)*/);

        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.v("ACTIVITY START","ACTIVITY ON START");
    }

    @Override
    public void onResume(){
       super.onResume();
       Log.v("ACTIVITY ON RESUME","ACTIVITY ON RESUME");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.v("ACTIVITY ON PAUSE","ACTIVITY ON PAUSE");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.v("ACTIVITY ON STOP","ACTIVITY ON STOP");
    }


    @Override
    public void onRestart(){
        super.onRestart();
        Log.v("ACTIVITY ON RESTART","ACTIVITY ON RESTART");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v("ACTIVITY ON DESTROY","ACTIVITY ON DESTROY");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scroll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.action_horizontal_scroll){

            clearAllFragments();


            HorizontalScrollFragment fragment = HorizontalScrollFragment.newInstance("Sample String 1","Sample String 2");

            FragmentManager manager = getSupportFragmentManager();

            FragmentTransaction transaction = manager.beginTransaction();

            transaction.add(R.id.scroll_container,fragment,HORIZONTAL_SCROLL_TAG);

            //transaction.addToBackStack(null);

            transaction.commit();

            mFragmentTagList.add(HORIZONTAL_SCROLL_TAG);

            return true;
        }
        else if(id == R.id.action_vertical_scroll){
            // call clear all fragments to remove all fragments from activity
            clearAllFragments();
            VerticalScrollFragment fragment = VerticalScrollFragment.newInstance("Sample String 1","Sample String 2");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.scroll_container,fragment,VERTICAL_SCROLL_TAG);
            //transaction.addToBackStack(null);
            transaction.commit();
            mFragmentTagList.add(VERTICAL_SCROLL_TAG);

            return true;
        }
        else if(id == R.id.action_combined_scroll){

            return true;
        }

        else if(id == R.id.action_pager){

            Intent intent = new Intent(this,SwipeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearAllFragments(){
        // declare an iterator
        Iterator<String> iterator = mFragmentTagList.iterator();
        while(iterator.hasNext()){

            String tag = iterator.next();

            FragmentManager manager = getSupportFragmentManager();

            Fragment fragment = manager.findFragmentByTag(tag);
            // check if returned fragment exists

            if(fragment != null){

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(fragment);
                transaction.commit();
            }
            // remove tag from arraylist
            iterator.remove();
        }// end of while

        // check if all fragment tags have been removed, else clear the list
        if(mFragmentTagList.size() != 0){
            mFragmentTagList.clear();
        }
    }

    // Overriden method from CombinedScrollFragment's Interface
    @Override
    public void onCombinedFragmentInteraction(Uri uri) {

    }

    // Overriden method from HorizontalScrollFragment's Interface
    @Override
    public void onHorizontalFragmentInteraction(Uri uri) {

    }

    @Override
    public void changeMethod(String string) {

        Log.v("Changed text",string);
    }

    // Overriden method from VericalScrollFragment's Interface
    @Override
    public void onVerticalFragmentInteraction(Uri uri) {

    }



    /*
    Fragment declarations begin here
     */

    public static class StaticFragment1 extends Fragment{

        @Override
        public void onAttach(Activity activity){
            super.onAttach(activity);
            Log.v("FRAGMENT ATTACH","FRAGMENT ON ATTACH");
        }

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            Log.v("ON CREATE A","STATIC FRAGMENT A CREATED");
            Log.v("FRAGMENT CREATE","FRAGMENT ON CREATE");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

            Log.v("FRAGMENT CREATE VIEW","FRAGMENT ON CREATE VIEW");
            return inflater.inflate(R.layout.fragment_static_1,container,false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            Log.v("FRAGMENT ACTI CREATED","FRAGMENT ON ACTIVITY CREATED");
        }

        @Override
        public void onStart(){
            super.onStart();
            Log.v("FRAGMENT START","FRAGMENT ON START");
        }

        @Override
        public void onResume(){
            super.onResume();
            Log.v("FRAGMENT RESUME","FRAGMENT ON RESUME");

        }

        @Override
        public void onPause(){
            super.onPause();
            Log.v("FRAGMENT PAUSE","FRAGMENT ON PAUSE");

        }

        @Override
        public void onStop(){
            super.onStop();
            Log.v("FRAGMENT STOP","FRAGMENT ON STOP");
        }

        @Override
        public void onDestroyView(){
            super.onDestroyView();
            Log.v("FRAGMENT DESTROY VIEW","FRAGMENT ON DESTROY VIEW");
        }

        @Override
        public void onDestroy(){
            super.onDestroy();
            Log.v("FRAGMENT DESTROY","FRAGMENT ON DESTROY");
        }

        @Override
        public void onDetach(){
            super.onDetach();
            Log.v("FRAGMENT DETACH","FRAGMENT ON DETACH");
        }

    }

    public static class StaticFragment2 extends Fragment{

        public interface StaticFragment2Listener{

            public void changeText(String text);
        }

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            //Log.v("ON CREATE B","STATIC FRAGMENT B CREATED");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){


            View view = inflater.inflate(R.layout.fragment_static_2,container,false);
            Button button = (Button)view.findViewById(R.id.button);
            final TextView textView = (TextView)view.findViewById(R.id.text_view);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    textView.setText("Changed Text");

                    TextView textView = (TextView)getActivity().findViewById(R.id.text_view);
                    textView.setText("manipulated text");
                }
            });

            return view;
        }

    }

    public static class StaticFragment3 extends Fragment{

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            //Log.v("ON CREATE C","STATIC FRAGMENT C CREATED");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

            return inflater.inflate(R.layout.fragment_static_3,container,false);
        }

    }




}
