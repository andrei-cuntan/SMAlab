package com.example.lab12;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.lab12.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final int REQ_PERMISSION = 5;
    private boolean checkPermission(){
        return(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }
    private void askPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQ_PERMISSION);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQ_PERMISSION:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(checkPermission()){
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else{

                }
                break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void drawPolyLineOnMap(List<LatLng> list, GoogleMap googleMap){
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(10);
        polylineOptions.addAll(list);
        googleMap.addPolyline(polylineOptions);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : list){
            builder.include(latLng);
        }
        builder.build();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (checkPermission()){
            mMap.setMyLocationEnabled(true);
        }
        else{
            askPermission();
        }
        // Add a marker in Sydney and move the camera
        LatLng notsydney = new LatLng(45.7450284, 21.2275766);
        List<LatLng> lst = new ArrayList<>();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions().position(notsydney).title("Marker in NotSydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(notsydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker){
                if (marker.getPosition().equals(notsydney)){
                    Toast.makeText(MapsActivity.this,"Test1234", Toast.LENGTH_LONG).show();
                }
                else{

                }
                return false;
            }
        });
        drawPolyLineOnMap(lst, mMap);
    }
}