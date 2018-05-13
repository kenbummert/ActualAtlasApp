package com.example.bkummert.atlasapp;

import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by bkummert on 5/2/18.
 */

public class MapFragment extends Fragment {
    private Map mMap;
    private TextView mTextView;
    private ImageView mImageView;
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
        mImageView = (ImageView)v.findViewById(R.id.map_photo);
        TypedArray imageArray = getResources().obtainTypedArray(R.array.map_images);
        int pos = 0;
        if(mMap.getTitle().equals("United States"))
        {
            pos = 0;
        }
        if(mMap.getTitle().equals("South America"))
        {
            pos = 1;
        }
        if(mMap.getTitle().equals("Europe"))
        {
            pos = 2;
        }
        if(mMap.getTitle().equals("Africa"))
        {
            pos = 3;
        }
        if(mMap.getTitle().equals("Asia"))
        {
            pos = 4;
        }
        mImageView.setImageResource(imageArray.getResourceId(pos,0));

        imageArray.recycle();
        return v;

    }

}
