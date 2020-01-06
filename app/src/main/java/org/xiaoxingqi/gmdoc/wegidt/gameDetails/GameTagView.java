package org.xiaoxingqi.gmdoc.wegidt.gameDetails;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.entity.BaseSimpleData;
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData;
import org.xiaoxingqi.gmdoc.impl.IConstant;
import org.xiaoxingqi.gmdoc.tools.AppTools;
import org.xiaoxingqi.gmdoc.tools.PreferenceTools;
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout;

import java.util.List;

/**
 * Created by yzm on 2017/11/10.
 */

public class GameTagView extends BaseLayout {
    private Context mContext;
    LinearLayout linearTagContainer;
    View iv_ShowMore;
    CustomFlowTables flowtables;
    View viewClose;
    View linearFlowView;
    View relative_Horizont;
    HorizontalScrollView scrollView;

    public GameTagView(@NonNull Context context) {
        super(context);
    }

    public GameTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        linearTagContainer = findViewById(R.id.linearTagContainer);
        iv_ShowMore = findViewById(R.id.iv_ShowMore);
        flowtables = findViewById(R.id.flowtables);
        viewClose = findViewById(R.id.viewClose);
        linearFlowView = findViewById(R.id.linearFlowView);
        relative_Horizont = findViewById(R.id.relative_Horizont);
        scrollView = findViewById(R.id.scrollView);


        bindEvent();
    }

    private void bindEvent() {
        viewClose.setOnClickListener(v -> {
            relative_Horizont.setVisibility(VISIBLE);
            linearFlowView.setVisibility(GONE);
            scrollView.scrollTo(0, 0);
        });
        iv_ShowMore.setOnClickListener(v -> {
            relative_Horizont.setVisibility(GONE);
            linearFlowView.setVisibility(VISIBLE);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_game_tab_list;
    }

    public void setData(List<BaseSimpleData> data) {
        UserInfoData infoData = PreferenceTools.getObj(mContext, IConstant.USERINFO, UserInfoData.class);
        linearTagContainer.removeAllViews();
        for (BaseSimpleData bean : data) {
            View view = View.inflate(mContext, R.layout.layout_tag_view, null);
            TextView tvTagName = view.findViewById(R.id.tv_Tag_Name);
            if (infoData != null && infoData.getData() != null) {
                if (infoData.getData().getUid().equals(bean.getUid())) {
                    tvTagName.setSelected(true);
                } else {
                    tvTagName.setSelected(false);
                }
            } else {
                tvTagName.setSelected(false);
            }
            tvTagName.setText(bean.getLabel());
            linearTagContainer.addView(view);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemClicklistener != null) {
                        mItemClicklistener.click(bean);
                    }
                    return false;
                }
            });
        }
        linearTagContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    linearTagContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    linearTagContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                int measuredWidth = linearTagContainer.getMeasuredWidth();
                if (measuredWidth > AppTools.getWindowsWidth(mContext)) {
                    iv_ShowMore.setVisibility(VISIBLE);
                } else {
                    iv_ShowMore.setVisibility(GONE);
                }
            }
        });
        flowtables.removeAllViews();
        for (BaseSimpleData listBean : data) {
            View view = View.inflate(mContext, R.layout.layout_tag_view, null);
            TextView tv = view.findViewById(R.id.tv_Tag_Name);
            tv.setText(listBean.getLabel());
            flowtables.addView(view);
            if (infoData != null && infoData.getData() != null) {
                if (infoData.getData().getUid().equals(listBean.getUid())) {
                    tv.setSelected(true);
                } else {
                    tv.setSelected(false);
                }
            } else {
                tv.setSelected(false);
            }
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemClicklistener != null) {
                        mItemClicklistener.click(listBean);
                    }
                    return false;
                }
            });
        }
    }

    private OnItemClicklistener mItemClicklistener;

    public void setOnItemClickListener(OnItemClicklistener listener) {
        mItemClicklistener = listener;
    }

    public interface OnItemClicklistener {
        void click(BaseSimpleData data);
    }

}
