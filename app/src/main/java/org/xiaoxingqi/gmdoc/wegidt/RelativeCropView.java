package org.xiaoxingqi.gmdoc.wegidt;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by yzm on 2017/11/17.
 */

public class RelativeCropView extends RelativeLayout {
    public RelativeCropView(Context context) {
        super(context);
    }

    public RelativeCropView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeCropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(size / 2, MeasureSpec.EXACTLY));
    }
}
