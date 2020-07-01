package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import org.xiaoxingqi.gmdoc.R;

/**
 * Created by yzm on 2018/1/4.
 */

public class PerCentImgView extends AppCompatImageView {

    private float percent = -1;

    public PerCentImgView(Context context) {
        this(context, null);
    }

    public PerCentImgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PerCentImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PerCentImgView);
        percent = array.getFloat(R.styleable.PerCentImgView_percentage, -1);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (percent == -1)
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        else {
            int size = View.MeasureSpec.getSize(widthMeasureSpec);
            setMeasuredDimension(size, (int) (size / percent + 0.5f));
        }
    }
}
