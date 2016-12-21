package com.ciaranbyrne.corkd.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ciaranbyrne.corkd.R;


public class HomeFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    String phoneNo, message;
    EditText etPhone, etMsg;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Button bDial = (Button) rootView.findViewById(R.id.btnCallWineClub);
        Button bText = (Button)rootView.findViewById(R.id.btnTextWineClub);

        bDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callPhone();
            }
        });

        bText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendSMSMessage();
                Toast.makeText(getActivity().getApplicationContext(),"Message sent"+ "\n"+message, Toast.LENGTH_SHORT).show();


            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }


    protected void callPhone() {
        Intent iDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0860726490"));
        startActivity(iDial);
    }

    protected void sendSMSMessage() {
        phoneNo = "0860726490";
        message = "Hi, I am interested in joining the wine club";
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

        }
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