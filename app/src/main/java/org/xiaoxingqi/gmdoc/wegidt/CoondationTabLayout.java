package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

/**
 * Created by yzm on 2018/1/2.
 * 点击反馈 判断是否执行view 更新
 */

public class CoondationTabLayout extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private CharSequence[] mTextArray;
    private boolean isFirst = false;
    private int defalueColor = Color.parseColor("#888888");
    private int selectColor = Color.parseColor("#282828");
    private OnTabClickListener mClickListener;
    private int slidWidth = 80;//dp
    private int DP = 1;
    private int mStartX = 0;
    private int mEndX = 0;
    private int mStartFinalX = 0;
    private int mFinalWidth = 0;
    private SlideAnim mSlideAnim = new SlideAnim();
    private final int TIME = 200;
    private int currentChild = 0;

    public CoondationTabLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public CoondationTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoondationTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseTabTitleLayout);
        mTextArray = typedArray.getTextArray(R.styleable.BaseTabTitleLayout_arrayText);
        defalueColor = typedArray.getColor(R.styleable.BaseTabTitleLayout_title_color, defalueColor);
        selectColor = typedArray.getColor(R.styleable.BaseTabTitleLayout_title_select_color, defalueColor);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        DP = AppTools.getPhoneDensity(mContext);
        this.setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        this.removeAllViews();
        if (mTextArray != null) {
            for (CharSequence text : mTextArray) {
                TextView textView = new TextView(mContext);
                LayoutParams params = new LayoutParams(AppTools.getWindowsWidth(mContext) / mTextArray.length, LayoutParams.MATCH_PARENT);
                textView.setGravity(Gravity.CENTER);
                textView.setText(text);
                textView.setTextColor(defalueColor);
                textView.setOnClickListener(this);
                this.addView(textView, params);
            }
        }
        mSlideAnim.setDuration(TIME);
    }

    /**
     * 代码设置子view
     *
     * @param arrays
     */
    public void setTitleArray(String[] arrays) {
        removeAllViews();
        mTextArray = arrays;
        for (CharSequence text : mTextArray) {
            TextView textView = new TextView(mContext);
            LayoutParams params = new LayoutParams(AppTools.getWindowsWidth(mContext) / mTextArray.length, LayoutParams.MATCH_PARENT);
            textView.setGravity(Gravity.CENTER);
            textView.setText(text);
            textView.setTextColor(defalueColor);
            textView.setOnClickListener(this);
            this.addView(textView, params);
        }
        setCurrentSelect(0);
        isFirst = false;
        invalidate();
    }

    /**
     * 修改textView 显示文本
     *
     * @param arrays
     */
    public void changeText(String[] arrays) {
        if (arrays.length != getChildCount()) {
            return;
        }
        mTextArray = arrays;
        for (int a = 0; a < arrays.length; a++) {
            TextView tvChild = (TextView) getChildAt(a);
            tvChild.setText(arrays[a]);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isFirst && getChildCount() > 0) {
            TextView tv = (TextView) getChildAt(0);
            tv.setTextColor(selectColor);
            //            mFinalWidth = DP * slidWidth;
            mFinalWidth = AppTools.getWindowsWidth(mContext) / mTextArray.length;
            mStartFinalX = AppTools.getWindowsWidth(mContext) / mTextArray.length / 2 - mFinalWidth / 2;
            /*mSlideAnim.setDuration(TIME);
            startAnimation(mSlideAnim);*/
            isFirst = true;
        } else {

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void selectedTitle(View view) {
        clearAll();
        ((TextView) view).setTextColor(selectColor);
        mStartFinalX = (int) view.getX() + AppTools.getWindowsWidth(mContext) / mTextArray.length / 2 - mFinalWidth / 2;
        //        mFinalWidth = view.getMeasuredWidth();
        currentChild = indexOfChild(view);
        mSlideAnim.setDuration(TIME);
        view.startAnimation(mSlideAnim);
    }

    private void clearAll() {
        for (int a = 0; a < getChildCount(); a++) {
            TextView tv = (TextView) getChildAt(a);
            tv.setTextColor(defalueColor);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            if (mClickListener != null) {
                boolean onClick = mClickListener.onClick(v);
                if (onClick) {
                    selectedTitle(v);
                }
            }
        }
    }

    public void setOnClick(OnTabClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface OnTabClickListener {
        boolean onClick(View view);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        onDefaultIndexDraw(canvas, mStartX, getMeasuredHeight() - DP, mFinalWidth + mStartX, getMeasuredHeight() - DP);
    }

    public boolean onDefaultIndexDraw(Canvas canvas, int x1, int y1, int x2, int y2) {
        Paint paint = new Paint();
        paint.setStrokeWidth(AppTools.dp2px(getContext(), 3));
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.color_shallow_yellow));
        canvas.drawLine(x1, y1, x2, y2, paint);
        return true;
    }

    private class SlideAnim extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mStartX = (int) (mStartX + (mStartFinalX - mStartX) * interpolatedTime);
            mEndX = mStartX + mFinalWidth;
            postInvalidate();
        }
    }

    /**
     * 设置当前选中
     *
     * @param position
     */
    public void setCurrentSelect(int position) {
        selectedTitle(getChildAt(position));
    }

    public void setCurrentSelect(int position, boolean isAnim) {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                selectedTitle(getChildAt(position));
            }
        });
    }

    /**
     * 获取当前选中的view位置
     *
     * @return
     */
    public int getSelectChild() {
        return currentChild;
    }
}
