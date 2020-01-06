package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import org.xiaoxingqi.gmdoc.tools.AppTools;
import org.xiaoxingqi.gmdoc.R;

/**
 * Created by yzm on 2017/10/30.
 */

public class ActionBarView extends BaseLayout implements View.OnClickListener {
    private Context mContext;
    private ImageView mHomeButton;
    private CustomSelecor mTab01;
    private CustomSelecor mTab02;
    private CustomSelecor mTab03;
    private CustomSelecor mTab04;
    private boolean mIsFrist = true;

    private Paint mPaint;


    public ActionBarView(@NonNull Context context) {
        this(context, null, 0);
    }

    public ActionBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_tab_layout;
    }

    private void initView() {
        setClipChildren(false);

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.color_gray_division));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setFilterBitmap(true);
        mPaint.setShadowLayer(5, 0, 3, getResources().getColor(R.color.color_gray_division));

        mHomeButton = findViewById(R.id.Iv_HomeButton);
        mTab01 = findViewById(R.id.tab_01);
        mTab02 = findViewById(R.id.tab_02);
        mTab03 = findViewById(R.id.tab_03);
        mTab04 = findViewById(R.id.tab_04);

        mHomeButton.setOnClickListener(this);
        mTab01.setOnClickListener(this);
        mTab02.setOnClickListener(this);
        mTab03.setOnClickListener(this);
        mTab04.setOnClickListener(this);

        mTab01.setDefaultView(R.mipmap.btn_home, "主页", false);
        mTab01.setSelectView(R.mipmap.btn_home_selected, R.color.color_666666);

        mTab02.setDefaultView(R.mipmap.btn_game, "游戏库", false);
        mTab02.setSelectView(R.mipmap.btn_game_selected, R.color.color_666666);

        mTab03.setDefaultView(R.mipmap.btn_group, "关注圈", false);
        mTab03.setSelectView(R.mipmap.btn_group_selected, R.color.color_666666);

        mTab04.setDefaultView(R.mipmap.btn_ring, "消息", false);
        mTab04.setSelectView(R.mipmap.btn_ring_selected, R.color.color_666666);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mIsFrist) {
            mTab01.setSelected();
            mIsFrist = false;
        }
    }

    @Override

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        float sqrt = (float) Math.sqrt(29 * 29 - 21 * 21);
        float v = (float) Math.toDegrees(Math.acos(21f / 29));
        mPaint.setStrokeWidth(AppTools.dp2px(mContext, 1));
        canvas.drawRoundRect(new RectF(0, 0, getWidth() * 1f / 2 - AppTools.dp2px(mContext, (int) (sqrt)), AppTools.dp2px(mContext, 1)), 5, 5, mPaint);
        canvas.drawRoundRect(new RectF(getWidth() * 1f / 2 + AppTools.dp2px(mContext, (int) (sqrt + 0.5f)), 0, getWidth(), AppTools.dp2px(mContext, 1)), 5, 5, mPaint);
        mPaint.setStrokeWidth(AppTools.dp2px(mContext, 2));
        canvas.drawArc(new RectF(getWidth() * 1f / 2 - AppTools.dp2px(mContext, 29), -AppTools.dp2px(mContext, 8), getWidth() * 1f / 2 + AppTools.dp2px(mContext, 29), getHeight())
                , v - 90, -2 * v, false, mPaint);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof CustomSelecor) {
            if (mClickListener != null) {
                boolean tabVlick = mClickListener.tabVlick(view);
                if (tabVlick) {
                    selectedTitle((CustomSelecor) view);
                }
            }
        } else if (view instanceof ImageView) {
            if (mClickListener != null) {
                mClickListener.tabVlick(view);

            }
        }
    }


    private OnTabOnClickListener mClickListener;

    public void setOnTabClickListener(OnTabOnClickListener clickListener) {

        this.mClickListener = clickListener;
    }

    private void selectedTitle(CustomSelecor view) {
        clearAll();
        view.setSelected();
    }

    private void clearAll() {
        mTab04.clearSelect();
        mTab03.clearSelect();
        mTab02.clearSelect();
        mTab01.clearSelect();
    }


    public interface OnTabOnClickListener {
        boolean tabVlick(View view);
    }

    /**
     * 设置标记是否可见
     *
     * @param isFlag
     */
    public void setFlag(boolean isFlag) {
        if (mTab04 != null)
            mTab04.setButtonFlag(isFlag);
    }


    public void setCurrentSelect(int child) {
        if (child == 0) {
            selectedTitle(mTab01);
        } else if (child == 1) {
            selectedTitle(mTab02);
        }
    }

}
