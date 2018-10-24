package org.xiaoxingqi.gmdoc.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xiaoxingqi.gmdoc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/11/2.
 */

public abstract class BaseHomeAdapter<T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper> implements View.OnClickListener {

    private final int ITEM_TYPE_IMG = 1;//图片模式
    private final int ITEM_TYPE_LIST = 2;//游戏列表模式
    private List<T> data;
    private Context mContext;

    /**
     * @param context
     * @param data
     */
    public BaseHomeAdapter(Context context, List<T> data) {
        mContext = context;
        this.data = data == null ? new ArrayList<T>() : data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 2 || position == 6) {
            return ITEM_TYPE_IMG;
        }
        return ITEM_TYPE_LIST;
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_IMG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list_bowen, parent, false);
            BaseAdapterHelper vh = new BaseAdapterHelper(view);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list_game, parent, false);
            BaseAdapterHelper vh = new BaseAdapterHelper(view);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper helper, int position) {
        helper.itemView.setTag(position);
        T item = getItem(position);
        convert((H) helper, item);
    }

    protected abstract void convert(H helper, T item);

    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {

    }
}
