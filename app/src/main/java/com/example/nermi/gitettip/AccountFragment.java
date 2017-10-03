package com.example.nermi.gitettip;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import models.User;
import utilities.ApiUtility;
import utilities.SharedPreferencesUtility;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        //show user info
//        int userId = SharedPreferencesUtility.readValue(getActivity(), "userId");
//        try {
//            User response = ApiUtility.getHttpGetResponse("users", String.valueOf(userId), User.class);
//            ((TextView)view.findViewById(R.id.scoreValue)).setText(response.getName());
//            ((TextView)view.findViewById(R.id.emailValue)).setText(response.getEmail());
//            ((TextView)view.findViewById(R.id.nameValue)).setText(response.getName());
//        } catch (Exception e) {
//            Log.e("ERROR: ",e.getLocalizedMessage());
//        }
    }
}
