package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import org.xiaoxingqi.gmdoc.tools.AppTools;


/**
 * Created by yzm on 2017/11/1.
 */

public class GradientRelative extends RelativeLayout {
    private Context mContext;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Region mRegion;

    public GradientRelative(Context context) {
        this(context, null, 0);
    }

    public GradientRelative(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        setWillNotDraw(false);
        mPaint = new Paint();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Rect rectF = new Rect(0, 0, mWidth, mHeight);
        /**
         * 贝塞尔曲线绘制的外层线
         */
        int d = AppTools.dp2px(mContext, 4);
        Path path = new Path();
        path.moveTo(mWidth / 2, 0);
        path.lineTo(mWidth - d, 0);

        path.quadTo(mWidth, 0, mWidth, d);

        path.lineTo(mWidth, mHeight - d);

        path.quadTo(mWidth, mHeight, mWidth - d, mHeight);

        path.lineTo(d, mHeight);

        path.quadTo(0, mHeight, 0, mHeight - d);

        path.lineTo(0, d);

        path.quadTo(0, 0, d, 0);

        path.close();
        mRegion = new Region();
        mRegion.setPath(path, new Region(rectF));
        Rect rect = new Rect(AppTools.dp2px(mContext, 4), AppTools.dp2px(mContext, 4), mWidth - AppTools.dp2px(mContext, 4), mHeight - AppTools.dp2px(mContext, 4));
        Region wrap = new Region();
        wrap.set(rect);
        mRegion.op(wrap, Region.Op.DIFFERENCE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelected()) {
            mPaint.setColor(Color.parseColor("#33ff863e"));
            drawRegion(canvas);
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        RegionIterator iterator = new RegionIterator(mRegion);
        Rect rect = new Rect();
        while (iterator.next(rect)) {
            canvas.drawRect(rect, mPaint);
        }

    }


}
