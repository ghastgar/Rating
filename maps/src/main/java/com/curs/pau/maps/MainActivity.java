package com.curs.pau.maps;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpMapIfNeeded();

        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_HYBRID)
                .compassEnabled(false)
                .rotateGesturesEnabled(true)
                //.camera(new CameraPosition(new LatLng(41.38, 2.17), 20, 1.0F, 1.0F))  // en realitat no cal aixo
                .describeContents();
        MapFragment.newInstance(options);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.38, 2.17), 14.0f));

        MarkerOptions mrkerOptions = new MarkerOptions();
        mrkerOptions.position(new LatLng(41.38, 2.17)).draggable(true).describeContents();
        mMap.addMarker(mrkerOptions);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).draggable(true).title("click"));
            }
        });

    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        }
    }

}
