package org.xiaoxingqi.gmdoc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper;
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter;
import org.xiaoxingqi.gmdoc.entity.user.LoveGameData;
import org.xiaoxingqi.gmdoc.tools.AppTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/11/27.
 * <p>
 * 最高273dp
 */

public class SelectLoveGameIndexDialog extends Dialog {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<LoveGameData.DataBean> mData;
    private QuickAdapter mAdapter;
    private int selected;

    public SelectLoveGameIndexDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_love_game_index);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new QuickAdapter(mContext, R.layout.item_select_text, mData) {
            @Override
            protected void convert(BaseAdapterHelper helper, Object item) {
                int tag = (int) helper.itemView.getTag();
                helper.getTextView(R.id.tv_Text).setText("No." + (tag + 1));
                if (tag == selected) {
                    helper.getTextView(R.id.tv_Text).setBackgroundColor(Color.parseColor("#EEEEEE"));
                } else {
                    helper.getTextView(R.id.tv_Text).setBackgroundDrawable(null);
                }
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        LinearLayout.LayoutParams params = null;
        if (mData.size() < 8) {
            params = new LinearLayout.LayoutParams(AppTools.dp2px(mContext, 80), AppTools.dp2px(mContext, mData.size() * 32));
        } else {
            params = new LinearLayout.LayoutParams(AppTools.dp2px(mContext, 80), AppTools.dp2px(mContext, 253));
        }
        params.setMargins(0, AppTools.dp2px(mContext, 10), 0, AppTools.dp2px(mContext, 10));
        mRecyclerView.setLayoutParams(params);
        mAdapter.setOnItemClickListener((view, position) -> {
            if (mItemSelectedListener != null) {
                mItemSelectedListener.selected(position);
            }
            dismiss();
        });
    }


    public interface ItemSelectedListener {
        void selected(int click);
    }

    private ItemSelectedListener mItemSelectedListener;

    public void setItemSelectedListener(ItemSelectedListener listener) {
        mItemSelectedListener = listener;
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(ArrayList<LoveGameData.DataBean> data, int x, int y, int selected) {
        this.selected = selected;
        mData = data;
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = x; // 新位置X坐标
        lp.y = y; // 新位置Y坐标
        lp.width = AppTools.dp2px(mContext, 80); // 宽度
        window.setAttributes(lp);
        show();
    }
}
