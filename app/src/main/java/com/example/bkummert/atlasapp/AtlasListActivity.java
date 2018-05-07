package com.example.bkummert.atlasapp;

import android.support.v4.app.Fragment;

/**
 * Created by bkummert on 5/5/18.
 */

public class AtlasListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AtlasListFragment();
    }
}
