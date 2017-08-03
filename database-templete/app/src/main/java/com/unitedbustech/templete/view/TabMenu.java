package com.unitedbustech.templete.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.unitedbustech.templete.R;

/**
 * @author yufei0213
 */
public class TabMenu extends LinearLayout implements View.OnClickListener {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FORTH = 3;

    private TabMenuItem firstItem;
    private TabMenuItem secondItem;
    private TabMenuItem thirdItem;
    private TabMenuItem forthItem;

    private TabMenuSelectedListener listener;

    public TabMenu(Context context) {

        this(context, null);
    }

    public TabMenu(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.tab_menu, this, true);

        firstItem = (TabMenuItem) this.findViewById(R.id.first_item);
        secondItem = (TabMenuItem) this.findViewById(R.id.second_item);
        thirdItem = (TabMenuItem) this.findViewById(R.id.third_item);
        forthItem = (TabMenuItem) this.findViewById(R.id.forth_item);

        firstItem.setOnClickListener(this);
        secondItem.setOnClickListener(this);
        thirdItem.setOnClickListener(this);
        forthItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.first_item:

                resetSelected();
                firstItem.setSelected();

                if (listener != null)
                    listener.onTabItemSelected(FIRST);
                break;
            case R.id.second_item:

                resetSelected();
                secondItem.setSelected();

                if (listener != null)
                    listener.onTabItemSelected(SECOND);
                break;
            case R.id.third_item:

                resetSelected();
                thirdItem.setSelected();

                if (listener != null)
                    listener.onTabItemSelected(THIRD);
                break;
            case R.id.forth_item:

                resetSelected();
                forthItem.setSelected();

                if (listener != null)
                    listener.onTabItemSelected(FORTH);
                break;
            default:
                break;
        }
    }

    public void setListener(TabMenuSelectedListener listener) {

        this.listener = listener;
    }

    public void setSelectedItem(int index) {

        switch (index) {

            case FIRST:

                firstItem.performClick();
                break;
            case SECOND:

                secondItem.performClick();
                break;
            case THIRD:

                thirdItem.performClick();
                break;
            case FORTH:

                forthItem.performClick();
                break;
            default:
                break;
        }
    }

    private void resetSelected() {

        firstItem.cancelSelected();
        secondItem.cancelSelected();
        thirdItem.cancelSelected();
        forthItem.cancelSelected();
    }

    public interface TabMenuSelectedListener {

        void onTabItemSelected(int index);
    }
}
