package com.unitedbustech.templete.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.unitedbustech.templete.R;
import com.unitedbustech.templete.util.ScreenUtil;

/**
 * @author yufei0213
 */
public class ForthFragment extends BaseFragment {

    public static ForthFragment newInstance() {

        ForthFragment fragment = new ForthFragment();
        return fragment;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forth, container, false);

        fillStatusBar(view);

        return view;
    }

    @Override
    protected void loadData() {

    }

    private void fillStatusBar(View view) {

        View statusBar = view.findViewById(R.id.status_bar);
        int height = ScreenUtil.getStatusBarHeight(getActivity());
        LinearLayout.LayoutParams statusBarParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        statusBar.setLayoutParams(statusBarParams);
        statusBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }
}
