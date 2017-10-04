package com.example.nermi.gitettip;

import android.os.Bundle;
//import android.app.ListFragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import models.Rank;
import utilities.ApiRanking;
import utilities.ApiUtility;
import utilities.AppConstants;

/**
 * A simple {@link ListFragment} subclass.
 * book, page 297
 */
public class ScoresListFragment extends ListFragment {

    public ScoresListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] numbers = {"one", "two", "three", "four"};
        String[] ranks = AppConstants.RANK_STRINGS;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                numbers);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
