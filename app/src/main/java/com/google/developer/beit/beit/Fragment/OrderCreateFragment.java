package com.google.developer.beit.beit.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.developer.beit.beit.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderCreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderCreateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static Boolean[] careButtonsState = {false, false, false, false, false, false};
    private static Boolean[] methodButtonsState = {false, false, false};

    public OrderCreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderCreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderCreateFragment newInstance(String param1, String param2) {
        OrderCreateFragment fragment = new OrderCreateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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


        View view = inflater.inflate(R.layout.fragment_order_create, container, false);


        final ImageView[] methodButtons = new ImageView[3];
        methodButtons[0] = view.findViewById(R.id.order_button_walk);
        methodButtons[1] = view.findViewById(R.id.order_button_bike);
        methodButtons[2] = view.findViewById(R.id.order_button_car);
        for (int i = 0; i < 3; i++) {
            methodButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = 0;
                    for (; i < 3; i++) {
                        methodButtonsState[i] = v.equals(methodButtons[i]);
                    }
                    mListener.onMethodButtonClick(v, methodButtons);
                }
            });
        }

        final ImageView[] careButtons = new ImageView[6];
        careButtons[0] = view.findViewById(R.id.order_care_broken);
        careButtons[1] = view.findViewById(R.id.order_care_updown);
        careButtons[2] = view.findViewById(R.id.order_care_careful);
        careButtons[3] = view.findViewById(R.id.order_care_water);
        careButtons[4] = view.findViewById(R.id.order_care_cutter);
        careButtons[5] = view.findViewById(R.id.order_care_sunlight);
        for (int i = 0; i < 6; i++) {
            careButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = 0;
                    for (; i < 6; i++) {
                        if (v.equals(careButtons[i])) {
                            careButtonsState[i] = !careButtonsState[i];
                            break;
                        }
                    }

                    mListener.onCareButtonClick(v, careButtonsState[i]);
                }
            });
        }



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void onMethodButtonClick(View v, ImageView[] buttons);

        void onCareButtonClick(View v, boolean state);

    }


}
