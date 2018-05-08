package com.example.bkummert.atlasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by bkummert on 5/5/18.
 */

public class AtlasListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MapAdapter mMapAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atlas_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.atlas_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        AtlasList atlasList = AtlasList.get(getActivity());
        List<Map> maps = atlasList.getMapList();

        mMapAdapter = new MapAdapter(maps);
        mRecyclerView.setAdapter(mMapAdapter);
    }

    private class MapHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Map mMap;

        public MapHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_maps, parent, false));
            mTitleTextView = (TextView) itemView.findViewById(R.id.map_title);
            itemView.setOnClickListener(this);

        }
        public void bind(Map map) {
            mMap = map;
            mTitleTextView.setText(mMap.getTitle());
        }

        @Override
        public void onClick(View view) {
            Intent intent = AtlasActivity.newIntent(getActivity(), mMap.getUUID());
            startActivity(intent);
        }
    }

    private class MapAdapter extends RecyclerView.Adapter<MapHolder> {
        private List<Map> mMapList;

        public MapAdapter(List<Map> maps) {
            mMapList = maps;
        }

        @Override
        public MapHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new MapHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MapHolder holder, int position) {
            Map map = mMapList.get(position);
            holder.bind(map);
        }

        @Override
        public int getItemCount() {
            return mMapList.size();
        }

    }
}
