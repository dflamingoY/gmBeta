package org.xiaoxingqi.gmdoc.wegidt.gifTools;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CentMeng csdn@vip.163.com on 16/7/19.
 */
public class GlideImageGetter implements Html.ImageGetter, Drawable.Callback {

    private final Context mContext;

    private final TextView mTextView;

    private final Set<ImageGetterViewTarget> mTargets;

    public static GlideImageGetter get(View view) {
        return (GlideImageGetter) view.getTag(R.id.drawable_callback_tag);
    }

    public void clear() {
        GlideImageGetter prev = get(mTextView);
        if (prev == null) return;

        for (ImageGetterViewTarget target : prev.mTargets) {
            Glide.clear(target);
        }
    }

    public GlideImageGetter(Context context, TextView textView) {
        this.mContext = context;
        this.mTextView = textView;

        //        clear(); 屏蔽掉这句在TextView中可以加载多张图片
        mTargets = new HashSet<>();
        mTextView.setTag(R.id.drawable_callback_tag, this);
    }

    @Override
    public Drawable getDrawable(String url) {
        final UrlDrawable_Glide urlDrawable = new UrlDrawable_Glide();
        Glide.with(mContext)
                .load(url)
                .override(1, 1)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageGetterViewTarget(mTextView, urlDrawable));
        return urlDrawable;
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        mTextView.invalidate();
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {

    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {

    }

    private class ImageGetterViewTarget extends ViewTarget<TextView, GlideDrawable> {

        private final UrlDrawable_Glide mDrawable;

        private ImageGetterViewTarget(TextView view, UrlDrawable_Glide drawable) {
            super(view);
            mTargets.add(this);
            this.mDrawable = drawable;
        }

        @Override
        public void onResourceReady(final GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
            Rect rect;
            if (resource.getIntrinsicWidth() > 100) {
                float width;
                float height;
                System.out.println("Image width is " + resource.getIntrinsicWidth());
                System.out.println("View width is " + view.getWidth());
                if (resource.getIntrinsicWidth() >= getView().getWidth()) {
                    float downScale = (float) resource.getIntrinsicWidth() / getView().getWidth();
                    width = (float) resource.getIntrinsicWidth() / (float) downScale;
                    height = (float) resource.getIntrinsicHeight() / (float) downScale;
                } else {
                    float multiplier = (float) getView().getWidth() / resource.getIntrinsicWidth();
                    width = (float) resource.getIntrinsicWidth() * (float) multiplier;
                    height = (float) resource.getIntrinsicHeight() * (float) multiplier;
                }
                System.out.println("New Image width is " + width);
                rect = new Rect(8, 0, AppTools.dp2px(mContext, 15), AppTools.dp2px(mContext, 15));
            } else {
                rect = new Rect(8, 0, AppTools.dp2px(mContext, 15) + 8, AppTools.dp2px(mContext, 15));
            }
            resource.setBounds(rect);
            mDrawable.setBounds(rect);
            mDrawable.setDrawable(resource);
            if (resource.isAnimated()) {
                mDrawable.setCallback(get(getView()));
                resource.setLoopCount(GlideDrawable.LOOP_FOREVER);
                resource.start();
            }
            getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    /**
                     * 添加视图
                     */
                    if (resource != null)
                        resource.start();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    if (resource != null)
                        if (resource.isRunning())
                            resource.stop();
                }
            });
            getView().setText(getView().getText());
            getView().invalidate();
        }

        private Request request;

        @Override
        public Request getRequest() {
            return request;
        }

        @Override
        public void setRequest(Request request) {
            this.request = request;
        }
    }
}