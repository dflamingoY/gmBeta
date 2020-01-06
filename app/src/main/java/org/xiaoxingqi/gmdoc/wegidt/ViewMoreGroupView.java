package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import org.xiaoxingqi.gmdoc.R;

/**
 * Created by yzm on 2017/11/17.
 */

public class ViewMoreGroupView extends BaseLayout {
    private Context mContext;
    private Drawable mDrawable;
    private String mTitleName;
    private TextView mTvMsgCount;

    public ViewMoreGroupView(@NonNull Context context) {
        this(context, null, 0);
    }

    public ViewMoreGroupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewMoreGroupView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewMoreGroupView);
        mDrawable = array.getDrawable(R.styleable.ViewMoreGroupView_title_img);
        mTitleName = array.getString(R.styleable.ViewMoreGroupView_title_name);
        array.recycle();
        initView();
    }

    private void initView() {
        mTvMsgCount = findViewById(R.id.tv_MsgCount);
        TextView tvTitleName = findViewById(R.id.tv_TitleName);
        ImageView ivTitleSrc = findViewById(R.id.iv_titleSrc);
        if (mDrawable != null)
            ivTitleSrc.setImageDrawable(mDrawable);
        if (!TextUtils.isEmpty(mTitleName)) {
            tvTitleName.setText(mTitleName);
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_more_group_view;
    }

    public void setMsgCount(String count) {
        if (TextUtils.isEmpty(count)) {
            mTvMsgCount.setText("0");
        } else
            mTvMsgCount.setText(count);
    }
}
