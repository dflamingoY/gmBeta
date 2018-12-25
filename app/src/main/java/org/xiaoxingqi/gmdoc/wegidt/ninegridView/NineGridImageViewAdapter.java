package org.xiaoxingqi.gmdoc.wegidt.ninegridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.wegidt.SpoilerImageView;

import java.util.List;

/**
 * Created by Jaeger on 16/2/24.
 * <p>
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
public abstract class NineGridImageViewAdapter<T> {
    protected abstract void onDisplayImage(Context context, View imageView, T t);

    protected void onItemImageClick(Context context, View imageView, int index, List<T> list) {
    }

    protected boolean onItemImageLongClick(Context context, View imageView, int index, List<T> list) {
        return false;
    }

    /**
     * create View
     *
     * @param context
     * @return
     */
    protected View generateImageView(Context context, ViewGroup group) {
        //        View imageView = LayoutInflater.from(context).inflate(R.layout.item_spoiler_img, null, false);
        //                GridImageView imageView = new GridImageView(context);
        //                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        View imageView = new SpoilerImageView(context);
        return imageView;
    }
}