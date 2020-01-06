package org.xiaoxingqi.gmdoc.wegidt.ninegridView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.xiaoxingqi.gmdoc.R;

/**
 * Created by Jaeger on 16/2/24.
 * <p>
 * Email: chjie.jaeger@gamil.com
 * GitHub: https://github.com/laobie
 */
public class GridImageView extends AppCompatImageView {
    private int gifflag = R.mipmap.img_mark_gif;
    private Bitmap bitmap;
    private int width, height;
    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;

    public GridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridImageView(Context context) {
        super(context);
        this.setAdjustViewBounds(true);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), gifflag);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isGif) {
            int width1 = bitmap.getWidth();
            int height1 = bitmap.getHeight();
            canvas.drawBitmap(bitmap, width - width1, height - height1, null);
        }
    }

    public void loadPic(String url, boolean isSpoiler) {
        isGif = url.contains(".gif");
        Glide.with(getContext())
                .load(isSpoiler ? R.mipmap.img_hidden_106 : url)
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        setImageBitmap(resource);
                    }
                });
    }

    private boolean isGif = false;
}