package com.ciaranbyrne.corkd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ciaranbyrne.corkd.R;


public class WinesFragment extends Fragment {

    String[] wines = {"Blossom Hill", "Paul Masson","Grape","Yellow Tail"};


    public WinesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wines, container, false);


        final ListView wineList = (ListView)rootView.findViewById(R.id.listWines);

        /*
        NOT working
        ArrayAdapter<String> wineAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.list_content, (List<String>) wineList);
         */

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}