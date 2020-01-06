package org.xiaoxingqi.gmdoc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import org.xiaoxingqi.gmdoc.tools.AppTools;

public abstract class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        initView();
    }

    protected void initSystem() {
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.gravity = Gravity.BOTTOM;
        p.width = AppTools.getWindowsWidth(getContext());
        getWindow().setAttributes(p);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}
