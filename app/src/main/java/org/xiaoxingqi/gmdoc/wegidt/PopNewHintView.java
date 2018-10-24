package org.xiaoxingqi.gmdoc.wegidt;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

/**
 * 在整個窗體展示一个View   2s后自动消失
 */
public class PopNewHintView {

    private static ViewGroup group;
    private static View view;
    private static Activity mactivity;
    private static ValueAnimator valueAnimator;

    /**
     * @param activity
     * @param data     展示的数据
     */
    public static void attach(Activity activity, Object data) {
        mactivity = activity;
        group = (ViewGroup) activity.getWindow().getDecorView();
        view = View.inflate(activity, R.layout.layout_toast, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppTools.dp2px(activity, 50));
        group.addView(view, params);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", -AppTools.dp2px(activity, 50), 0f);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 开始计时  3s 倒计时
                 */
                valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(3000);
                valueAnimator.start();
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dettach();
                    }
                });
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.cancel();
                valueAnimator = null;
                Toast.makeText(mactivity, "点击事件", Toast.LENGTH_SHORT).show();
                dettach();
            }
        });
        animator.start();
    }

    /**
     * 移除窗体
     */
    private synchronized static void dettach() {
        if (group != null && view != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", -AppTools.dp2px(mactivity, 50));
            animator.setDuration(320);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    group.removeView(view);
                    group = null;
                    view = null;
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);

                }
            });
            animator.start();
        }
    }

}
