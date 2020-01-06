package org.xiaoxingqi.gmdoc.core.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghejie on 15/8/8.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper> implements View.OnClickListener {
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context context;

    protected final int layoutResId;

    protected final List<T> data;

    protected boolean displayIndeterminateProgress = false;

    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public BaseQuickAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     * @param headView    头布局
     */
    private View mHeadView;
    private boolean isHeadView;
    private boolean isHaveHeadView = false;
    //是添加的头布局
    private static final int ITEM_VIEW_TYPE_HEADER = 1;
    //是正常显示的View
    private static final int ITEM_VIEW_TYPE_ITEM = 0;
    private static final int ITEM_VIEW_TYPE_BOTTOM = 2;
    private boolean isHaveFoot = false;
    private View mFootView;

    public void setIsHeadView(boolean isHeadView) {
        this.isHeadView = isHeadView;
    }

    private int mHeadViewCount = 0;
    private int mFootViewCount = 0;

    public void setIsHaveHeadView(boolean isHaveHeadView) {
        this.isHaveHeadView = isHaveHeadView;
        if (isHaveHeadView) {
            mHeadViewCount = 1;
        }
    }

    public void setIsHaveFoot(boolean view) {
        if (view) {
            mFootViewCount = 1;
            isHaveFoot = true;
        } else {
            mFootViewCount = 0;
            isHaveFoot = false;
        }
    }

    /**
     * 判断是否尾部View
     */
    public boolean isFootView(int position) {
        if (data.size() == 0) {
            return position == mHeadViewCount + data.size();
        }
        return position == data.size() + mHeadViewCount;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHaveHeadView) {
            if (isHeader(position)) {
                return ITEM_VIEW_TYPE_HEADER;
            }
        }
        if (isHaveFoot) {
            if (isFootView(position))
                return ITEM_VIEW_TYPE_BOTTOM;
        }
        return super.getItemViewType(position);
    }

    public BaseQuickAdapter(Context context, int layoutResId, List<T> data, View headView) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
        this.mHeadView = headView;
        setIsHaveHeadView(true);
    }

    public BaseQuickAdapter(Context context, int layoutResId, List<T> data, View headView, View footView) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
        mFootView = footView;
        if (null != mFootView) {
            mFootViewCount = 1;
            isHaveFoot = true;
        }

        if (headView != null) {
            this.mHeadView = headView;
            setIsHaveHeadView(true);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + mHeadViewCount + mFootViewCount;
    }

    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return new BaseAdapterHelper(mHeadView);
        }
        if (viewType == ITEM_VIEW_TYPE_BOTTOM) {
            return new BaseAdapterHelper(mFootView);
        }

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        view.setOnClickListener(this);
        BaseAdapterHelper vh = new BaseAdapterHelper(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper helper, int position) {
        if (isHaveHeadView && isHeader(position)) {
            return;
        }
        if (isHaveFoot && isFootView(position))
            return;
        helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(v, (int) v.getTag());
                    return true;
                } else {
                    return false;
                }
            }
        });
        helper.itemView.setTag(position - mHeadViewCount);
        T item = getItem(position - mHeadViewCount);
        convert((H) helper, item);
    }

    /**
     * 移除头部View
     */
    public void removeHeard(boolean isAttach) {
        if (mHeadView == null)
            return;
        if (isAttach) {//显示
            isHaveHeadView = true;
            mHeadViewCount = 1;
        } else {//移除
            isHaveHeadView = false;
            mHeadViewCount = 0;
        }
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item);

    public void notifyBFootView(boolean isAttach) {
        setIsHaveFoot(isAttach);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

}
