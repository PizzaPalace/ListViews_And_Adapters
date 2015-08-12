package com.savingstate;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.matrimonysense.android_tutorial_06.R;

public class SavedStateActivity extends AppCompatActivity{

    EditText mNameEditText, mColorEditText, mNumberEditText, mMothertongueEditText;
    TextView mNameText, mColorText, mNumberText, mMothertongueText;
    Button mButton;

    private static final String NAME_KEY = "NAME_KEY";
    private static final String COLOR_KEY = "COLOR_KEY";
    private static final String NUMBER_KEY = "NUMBER_KEY";

    private static final String MOTHERTONGUE_KEY_PREFERENCES = "MOTHERTONGUE_KEY";

    private static final String PREFERENCES_KEY = "PREFERENCES_KEY";

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_state);

        mButton = (Button) findViewById(R.id.button);
        mSharedPreferences = getSharedPreferences(PREFERENCES_KEY,MODE_PRIVATE);


        mNameEditText = (EditText)findViewById(R.id.edit_text_name);
        mColorEditText = (EditText)findViewById(R.id.edit_text_fav_color);
        mNumberEditText = (EditText)findViewById(R.id.edit_text_number);
        mMothertongueEditText = (EditText) findViewById(R.id.edit_text_mothertongue);

        mNameText= (TextView) findViewById(R.id.text_name);
        mColorText = (TextView) findViewById(R.id.text_fav_color);
        mNumberText = (TextView) findViewById(R.id.text_number);
        mMothertongueText = (TextView) findViewById(R.id.text_mother_tongue);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mNameEditText.getText().toString();
                String color = mColorEditText.getText().toString();
                String number = mNumberEditText.getText().toString();
                String mothertongue= mMothertongueEditText.getText().toString();

                mNameText.setText(name);
                mColorText.setText(color);
                mNumberText.setText(number);
                mMothertongueText.setText(mothertongue);

                SharedPreferences.Editor editor = mSharedPreferences.edit();

                editor.putString(MOTHERTONGUE_KEY_PREFERENCES,mothertongue);
                editor.commit();
            }
        });

        // Using persisted data with SharedPreferences
        if(mSharedPreferences != null) {
            String motherTongue = mSharedPreferences.getString(MOTHERTONGUE_KEY_PREFERENCES, "HINDI");
            if(motherTongue != null)
                mMothertongueText.setText(motherTongue);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saved_state, menu);
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
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        String name = mNameEditText.getText().toString();
        if(name == null)
            name="";

        String color = mColorEditText.getText().toString();
        if(color == null)
            color = "";

        String number = mNumberEditText.getText().toString();
        if(number == null)
            number = "";

        savedInstanceState.putString(NAME_KEY,name);
        savedInstanceState.putString(COLOR_KEY,color);
        savedInstanceState.putString(NUMBER_KEY,number);

        Log.v("ON SAVE","ON SAVE");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){

            String name= savedInstanceState.getString(NAME_KEY,"RAM");
            String color = savedInstanceState.getString(COLOR_KEY,"RED");
            String number = savedInstanceState.getString(NUMBER_KEY,"0000");

            mNameEditText.setText(name);
            mColorEditText.setText(color);
            mNumberEditText.setText(number);

            mNameText.setText(name);
            mColorText.setText(color);
            mNumberText.setText(number);
        }

        Log.v("ON RESTORE", "ON RESTORE");
    }
}
