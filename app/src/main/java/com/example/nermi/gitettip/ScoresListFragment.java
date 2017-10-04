package com.example.nermi.gitettip;

import android.os.Bundle;
//import android.app.ListFragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

import models.Rank;
import utilities.ApiRanking;

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

        try {
            List<Rank> ranks = new ApiRanking().execute().get();
            String[] ranksStrings = new String[ranks.size()];
            int i = 0;
            for (Rank r : ranks) {
                ranksStrings[i] = r.getScoreString();
                i++;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    inflater.getContext(), android.R.layout.simple_list_item_1,
                    ranksStrings);
            setListAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error", e.toString());
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
