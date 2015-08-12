package com.scrollableviewsandfragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.matrimonysense.android_tutorial_06.R;

public class SwipeActivity extends AppCompatActivity
                                  implements VerticalScrollFragment.OnVerticalFragmentInteractionListener,
                                             HorizontalScrollFragment.OnHorizontalFragmentInteractionListener,
                                             CombinedFragment.OnCombinedFragmentInteractionListener{

    ViewPager mViewPager;
    MyPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipe, menu);
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
    public void onCombinedFragmentInteraction(Uri uri) {

    }

    @Override
    public void onHorizontalFragmentInteraction(Uri uri) {

    }

    @Override
    public void changeMethod(String string) {

    }

    @Override
    public void onVerticalFragmentInteraction(Uri uri) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            if(i == 0)
                return new VerticalScrollFragment();
            else if(i == 1)
                return new HorizontalScrollFragment();
            else if(i == 2)
                return new CombinedFragment();

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
