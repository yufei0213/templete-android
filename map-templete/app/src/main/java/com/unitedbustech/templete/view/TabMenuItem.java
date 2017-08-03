package com.unitedbustech.templete.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unitedbustech.templete.R;

/**
 * @author yufei0213
 */
public class TabMenuItem extends LinearLayout {

    private TextView itemView;
    private TextView itemNumView;

    public TabMenuItem(Context context) {

        this(context, null);
    }

    public TabMenuItem(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.tab_menu_item, this, true);

        itemView = (TextView) this.findViewById(R.id.tab_menu_text);
        itemNumView = (TextView) this.findViewById(R.id.tab_menu_num);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tab_menu_item);

        String itemText = typedArray.getString(R.styleable.tab_menu_item_itemText);
        int itemTextColor = typedArray.getResourceId(R.styleable.tab_menu_item_itemTextColor, 0);
        int itemIcon = typedArray.getResourceId(R.styleable.tab_menu_item_itemIcon, 0);

        itemView.setCompoundDrawablesWithIntrinsicBounds(null, context.getResources().getDrawable(itemIcon), null, null);
        itemView.setTextColor(context.getResources().getColorStateList(itemTextColor));
        itemView.setText(itemText);

        typedArray.recycle();
    }

    public void setSelected() {

        itemView.setSelected(true);
    }

    public void cancelSelected() {

        itemView.setSelected(false);
    }

    public void showNum(String num) {

        itemNumView.setVisibility(VISIBLE);
        itemNumView.setText(num);
    }

    public void hideNum() {

        itemNumView.setVisibility(GONE);
    }
}
