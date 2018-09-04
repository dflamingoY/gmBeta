package org.xiaoxingqi.gmdoc.wegidt;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xiaoxingqi.gmdoc.R;

/**
 * Created by DoctorKevin on 2017/5/8.
 */

public class CustomSelecor extends LinearLayout {
    private Context mContext;
    private LayoutInflater mLayoutInfalt;
    private ImageView mTabImage;
    private TextView mTabName;
    private ImageView mTabDot;
    private OnButtonClickListener mButtonClickListener;
    private int normalId;
    private String name;
    private boolean isFlag;
    private int selectedId;
    private int selectedColor = R.color.color_666666;
    private int normalTextColor = R.color.color_666666;
    private AnimatorSet mSet;

    public CustomSelecor(Context context) {
        this(context, null, 0);
    }

    public CustomSelecor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSelecor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (null != mButtonClickListener) {
                mButtonClickListener.onClick();
            }
        }
    };

    /**
     * 初始化View
     */
    private void initView() {
        mLayoutInfalt = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInfalt.inflate(R.layout.tab_selector_layout, null);
        this.addView(view);
        this.setOnClickListener(mOnClickListener);
        mTabImage = (ImageView) view.findViewById(R.id.tab_image);
        mTabName = (TextView) view.findViewById(R.id.tab_name);
        mTabDot = (ImageView) view.findViewById(R.id.tab_dot);
    }

    /**
     * 设置默认的视图
     */
    public void setDefaultView(int resId, String name, boolean isFlag) {
        this.normalId = resId;
        this.name = name;
        this.isFlag = isFlag;
        setTabName(name);
        setTabImage(resId);
        setNameColor(normalTextColor);
        setButtonFlag(isFlag);
    }


    /**
     * 设置选中的视图
     */
    public void setSelectView(int resId, int colorId) {
        this.selectedId = resId;
        selectedColor = colorId;
    }

    /**
     * 设置文字的颜色
     *
     * @param colorId
     */
    public void setNameColor(int colorId) {
        mTabName.setTextColor(getResources().getColor(colorId));
    }

    /**
     * 设置按钮图片
     *
     * @param resId
     */
    private void setTabImage(int resId) {
        mTabImage.setImageResource(resId);
    }

    /**
     * 设置按钮文字
     *
     * @param name
     */
    private void setTabName(String name) {
        mTabName.setText(name);
    }

    /**
     * 回复默认设置
     */
    public void clearSelect() {
        setTabImage(normalId);
        setNameColor(normalTextColor);
    }

    /**
     * 设置选中状态
     */
    public void setSelected() {
        setNameColor(selectedColor);
        setTabImage(selectedId);
        //执行动画
        if (mSet != null) {
            if (mSet.isRunning()) {
                mSet.cancel();
            }
        } else {
            mSet = new AnimatorSet();
        }
        mSet.playTogether(ObjectAnimator.ofFloat(mTabImage, "ScaleX", 1f, 0.8f, 1f),
                ObjectAnimator.ofFloat(mTabImage, "ScaleY", 1f, 0.8f, 1f));
        mSet.setInterpolator(new BounceInterpolator());
        mSet.setDuration(520);
        mSet.start();
    }

    public void setButtonFlag(boolean isFlag) {
        if (isFlag) {
            mTabDot.setVisibility(VISIBLE);
        } else {
            mTabDot.setVisibility(GONE);
        }
    }

    /**
     * 监听点击回调；
     */
    public interface OnButtonClickListener {
        public void onClick();
    }

    public void setOnButtonClickListener(OnButtonClickListener buttonClickListener) {
        mButtonClickListener = buttonClickListener;
    }
}
