package com.example.bkummert.atlasapp;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

/**
 * Created by bkummert and VIKRAMKHER on 5/2/18.
 */

public class Map {
    private Image mImage;
    private UUID mUUID;
    private String mTitle;

    public Map() {
        mTitle = "map title";
        mUUID = UUID.randomUUID();

    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }
}
