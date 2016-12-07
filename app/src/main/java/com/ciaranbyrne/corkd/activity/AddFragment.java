package com.ciaranbyrne.corkd.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ciaranbyrne.corkd.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;


public class AddFragment extends Fragment {
    List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);


    //request codes and thumbnail instantiated
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int MY_PERMISSIONS_REQUEST_CAMERA = 2;
    private ImageView ivThummbnail;
    private Context context;

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

        super.onCreate(savedInstanceState);


        //Button for cam - had to change for on create view

        Button bCam = (Button) getView().findViewById(R.id.btnCam); //btn that will activate cam
        ivThummbnail = (ImageView) getView().findViewById(R.id.ivAddWine); //declare thumbnail to show taken photo
        bCam.setOnClickListener(new View.OnClickListener() { //set listener for btn press
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });






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

//Camera stuff.....

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //requestCode, which request we're responding to, resultCode,
        // all is well, data, the image in this case
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {//if correct request and all is well
            Bundle extras = data.getExtras(); //get the image
            Bitmap imageBitmap = (Bitmap) extras.get("data"); //get the image
            ivThummbnail.setImageBitmap(imageBitmap); //set image to thumbnail
        }
    }
    private void cameraIntentCode() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //intent to take the picture
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) { //resolveActivity returns first activity component that can handle the intent
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); //should start only if there's an app that can handle the intent (has a camera)
        } //next up to onActivityResult
    }

    private void takePicture() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { //if all's not good
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA); //do this
        } else {
            cameraIntentCode(); //otherwise go on to this method (1 above)
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    cameraIntentCode();
                } else {
                    // permission deniedDisable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

}