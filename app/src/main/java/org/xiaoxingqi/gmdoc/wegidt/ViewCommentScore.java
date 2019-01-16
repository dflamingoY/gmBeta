package org.xiaoxingqi.gmdoc.wegidt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.xiaoxingqi.gmdoc.tools.AppTools;

/**
 * Created by yzm on 2017/11/8.
 * <p>
 * 总共十块  分三级   填充时有阴影
 */

public class ViewCommentScore extends View {
    private Context mContext;
    private int goodColor = Color.parseColor("#97cc04");
    private int normalColor = Color.parseColor("#ffd116");
    private int badlColor = Color.parseColor("#db504a");
    private int grayColor = Color.parseColor("#cccccc");
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int defaultmargin = 5;
    private GestureDetector mGestureDetector;
    private int current = 0;

    public ViewCommentScore(Context context) {
        this(context, null, 0);
    }

    public ViewCommentScore(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewCommentScore(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {
        defaultmargin = AppTools.dp2px(mContext, 5);
        mPaint = new Paint();
        mPaint.setColor(goodColor);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mGestureDetector = new GestureDetector(mContext, new MyGestureListener());
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = (int) ((w - AppTools.dp2px(mContext, 12 * 5)) * 1f / 11 + 0.5f);
        mHeight = h - defaultmargin * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int a = 1; a < 12; a++) {
            if (a <= current) {
                if (a >= 1 && a <= 5) {
                    mPaint.setShadowLayer(5, 0, 3, badlColor);
                    mPaint.setColor(badlColor);
                } else if (a > 5 && a <= 8) {
                    mPaint.setShadowLayer(5, 0, 3, normalColor);
                    mPaint.setColor(normalColor);
                } else {
                    mPaint.setShadowLayer(5, 0, 3, goodColor);
                    mPaint.setColor(goodColor);
                }
            } else {
                mPaint.setShadowLayer(0, 0, 0, Color.WHITE);
                mPaint.setColor(grayColor);
            }
            canvas.drawRoundRect(new RectF(a * defaultmargin + (a - 1) * mWidth, defaultmargin, a * defaultmargin + mWidth * a, mHeight + defaultmargin), 5, 5, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();
            for (int a = 1; a < 12; a++) {
                RectF rectF = new RectF(a * defaultmargin + (a - 1) * mWidth, defaultmargin, a * defaultmargin + mWidth * a, mHeight);
                if (rectF.contains(x, y)) {
                    current = a;
                    invalidate();
                    if (mListener != null) {
                        mListener.change(a - 1);
                    }
                    break;
                }
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("Mozator",  "${e1.}")

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    public void setScore(int score) {
        current = score;
        invalidate();
    }

    private OnChangeListener mListener;

    public int getScore() {
        return current - 1;
    }

    public void setChangeListener(OnChangeListener listener) {
        mListener = listener;
    }

    public interface OnChangeListener {
        void change(int pisition);
    }
}
