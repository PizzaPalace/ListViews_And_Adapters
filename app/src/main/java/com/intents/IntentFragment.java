package com.intents;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.matrimonysense.android_tutorial_06.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IntentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IntentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView mImageView;
    Button mButtonLeft, mButtonRight;

    public static final int PHOTO_CHOOSER_REQUEST_CODE = 1;
    public static final int CAMERA_CHOOSER_REQUEST_CODE = 20;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IntentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IntentFragment newInstance(String param1, String param2) {
        IntentFragment fragment = new IntentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public IntentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intent, container, false);
        mImageView = (ImageView)view.findViewById(R.id.stretched_image);
        mButtonLeft = (Button)view.findViewById(R.id.button);
        mButtonRight = (Button)view.findViewById(R.id.camera_button);

        mButtonLeft.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                Intent intent =  new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select chooser"),PHOTO_CHOOSER_REQUEST_CODE);

            }
        });

        mButtonRight.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_CHOOSER_REQUEST_CODE);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
         super.onActivityCreated(savedInstanceState);
        /*Intent intent = new Intent();
        intent.addCategory(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.v("REQUEST_CODE",Integer.toString(requestCode));
        Log.v("RESULT_CODE",Integer.toString(resultCode));
        //Uri selectedImageUri = data.getData();

        if(requestCode == PHOTO_CHOOSER_REQUEST_CODE){

            if(resultCode == getActivity().RESULT_OK){
                Log.v("WELL INSIDE", "WELL INSIDE");
                Uri uri = data.getData();

                if(uri == null){
                    return;
                }

                else{

                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();
                    int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String path = cursor.getString(column_index);

                    Drawable drawable = Drawable.createFromPath(path);
                    mImageView.setImageDrawable(drawable);

                }

            }
        }
        else if(requestCode == CAMERA_CHOOSER_REQUEST_CODE){

            if(resultCode == getActivity().RESULT_OK){

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImageView.setImageBitmap(imageBitmap);

            }

        }
    }
}
