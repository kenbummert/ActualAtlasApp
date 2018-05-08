package com.example.bkummert.atlasapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by bkummert on 5/5/18.
 */

public class AtlasListActivity extends SingleFragmentActivity {

    public static final String EXTRA_MAP_ID = "com.bkummert.android.atlasapp.map_id";

    @Override
    protected Fragment createFragment() {
        return new AtlasListFragment();
    }

    public static Intent newIntent(Context packageContext, UUID mapId) {
        Intent intent = new Intent(packageContext, AtlasActivity.class);
        intent.putExtra(EXTRA_MAP_ID, mapId);
        return intent;
    }
}
