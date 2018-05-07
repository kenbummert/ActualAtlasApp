package com.example.bkummert.atlasapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by bkummert on 5/3/18.
 */

public class AtlasList {
    private static AtlasList sAtlasList;
    private List<Map> mMapList;

    public static AtlasList get(Context context) {
        if (sAtlasList == null) {
            sAtlasList = new AtlasList(context);
        }
        return sAtlasList;
    }

    private AtlasList(Context context) {
        mMapList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Map map = new Map();
            map.setTitle("Map " + i);
            mMapList.add(map);
        }
    }

    public List<Map> getMapList() {
        return mMapList;
    }

    public Map getMap(UUID id) {
        for (Map map : mMapList) {
            if (map.getUUID().equals(id)) {
                return map;
            }
        }
        return null;
    }
}
