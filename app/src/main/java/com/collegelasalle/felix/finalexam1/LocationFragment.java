package com.collegelasalle.felix.finalexam1;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    private FusedLocationProviderClient mFusedLocationClient;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findCountry();
            }
        });

        return rootView;
    }


    private void findCountry() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.

                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

                        TextView tvLatitude = getActivity().findViewById(R.id.textView1);
                        TextView tvLongitude = getActivity().findViewById(R.id.textView2);

                        TextView locationResult = getActivity().findViewById(R.id.locationResult);

                            try {
                                List<Address> addresses = geocoder.getFromLocation(Double.valueOf(tvLatitude.getText().toString()),Double.valueOf(tvLongitude.getText().toString()), 5);
                                if (addresses.size() > 0) {

                                    locationResult.setText(addresses.get(0).getCountryName());

                                }
                            } catch (Exception e) {
                                locationResult.setText("Error");

                            }


                    }
                });
    }

}
