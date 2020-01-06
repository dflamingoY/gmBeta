package org.xiaoxingqi.gmdoc.wegidt.gameDetails;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout;

/**
 * Created by yzm on 2017/11/6.
 */

public class HintTagView extends BaseLayout {

    private boolean isFirst = false;
    private boolean isShow;
    private boolean isRunning;

    public HintTagView(@NonNull Context context) {
        this(context, null);
    }

    public HintTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_Close).setOnClickListener(v -> {
            if (isShow)
                showMenu();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_hint_tag_view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isFirst) {
            ObjectAnimator.ofFloat(this, "alpha", 0).setDuration(0).start();
            isFirst = true;
        }
    }

    public void showMenu() {
        if (isRunning) {
            return;
        }
        if (isShow) {
            ObjectAnimator alpha = ObjectAnimator.ofFloat(this, "alpha", 1, 0).setDuration(320);
            alpha.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    isRunning = false;
                    isShow = false;
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    isRunning = true;
                }
            });
            alpha.start();
        } else {
            ObjectAnimator alpha = ObjectAnimator.ofFloat(this, "alpha", 0, 1).setDuration(320);
            alpha.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    isRunning = false;
                    isShow = true;
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    isRunning = true;
                }
            });
            alpha.start();
        }
    }

    public boolean isShow() {
        return isShow;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isShow;
    }
}
