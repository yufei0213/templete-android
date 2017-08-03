package com.unitedbustech.templete.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.unitedbustech.templete.R;
import com.unitedbustech.templete.map.OverViewMapOperator;

/**
 * @author yufei0213
 */
public class FirstFragment extends BaseFragment {

    private OverViewMapOperator mapOperator;

    public static FirstFragment newInstance() {

        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    protected void initVariables() {

        mapOperator = new OverViewMapOperator();
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.map_container, mapFragment).commit();
        mapFragment.getMapAsync(mapOperator);

        return view;
    }

    @Override
    protected void loadData() {

    }
}
