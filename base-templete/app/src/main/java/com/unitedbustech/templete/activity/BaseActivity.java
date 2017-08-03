package com.unitedbustech.templete.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.unitedbustech.templete.R;
import com.unitedbustech.templete.view.ContentView;

/**
 * @author yufei0213
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final String EXTRA_ANIM_IN_IN_ID = "anim_in_in_id";
    protected static final String EXTRA_ANIM_IN_OUT_ID = "anim_in_out_id";
    protected static final String EXTRA_ANIM_OUT_IN_ID = "anim_out_in_id";
    protected static final String EXTRA_ANIM_OUT_OUT_ID = "anim_out_out_id";

    private int animInInId;
    private int animInOutId;
    private int animOutInId;
    private int animOutOutId;

    private ContentView contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initAnimate();

        animateIn();

        initVariables();

        initViews(savedInstanceState);

        loadData();

        ActivityStack.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        animateOut();

        ActivityStack.getInstance().removeActivity(this);
    }

    protected void animateIn() {

        this.overridePendingTransition(animInInId, animInOutId);
    }

    protected void animateOut() {

        this.overridePendingTransition(animOutInId, animOutOutId);
    }

    protected void initStatusBar() {

    }

    protected void setStatusBar(int colorId, boolean isFullScreen) {

        contentView.setStatusBarColor(this, colorId, isFullScreen);
    }

    protected abstract void initVariables();

    protected abstract View onCreateView(Bundle savedInstanceState);

    protected abstract void loadData();

    private void initAnimate() {

        animInInId = getIntent().getIntExtra(EXTRA_ANIM_IN_IN_ID, R.anim.slide_right_in);
        animInOutId = getIntent().getIntExtra(EXTRA_ANIM_IN_OUT_ID, R.anim.slide_left_out);
        animOutInId = getIntent().getIntExtra(EXTRA_ANIM_OUT_IN_ID, R.anim.slide_left_in);
        animOutOutId = getIntent().getIntExtra(EXTRA_ANIM_OUT_OUT_ID, R.anim.slide_right_out);
    }

    private void initViews(Bundle savedInstanceState) {

        contentView = new ContentView(this);
        View view = onCreateView(savedInstanceState);
        contentView.setContentView(view);
        setContentView(contentView);

        initStatusBar();
    }
}
