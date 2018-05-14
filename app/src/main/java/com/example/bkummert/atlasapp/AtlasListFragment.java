package com.example.bkummert.atlasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkummert on 5/5/18.
 */

public class AtlasListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MapAdapter mMapAdapter;
    private List<Map> mMaps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atlas_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.atlas_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMaps = new ArrayList<Map>();
        mMaps.add(new Map("United States"));
        mMaps.add(new Map("South America"));
        mMaps.add(new Map("Europe"));
        mMaps.add(new Map("Africa"));
        mMaps.add(new Map("Asia"));
        setHasOptionsMenu(true);
        updateUI();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.atlas_list_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                query = query.toLowerCase();

                final List<Map> filteredModelList = new ArrayList<>();
                for (Map model : mMaps) {
                    final String text = model.getTitle().toLowerCase();
                    if (text.contains(query)) {
                        filteredModelList.add(model);
                    }
                }
                mMapAdapter.animateTo(filteredModelList);
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });
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



    private class MapAdapter extends RecyclerView.Adapter<MapHolder>  {
        private List<Map> mMapList;
        private List<Map> mapHolder;

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

        public Map removeItem(int position) {
            final Map model = mMapList.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, Map map) {
            mMapList.add(position, map);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final Map model = mMapList.remove(fromPosition);
            mMapList.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        public void animateTo(List<Map> maps) {
            applyAndAnimateRemovals(maps);
            applyAndAnimateAdditions(maps);
            applyAndAnimateMovedItems(maps);
        }

        private void applyAndAnimateRemovals(List<Map> newMaps) {
            for (int i = mMapList.size() - 1; i >= 0; i--) {
                final Map map = mMapList.get(i);
                if (!newMaps.contains(map)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<Map> newMaps) {
            for (int i = 0, count = newMaps.size(); i < count; i++) {
                final Map map = newMaps.get(i);
                if (!mMapList.contains(map)) {
                    addItem(i, map);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<Map> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final Map model = newModels.get(toPosition);
                final int fromPosition = mMapList.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

    }
}
