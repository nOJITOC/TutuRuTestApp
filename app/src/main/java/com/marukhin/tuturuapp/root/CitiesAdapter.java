package com.marukhin.tuturuapp.root;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.marukhin.tuturuapp.R;
import com.marukhin.tuturuapp.core.db.models.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends BaseExpandableListAdapter{

    List<City> mCities = new ArrayList<>();

    private Context mContext;
    private MainPresenter.OnStationClickListener mStationClickListener;
    private ExpandableListView.OnChildClickListener mChildClickListener;


    public CitiesAdapter(Context context, MainPresenter.OnStationClickListener listener) {
        mContext = context;
        mStationClickListener = listener;
        mChildClickListener = (parent, v, groupPosition, childPosition, id) -> {
            mStationClickListener.onStationClick(mCities.get(groupPosition).getStations().get(childPosition).getId());
            return true;
        };
    }

    public void setCities(List<City> cities) {
        mCities = cities;
        notifyDataSetChanged();
    }


    //region =========================ExpandableListAdapter==============================
    @Override
    public int getGroupCount() {
        return mCities.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCities.get(groupPosition).getStations().size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return mCities.get(groupPosition).getTitle();
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return mCities.get(groupPosition).getStations().get(childPosition).getStationTitle();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.stations_group_view, null);
        }
        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText(getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.station_child_view, parent, false);
        }
        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(getChild(groupPosition, childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public ExpandableListView.OnChildClickListener getChildClickListener() {
        return mChildClickListener;
    }
    //endregion

}
