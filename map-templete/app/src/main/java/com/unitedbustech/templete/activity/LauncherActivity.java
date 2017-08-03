package com.unitedbustech.templete.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.unitedbustech.templete.R;

/**
 * @author yufei0213
 */
public class LauncherActivity extends BaseActivity {

    private static final long DURATION = 2 * 1000l;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void initVariables() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                Intent intent = MainActivity.newIntent(LauncherActivity.this);
                intent.putExtra(EXTRA_ANIM_IN_IN_ID, R.anim.fade_in);
                intent.putExtra(EXTRA_ANIM_IN_OUT_ID, R.anim.fade_out);
                intent.putExtra(EXTRA_ANIM_OUT_IN_ID, R.anim.fade_in);
                intent.putExtra(EXTRA_ANIM_OUT_OUT_ID, R.anim.fade_out);

                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    protected View onCreateView(Bundle savedInstanceState) {

        View view = LayoutInflater.from(LauncherActivity.this).inflate(R.layout.activity_launcher, null);

        return view;
    }

    @Override
    protected void loadData() {

        handler.postDelayed(runnable, DURATION);
    }

    @Override
    protected void initStatusBar() {

        setStatusBar(R.color.transparent, true);
    }

    @Override
    protected void animateIn() {

        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void animateOut() {

        this.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
