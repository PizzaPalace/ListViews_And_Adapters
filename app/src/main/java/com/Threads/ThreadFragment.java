package com.Threads;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.matrimonysense.android_tutorial_06.R;

import java.util.HashSet;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThreadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThreadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThreadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView mTextView;
    MyAsyncTask mTask;
    Handler mHandler,mHandlerLeft, mHandlerRight;
    Button mButtonLeft,mButtonRight;
    ThreadService mService;
    ServiceConnection mServiceConnection;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThreadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThreadFragment newInstance(String param1, String param2) {
        ThreadFragment fragment = new ThreadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ThreadFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_thread, container, false);

        mTextView = (TextView)view.findViewById(R.id.text_view);
        mButtonLeft = (Button) view.findViewById(R.id.button_one);

        mHandlerLeft = new Handler();
        mHandlerRight = new Handler();

        Looper looperLeft = mHandlerLeft.getLooper();
        Looper looperRight = mHandlerRight.getLooper();

        Thread threadLeft = looperLeft.getThread();
        Thread threadRight = looperRight.getThread();

        Log.v("THREAD LEFT",threadLeft.getName());
        Log.v("THREAD RIGHT",threadRight.getName());

        mButtonLeft.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mHandlerLeft.post(new Runnable(){

                    @Override
                    public void run() {

                        Log.v("HANDLER LEFT","HANDLER LEFT");
                    }
                });
            }
        });

        mButtonRight = (Button) view.findViewById(R.id.button_two);
        mButtonRight.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                /*mHandlerRight.post(new Runnable(){

                    @Override
                    public void run() {


                        Log.v("HANDLER RIGHT","HANDLER RIGHT");
                        Intent intent = new Intent(getActivity(),ThreadService.class);
                        getActivity().bindService(intent,mServiceConnection, getActivity().BIND_AUTO_CREATE);
                    }
                });*/

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
       super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onResume(){
        super.onResume();

        mTask = new MyAsyncTask();
        mTask.execute();

        mHandler = new Handler();

        mHandler.post(new Runnable(){

            @Override
            public void run() {

                mTextView.setText("FIRST");
            }
        });

        mTextView.setText("SECOND");
        String string = mTextView.getText().toString();
        Log.v("OUTPUT",string);

        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {

                mTextView.setText("FROM EXTERNAL THREAD");
                String output = mTextView.getText().toString();
                Log.v("EXTERNAL THREAD",output);
            }
        });

        thread.run();
        String name = thread.getName();
        Log.v("NAME",name);

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                Log.v("CONNECTED","ON SERVICE CONNECTED");

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

                Log.v("DISCONNECTED","ON SERVICE DISCONNECTED");
            }
        };

        Intent intent = new Intent(getActivity(),ThreadService.class);
        getActivity().startService(intent);
        //getActivity().bindService(intent,mServiceConnection, getActivity().BIND_AUTO_CREATE);



    }

    @Override
    public void onPause(){
        super.onPause();

        //getActivity().unbindService(mServiceConnection);

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

    private class MyAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {

            mTextView.setText("FOO");

            return null;
        }
    }


}
