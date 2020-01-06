package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xiaoxingqi.gmdoc.R;

public class ViewMsgShortType extends BaseLayout {
    private Context mContext;
    private TextView mTvHintCount;
    private Drawable mDrawable;
    private String mName;
    private View mViewNewType;//新增功能的提醒

    public ViewMsgShortType(@NonNull Context context) {
        this(context, null, 0);
    }

    public ViewMsgShortType(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewMsgShortType(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewMoreGroupView);
        mDrawable = array.getDrawable(R.styleable.ViewMoreGroupView_title_img);
        mName = array.getString(R.styleable.ViewMoreGroupView_title_name);
        array.recycle();
        mContext = context;
        initView();
    }

    private void initView() {
        ImageView ivIcon = findViewById(R.id.iv_Icon);
        TextView tvItemName = findViewById(R.id.tv_ItemName);
        mViewNewType = findViewById(R.id.iv_NewType);
        if (!TextUtils.isEmpty(mName)) {
            tvItemName.setText(mName);
        }
        if (mDrawable != null) {
            ivIcon.setImageDrawable(mDrawable);
        }
        mTvHintCount = findViewById(R.id.tv_HintMsg);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_msg_short_type;
    }

    /**
     * 设置提醒是否显示
     *
     * @param count
     */
    public void setFlag(int count) {
        mTvHintCount.setVisibility(count > 0 ? VISIBLE : GONE);
        mTvHintCount.setText("" + count);
    }

    public void setNewTag(int flag) {
        mViewNewType.setVisibility(flag > 0 ? VISIBLE : GONE);
    }
}
