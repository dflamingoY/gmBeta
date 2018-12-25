package org.xiaoxingqi.gmdoc.wegidt.ninegridView;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * @author yueban
 * Date: 2017/9/19
 * Email: fbzhh007@gmail.com
 */
public interface ItemImageLongClickListener<T> {
    boolean onItemImageLongClick(Context context, View imageView, int index, List<T> list);
}
