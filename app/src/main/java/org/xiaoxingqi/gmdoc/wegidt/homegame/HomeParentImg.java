package org.xiaoxingqi.gmdoc.wegidt.homegame;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.entity.BaseHomeBean;
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout;

/**
 * Created by yzm on 2017/11/2.
 */

public class HomeParentImg extends BaseLayout {

    ImageView mIvTopView;
    TextView mTvTopDesc;
    TextView mTvUserName;

    public HomeParentImg(@NonNull Context context) {
        this(context, null);
    }

    public HomeParentImg(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mIvTopView = findViewById(R.id.iv_TopView);
        mTvTopDesc = findViewById(R.id.tv_TopDesc);
        mTvUserName = findViewById(R.id.tv_UserName);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_article;
    }

    public void setData(BaseHomeBean bean) {
        mTvUserName.setText(bean.getName());
        Glide.with(getContext())
                .asBitmap()
                .load(bean.getCover())
                .into(mIvTopView);
        mTvTopDesc.setText(bean.getTitle());
    }
}
