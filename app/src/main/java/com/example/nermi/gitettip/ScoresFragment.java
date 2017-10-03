package com.example.nermi.gitettip;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import utilities.ApiRanking;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoresFragment extends ListFragment {

    public ScoresFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // storing string resources into Array
        String[] numbers = {"one","two","three","four"};
        // here you store the array of string you got from the database

        // Binding Array to ListAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, numbers);
        this.setListAdapter(adapter);


        // refer the ArrayAdapter Document in developer.android.com
        ListView lv = getListView();

//        // listening to single list item on click
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                // selected item
//                String num = ((TextView) view).getText().toString();
//
//                // Launching new Activity on selecting single List Item
//                Intent i = new Intent(this, MainActivity.class);
//                // sending data to new activity
//                i.putExtra("number", num);
//                startActivity(i);
//            }
//        });
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        new ApiRanking().execute();
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_scores, container, false);
//    }
}
