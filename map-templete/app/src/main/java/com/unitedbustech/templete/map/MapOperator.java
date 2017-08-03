package com.unitedbustech.templete.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

/**
 * @author yufei0213
 */
public class MapOperator implements OnMapReadyCallback {

    protected GoogleMap map;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        //禁用工具栏，指南针，旋转
        this.map.getUiSettings().setMapToolbarEnabled(false);
        this.map.getUiSettings().setCompassEnabled(false);
        this.map.getUiSettings().setRotateGesturesEnabled(false);
        //缩放九倍，定位到纽约
        this.map.moveCamera(CameraUpdateFactory.zoomTo((float) 9));
        this.map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(40.7154861, -73.9926386)));
    }

    public void zoomIn() {

        if (map == null)
            return;

        map.animateCamera(CameraUpdateFactory.zoomIn());
    }

    public void zoomOut() {

        if (map == null)
            return;

        map.animateCamera(CameraUpdateFactory.zoomOut());
    }

    public void setTrafficMode(boolean status) {

        if (map == null)
            return;

        map.setTrafficEnabled(status);
    }

    public void setHybridMode(boolean status) {

        if (map == null)
            return;

        if (status)
            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        else
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
