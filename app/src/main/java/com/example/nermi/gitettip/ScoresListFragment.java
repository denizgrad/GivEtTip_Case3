package com.example.nermi.gitettip;

import android.os.Bundle;
//import android.app.ListFragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * A simple {@link ListFragment} subclass.
 */
public class ScoresListFragment extends ListFragment {

    public ScoresListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] numbers = {"one", "two", "three", "four"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                numbers);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
////        new ApiRanking().execute();
//
//        // storing string resources into Array
//        String[] numbers = {"one", "two", "three", "four"};
//        // here you store the array of string you got from the database
//
//        // Binding Array to ListAdapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, numbers);
//        this.setListAdapter(adapter);
//
//        // refer the ArrayAdapter Document in developer.android.com
//        ListView lv = getListView();
//
//        // Inflate the layout for this fragment
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//        // We'll define a custom screen layout here (the one shown above), but
//        // typically, you could just use the standard ListActivity layout.
//        setContentView(R.layout.simple_list_item_1);
//
//        // Query for all people contacts using the Contacts.People convenience class.
//        // Put a managed wrapper around the retrieved cursor so we don't have to worry about
//        // requerying or closing it as the activity changes state.
//        mCursor = this.getContentResolver().query(Contacts.People.CONTENT_URI, null, null, null, null);
//        startManagingCursor(mCursor);
//
//        // Now create a new list adapter bound to the cursor.
//        // SimpleListAdapter is designed for binding to a Cursor.
//        ListAdapter adapter = new SimpleCursorAdapter(
//                this, // Context.
//                android.R.layout.two_line_list_item,  // Specify the row template to use (here, two columns bound to the two retrieved cursor
//                rows).
//                mCursor,                                              // Pass in the cursor to bind to.
//        new String[] {People.NAME, People.COMPANY},           // Array of cursor columns to bind to.
//                new int[] {android.R.id.text1, android.R.id.text2});  // Parallel array of which template objects to bind to those columns.
//
//        // Bind to our new adapter.
//        setListAdapter(adapter);
//    }
//}


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        // refer the ArrayAdapter Document in developer.android.com
//        ListView lv = getListView();
//
////        // listening to single list item on click
////        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            public void onItemClick(AdapterView<?> parent, View view,
////                                    int position, long id) {
////
////                // selected item
////                String num = ((TextView) view).getText().toString();
////
////                // Launching new Activity on selecting single List Item
////                Intent i = new Intent(this, MainActivity.class);
////                // sending data to new activity
////                i.putExtra("number", num);
////                startActivity(i);
////            }
////        });
//    }

}
