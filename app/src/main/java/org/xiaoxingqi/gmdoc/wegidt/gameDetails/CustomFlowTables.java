package org.xiaoxingqi.gmdoc.wegidt.gameDetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AddBean on 2016/6/21.
 * 自动
 */
public class CustomFlowTables extends ViewGroup implements View.OnClickListener {
    public CustomFlowTables(Context context) {
        super(context);
    }

    public CustomFlowTables(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFlowTables(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean s, int l, int t, int r, int b) {
        int x = 0;
        int y = 0;
        int maxH = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (x + child.getMeasuredWidth() >= getMeasuredWidth()) {
                x = 0;
                y = y + maxH;
                maxH = 0;
            }
            child.layout(x, y, x + child.getMeasuredWidth(), y + child.getMeasuredHeight());
            if (child.getMeasuredHeight() > maxH) {
                maxH = child.getMeasuredHeight();
            }
            x = x + child.getMeasuredWidth();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;
        int y = 0;
        int maxH = 0;
        for (int i = 0; i < getChildCount(); i++) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            View child = getChildAt(i);
            if (x + child.getMeasuredWidth() >= getMeasuredWidth()) {
                x = 0;
                y = y + maxH;
                maxH = 0;
            }
            if (child.getMeasuredHeight() > maxH) {
                maxH = child.getMeasuredHeight();
            }
            x = x + child.getMeasuredWidth();
        }
        y = y + maxH;
        setMeasuredDimension(widthMeasureSpec, y);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        child.setOnClickListener(this);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < getChildCount(); i++) {
            if (v == getChildAt(i)) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(v, i);
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public View getSelectedChild() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).isSelected()) {
                return getChildAt(i);
            }
        }
        return null;
    }


    public int getSelectedPosition() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }
}
