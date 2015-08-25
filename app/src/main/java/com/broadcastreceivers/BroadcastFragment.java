package com.broadcastreceivers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.matrimonysense.android_tutorial_06.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BroadcastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BroadcastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class BroadcastFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button mButtonOne, mButtonTwo, mButtonThree;
    TextView mTextViewOne, mTextViewTwo, mTextViewThree;
    EditText mEditTextOne, mEditTextTwo, mEditTextThree;

    ReceiverOne mReceiverOne;
    ReceiverTwo mReceiverTwo;
    ReceiverThree mReceiverThree;



    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BroadcastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BroadcastFragment newInstance(String param1, String param2) {
        BroadcastFragment fragment = new BroadcastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BroadcastFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            mReceiverOne = (ReceiverOne)getArguments().getSerializable("FRAGMENT_ONE");
            mReceiverTwo = (ReceiverTwo)getArguments().getSerializable("FRAGMENT_TWO");
            mReceiverThree= (ReceiverThree)getArguments().getSerializable("FRAGMENT_THREE");

            Log.v("ONE",mReceiverOne.toString());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_broadcast, container, false);

        mButtonOne = (Button)view.findViewById(R.id.first_button);
        mButtonTwo = (Button)view.findViewById(R.id.second_button);
        mButtonThree = (Button)view.findViewById(R.id.third_button);

        mTextViewOne = (TextView)view.findViewById(R.id.text_view_one);
        mTextViewTwo = (TextView)view.findViewById(R.id.text_view_two);
        mTextViewThree = (TextView)view.findViewById(R.id.text_view_three);

        mEditTextOne = (EditText)view.findViewById(R.id.edit_text_one);
        mEditTextTwo = (EditText)view.findViewById(R.id.edit_text_two);
        mEditTextThree = (EditText)view.findViewById(R.id.edit_text_three);

        mButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*ReceiverOne.RECEIVER_ONE_KEY*/
                String input = mEditTextOne.getText().toString();
                Intent broadcastintent = new Intent();
                broadcastintent.addCategory(Intent.CATEGORY_DEFAULT);
                broadcastintent.setAction(Intent.ACTION_DEFAULT);
                broadcastintent.putExtra("ONE", input);
                //getActivity().sendBroadcast(broadcastintent);
                getActivity().sendOrderedBroadcast(broadcastintent,null);



            }
        });

        mButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = mEditTextTwo.getText().toString();
                Intent broadcastintent = new Intent();
                broadcastintent.addCategory(Intent.CATEGORY_DEFAULT);
                broadcastintent.setAction(Intent.ACTION_DEFAULT/*ReceiverTwo.RECEIVER_TWO_KEY*/);
                broadcastintent.putExtra("TWO",input);
                getActivity().sendBroadcast(broadcastintent);

            }
        });

        mButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = mEditTextThree.getText().toString();
                Intent broadcastintent = new Intent();
                broadcastintent.addCategory(Intent.CATEGORY_DEFAULT);
                broadcastintent.setAction(Intent.ACTION_DEFAULT/*ReceiverThree.RECEIVER_THREE_KEY*/);
                broadcastintent.putExtra("THREE",input);
                getActivity().sendBroadcast(broadcastintent);

            }
        });

        return view;
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


    public void changeTextOne(String string){

        mTextViewOne.setText(string);
    }

    public void changeTextTwo(String string){

        mTextViewTwo.setText(string);
    }

    public void changeTextThree(String string){

        mTextViewThree.setText(string);
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

}
