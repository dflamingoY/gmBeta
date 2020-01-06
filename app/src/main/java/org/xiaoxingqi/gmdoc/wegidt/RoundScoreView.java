package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

/**
 * Created by yzm on 2017/11/2.
 * 圆角矩形评分控件
 * 0-4  红
 * 5-7  黄
 * 8-10 绿
 */

public class RoundScoreView extends AppCompatTextView {
    private Context mContext;
    private Paint mPaint;
    private float mScore = 0;
    private int mAnInt = 1;//矩形   否则 圆形

    public RoundScoreView(Context context) {
        this(context, null, 0);
    }

    public RoundScoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundScoreView);
        //默认位矩形
        mAnInt = typedArray.getInt(R.styleable.RoundScoreView_bgStyle, 1);
        typedArray.recycle();
        mContext = context;
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#CCCCCC"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mScore < 0) {//默认灰色
            mPaint.setColor(Color.parseColor("#CCCCCC"));
        } else if (mScore >= 0 && mScore <= 4) {//红色
            mPaint.setColor(Color.parseColor("#f22b24"));
        } else if (mScore > 4 && mScore <= 7) {//黄色
            mPaint.setColor(Color.parseColor("#ffd116"));
        } else if (mScore > 7 && mScore <= 10) {//绿色
            mPaint.setColor(Color.parseColor("#49d800"));
        }
        if (mAnInt == 1) {//矩形
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), AppTools.dp2px(mContext, 4), AppTools.dp2px(mContext, 4), mPaint);
        } else {//圆形
            canvas.drawCircle(getWidth() * 1f / 2, getHeight() * 1f / 2, getWidth() * 1f / 2, mPaint);
        }
        super.onDraw(canvas);
        //绘制背景色
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    /**
     * 设置评分
     *
     * @param score
     */
    public void setScore(float score) {
        mScore = score;
        if (score < 0) {
            setText("tbd");
        } else {
            String text = score + "";
            if (text.contains(".0")) {
                text = text.replace(".0", "");
            }
            setText(text);
        }
    }

    /**
     * @param score
     * @param type  背景形状
     */
    public void setScore(float score, boolean type) {
        mScore = score;
        mAnInt = type ? 0 : 1;
        if (score < 0) {
            setText("tbd");
        } else {
            setText(((int) score) + "");
        }
    }
}
