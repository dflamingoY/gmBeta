package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

/**
 * Created by yzm on 2017/11/18.
 */

public class StokenView extends View {
    private Context mContext;
    private int defaultType = 1;
    private Paint mPaint;

    public StokenView(Context context) {
        this(context, null, 0);
    }

    public StokenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StokenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StokenView);
        defaultType = array.getInt(R.styleable.StokenView_size_Type, 1);
        mContext = context;
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(AppTools.dp2px(mContext, 1));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (defaultType == 1) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) / 2, MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
    }
}
