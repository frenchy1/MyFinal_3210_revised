package com.example.myfinal_3210;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/*
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment
       implements View.OnClickListener{
        private ListView itemsListView;
        private EditText oNameEdit;
        private Button oNameInsert;
        private PlayerDB db;


    public LeaderBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leader_board, container, false);
        itemsListView = (ListView) view.findViewById(R.id.itemsListView);
        oNameEdit = (EditText) view.findViewById(R.id.nameEditText);
        oNameInsert = (Button) view.findViewById(R.id.insertButton);
        oNameInsert.setOnClickListener(this);

        db = new PlayerDB(this.getContext());
        updateDisplay();

        return(view);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.insertButton){
            String sNewPlayer = oNameEdit.getText().toString();
            try {
                db.insertPlayer(sNewPlayer);
                updateDisplay();
                oNameEdit.setText("");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    private void updateDisplay(){
        // create a List of Map<String, ?> objects
        ArrayList<HashMapToString> data = db.getPlayers();

        // create the resource, from, and to variables
        int resource = R.layout.listview_item;
        String[] from = {"name", "wins", "losses", "ties"};
        int[] to = {R.id.nameTextView, R.id.winsTextView, R.id.lossesTextView, R.id.tiesTextView};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this.getContext(), data, resource, from, to);
        itemsListView.setAdapter(adapter);

    }


}
