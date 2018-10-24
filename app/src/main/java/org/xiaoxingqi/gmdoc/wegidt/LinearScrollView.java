package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by yzm on 2018/1/19.
 */

public class LinearScrollView extends HorizontalScrollView {
    public LinearScrollView(Context context) {
        super(context);
    }

    public LinearScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (mOnInterListener != null) {
                    mOnInterListener.intercept();
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mOnInterListener != null) {
                    mOnInterListener.cancle();
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private OnInterListener mOnInterListener;

    public void setOnInterListener(OnInterListener listener) {

        mOnInterListener = listener;
    }

    public interface OnInterListener {

        void intercept();

        void cancle();

    }


}
