package com.example.bkummert.atlasapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class AtlasActivity extends SingleFragmentActivity {

    public static final String EXTRA_MAP_ID = "com.bkummert.android.atlasapp.map_id";
    @Override
    protected Fragment createFragment() {
        return new MapFragment();
    }

    public static Intent newIntent(Context packageContext, UUID mapId) {
        Intent intent = new Intent(packageContext, AtlasActivity.class);
        intent.putExtra(EXTRA_MAP_ID, mapId);
        return intent;
    }
}

