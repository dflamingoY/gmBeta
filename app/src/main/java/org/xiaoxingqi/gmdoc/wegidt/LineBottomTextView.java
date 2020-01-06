package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

/**
 * Created by yzm on 2017/11/16.
 */

public class LineBottomTextView extends AppCompatTextView {
    private Context mContext;
    private Paint mPaint;

    public LineBottomTextView(Context context) {
        super(context, null, 0);
    }

    public LineBottomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineBottomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(mContext.getResources().getColor(R.color.color_shallow_yellow));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, getMeasuredHeight() - AppTools.dp2px(mContext, 2), getMeasuredWidth(), getMeasuredHeight(), mPaint);
    }
}
