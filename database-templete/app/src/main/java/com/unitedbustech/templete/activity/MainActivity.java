package com.unitedbustech.templete.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;

import com.unitedbustech.templete.R;
import com.unitedbustech.templete.fragment.ThirdFragment;
import com.unitedbustech.templete.fragment.ForthFragment;
import com.unitedbustech.templete.fragment.FirstFragment;
import com.unitedbustech.templete.fragment.SecondFragment;
import com.unitedbustech.templete.view.TabMenu;

/**
 * @author yufei0213
 */
public class MainActivity extends BaseActivity implements TabMenu.TabMenuSelectedListener {

    private Fragment overViewFragment;
    private Fragment scheduleFragment;
    private Fragment alertsFragment;
    private Fragment moreFragment;

    public static Intent newIntent(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void initVariables() {

        overViewFragment = FirstFragment.newInstance();
        scheduleFragment = SecondFragment.newInstance();
        alertsFragment = ThirdFragment.newInstance();
        moreFragment = ForthFragment.newInstance();
    }

    @Override
    protected View onCreateView(Bundle savedInstanceState) {

        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);

        TabMenu tabMenu = (TabMenu) view.findViewById(R.id.tab_menu);
        tabMenu.setListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.add(R.id.fragment_container, overViewFragment)
                .add(R.id.fragment_container, scheduleFragment)
                .add(R.id.fragment_container, alertsFragment)
                .add(R.id.fragment_container, moreFragment)
                .commit();

        tabMenu.setSelectedItem(TabMenu.FIRST);

        return view;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initStatusBar() {

        setStatusBar(R.color.transparent, true);
    }

    @Override
    public void onTabItemSelected(int index) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (index) {

            case TabMenu.FIRST:

                hideAllViews(ft);

                ft.show(overViewFragment);
                ft.commit();
                break;
            case TabMenu.SECOND:

                hideAllViews(ft);

                ft.show(scheduleFragment);
                ft.commit();
                break;
            case TabMenu.THIRD:

                hideAllViews(ft);

                ft.show(alertsFragment);
                ft.commit();
                break;
            case TabMenu.FORTH:

                hideAllViews(ft);

                ft.show(moreFragment);
                ft.commit();
                break;
            default:
                break;
        }
    }

    private void hideAllViews(FragmentTransaction ft) {

        ft.hide(overViewFragment);
        ft.hide(scheduleFragment);
        ft.hide(alertsFragment);
        ft.hide(moreFragment);
    }
}
