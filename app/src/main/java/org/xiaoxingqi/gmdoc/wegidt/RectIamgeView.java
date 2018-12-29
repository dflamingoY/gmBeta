package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by yzm on 2017/11/27.
 */

public class RectIamgeView extends AppCompatImageView {
    public RectIamgeView(Context context) {
        super(context);
    }

    public RectIamgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectIamgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int size1 = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(size, size1);
        super.onMeasure(min, min);
    }
}
