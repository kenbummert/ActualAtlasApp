package com.example.bkummert.atlasapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by bkummert on 5/2/18.
 */

public class MapFragment extends Fragment {
    private Map mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMap = new Map();
    }
}
