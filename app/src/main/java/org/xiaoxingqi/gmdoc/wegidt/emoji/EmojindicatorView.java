package org.xiaoxingqi.gmdoc.wegidt.emoji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

/**
 * Created by yzm on 2017/12/28.
 */

public class EmojindicatorView extends View {
    private Context mContext;
    private int allCount;
    private int current;
    private Paint mPaint;
    private int radius = 8;//角标的宽度
    private int slideHeight;
    private int mWidth;
    private int mHeight;

    public EmojindicatorView(Context context) {
        this(context, null);
    }

    public EmojindicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        radius = AppTools.dp2px(mContext, 8);
        slideHeight = AppTools.dp2px(mContext, 2);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int slideLen = allCount * radius + (allCount - 1) * radius;
        int start = (mWidth - slideLen) / 2;
        for (int a = 0; a < allCount; a++) {
            if (a + 1 == current) {
                mPaint.setColor(getResources().getColor(R.color.color_shallow_yellow));
            } else {
                mPaint.setColor(getResources().getColor(R.color.color_text_color));
            }
            canvas.drawRoundRect(new RectF(start + a * radius + a * radius, mHeight / 2 - slideHeight / 2, start + a * radius + a * radius + radius, mHeight / 2 + slideHeight / 2), slideHeight / 2, slideHeight / 2, mPaint);
        }
    }

    public void setCurrent(int count, int current) {
        allCount = count;
        this.current = current;
        invalidate();
    }
}
