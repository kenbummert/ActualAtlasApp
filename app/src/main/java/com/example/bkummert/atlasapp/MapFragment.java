package com.example.bkummert.atlasapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by bkummert on 5/2/18.
 */

public class MapFragment extends Fragment {
    private Map mMap;
    private TextView mTextView;
    private static final String ARG_MAP_ID = "map_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID mapID = (UUID) getActivity().getIntent().getSerializableExtra(AtlasActivity.EXTRA_MAP_ID);
        mMap = AtlasList.get(getActivity()).getMap(mapID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, container, false);
        mTextView = (TextView)v.findViewById(R.id.map_title);
        mTextView.setText(mMap.getTitle());
        return v;
    }

}
