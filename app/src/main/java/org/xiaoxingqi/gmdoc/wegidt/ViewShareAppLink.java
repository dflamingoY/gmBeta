package org.xiaoxingqi.gmdoc.wegidt;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import org.xiaoxingqi.gmdoc.R;

/**
 * Created by yzm on 2018/1/5.
 */

public class ViewShareAppLink extends BaseLayout {

    private String mName;
    private TextView mTv_copyLink;
    private TextView mTv_link;
    private Context mContext;

    public ViewShareAppLink(@NonNull Context context) {
        this(context, null);
    }

    public ViewShareAppLink(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewShareAppLink(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewShareAppLink);
        mName = array.getString(R.styleable.ViewShareAppLink_typeName);
        array.recycle();
        mContext = context;
        initView();
    }

    private void initView() {
        TextView mTvType = findViewById(R.id.tv_Type);
        if (!TextUtils.isEmpty(mName))
            mTvType.setText(mName);
        mTv_copyLink = findViewById(R.id.tv_CopyLink);
        mTv_link = findViewById(R.id.tv_Link);
        mTv_copyLink.setOnClickListener(v -> {
            ClipboardManager manager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setText(mTv_link.getText().toString());
            Toast.makeText(mContext, "已复制到剪切板", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_view_share_link;
    }

    public void setLink(String link) {
        if (!TextUtils.isEmpty(link)) {
            mTv_link.setText(link);
        }
    }
}
