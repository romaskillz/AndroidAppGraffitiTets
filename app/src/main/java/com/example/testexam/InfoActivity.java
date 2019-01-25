package com.example.testexam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.example.testexam.Fragments.InfoFragment;
import com.example.testexam.Fragments.MapFragment;

import butterknife.BindView;

;

public class InfoActivity extends FragmentActivity {
    @BindView(R.id.cont)
    LinearLayout layout;

    private InfoFragment infoFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        String nme = getIntent().getStringExtra("name");
        String img = getIntent().getStringExtra("image");
        String auth = getIntent().getStringExtra("author");
        String descrip = getIntent().getStringExtra("name");
        String addr = getIntent().getStringExtra("address");
        final Double lat = (Double) getIntent().getDoubleExtra("lat", 43.9883632);
        final Double lng = (Double) getIntent().getDoubleExtra("lng", 56.3142945);

        Bundle bInfo = new Bundle();
        bInfo.putString("name", nme);
        bInfo.putString("image", img);
        bInfo.putString("author", auth);
        bInfo.putString("description", descrip);
        bInfo.putString("address", addr);
        infoFragment = InfoFragment.newInstance(bInfo);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.cont, infoFragment);
        transaction.commit();

        Bundle bLocation = new Bundle();
        bLocation.putDouble("lat", lat);
        bLocation.putDouble("lng", lng);
        mapFragment = MapFragment.newInstance(bLocation);

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.add(R.id.cont, mapFragment);
        transaction2.commit();
    }
}