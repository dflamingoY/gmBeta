package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by DoctorKevin on 2017/5/8.
 */

public abstract class BaseLayout extends FrameLayout {
    public int DP;
    private Context mContext;
    private LayoutInflater mInflater;
    private View mLayout;

    public BaseLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public BaseLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initLayout();
    }

    private void initLayout() {
        DP = (int) getContext().getResources().getDisplayMetrics().density;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = mInflater.inflate(getLayoutId(), this, false);
        this.addView(mLayout);
    }

    public Context getmContext() {
        return mContext;
    }

    public abstract int getLayoutId();

}
