package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xiaoxingqi.gmdoc.R;


/**
 * Created by DoctorKevin on 2017/7/22.
 */

public class BaseToolbarLayout extends Toolbar {

    private int mResourceId;

    public BaseToolbarLayout(Context context) {
        this(context, null, 0);
    }

    public BaseToolbarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseToolbarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseToolbarLayout);
        mResourceId = array.getResourceId(R.styleable.BaseToolbarLayout_contentLayout, -1);
        array.recycle();
        initView(context);
    }

    private void initView(Context context) {
        if (mResourceId != -1) {
            View view = LayoutInflater.from(context).inflate(mResourceId, null);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(view, lp);
        }
    }


}
