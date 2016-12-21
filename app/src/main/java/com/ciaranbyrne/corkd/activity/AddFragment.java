package com.ciaranbyrne.corkd.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ciaranbyrne.corkd.R;


public class AddFragment extends Fragment {

    String[] wineTypes = {"Cabernet Sauvignon","Merlot","Shiraz","Pinot Noir","Chardonay","Sauvignon Blanc","Riesling","Muscat"};

    DBHandler db = new DBHandler(getActivity()); //instantiating the database handler

    private EditText etWineName;

    OnWineAddedListener mCallback;


    // Container Activity must implement this interface
    public interface OnWineAddedListener {
        public void onWineAdded(String wine);
    }


    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        final Spinner spinnerWineType = (Spinner)rootView.findViewById(R.id.spinnerWineType);



        //wine spinner
        ArrayAdapter<String> wineAdapterForSpinner = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, wineTypes);

        spinnerWineType.setAdapter((wineAdapterForSpinner));

        spinnerWineType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity().getApplicationContext(),"Spinner day selected: \n"+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etWineName = (EditText) rootView.findViewById(R.id.etWineName);


        //add wine
        Button btnAddWine = (Button) rootView.findViewById(R.id.btnAddWine);
        btnAddWine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wine = etWineName.getText().toString();
                String type = spinnerWineType.getSelectedItem().toString();
                db.addWine(new Wine(0, wine, type)); //adding wine
            }
        });



        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        // This makes sure that the container activity has implemented
        // code from - https://developer.android.com/training/basics/fragments/communicating.html
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnWineAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnWineaAddedListener");
        }
    }

    private void sendData(){
        String wineName = etWineName.getText().toString();

        OnWineAddedListener wineAddedListener = (OnWineAddedListener) getActivity();
        wineAddedListener.onWineAdded(wineName);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}