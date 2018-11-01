package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import org.xiaoxingqi.gmdoc.R;

/**
 * Created by yzm on 2017/11/3.
 * 最多显示两行
 */

public class ExpendTextView extends BaseLayout {
    private TextView mTvShowText;
    private TextView mTvExpend;
    private boolean isCollsped = true;

    public ExpendTextView(Context context) {
        this(context, null);
    }

    public ExpendTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mTvShowText = findViewById(R.id.tv_ShowText);
        mTvExpend = findViewById(R.id.tv_Expend);
        mTvExpend.setOnClickListener(v -> {
            if (isCollsped) {//折叠状态
                mTvShowText.setMaxLines(Integer.MAX_VALUE);
                mTvExpend.setText("收起");
                isCollsped = false;
            } else {
                mTvShowText.setMaxLines(2);
                isCollsped = true;
                mTvExpend.setText("展开全部");
            }
            mTvShowText.requestLayout();
            if (mListener != null) {
                mListener.expend(isCollsped);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_expend_textview;
    }

    public void setTvShowText(String text) {
        mTvShowText.setText(text);
        mTvExpend.setMaxLines(2);
    }

    private OnExpendListener mListener;

    public void stOnExpendListener(OnExpendListener listener) {
        mListener = listener;
    }

    public interface OnExpendListener {
        void expend(boolean isExpend);
    }

}
