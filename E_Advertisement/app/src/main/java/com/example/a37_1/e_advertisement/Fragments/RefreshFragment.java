package com.example.a37_1.e_advertisement.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a37_1.e_advertisement.Api.ApiService;
import com.example.a37_1.e_advertisement.Api.RetroClient;
import com.example.a37_1.e_advertisement.LevelAdapter;
import com.example.a37_1.e_advertisement.R;
import com.example.a37_1.e_advertisement.Utils.InternetConnection;
import com.example.a37_1.e_advertisement.model.LevelModel;
import com.example.a37_1.e_advertisement.model.LevelsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RefreshFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button refresh;

    private ListView listView;
    private View parentView;
    private ArrayList<LevelModel> contactList;
    private LevelAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public RefreshFragment() {
        // Required empty public constructor
    }

    public static RefreshFragment newInstance(String param1, String param2) {
        RefreshFragment fragment = new RefreshFragment();
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
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_refresh, container, false);

        refresh = v.findViewById(R.id.refresh);
        contactList = new ArrayList<>();

        parentView = v.findViewById(R.id.parentLayout);
        listView = v.findViewById(R.id.listView);


        refresh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh();

                    }
                });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void refresh() {

        if (InternetConnection.checkConnection(getActivity().getApplicationContext())) {
            ApiService api = RetroClient.getApiService();

            Call<LevelsList> call = api.getMyLevel();

            call.enqueue(new Callback<LevelsList>() {
                @Override
                public void onResponse(Call<LevelsList> call, Response<LevelsList> response) {

                    if (response.isSuccessful()) {
                        contactList = response.body().getLevels();

                        adapter = new LevelAdapter(getContext(), contactList);
                        listView.setAdapter(adapter);

                    } else {
                        System.out.print("not data");
                    }
                }

                @Override
                public void onFailure(Call<LevelsList> call, Throwable t) {
                    System.out.print("Not servak");
                }
            });

        } else {
            System.out.print("Not connection");
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
