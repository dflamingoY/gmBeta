package org.xiaoxingqi.gmdoc.wegidt.ninegridView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.tools.AppTools;

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
     * 渲染图像，使用图像为绘制图形着色
     */
    private BitmapShader mBitmapShader;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;
    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;

    private RectF mRoundRect;

    /**
     * 圆角的大小
     */
    private int mBorderRadius;

    public GridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridImageView(Context context) {
        super(context);
        this.setAdjustViewBounds(true);
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), gifflag);
        mBorderRadius = AppTools.dp2px(context, 2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        mRoundRect = new RectF(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //        if (getDrawable() == null) {
        //            return;
        //        }
        //        setUpShader();
        //        canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius,
        //                mBitmapPaint);
        if (isGif) {
            int width1 = bitmap.getWidth();
            int height1 = bitmap.getHeight();
            canvas.drawBitmap(bitmap, width - width1, height - height1, null);
        }
    }

    public void loadPic(String url) {
        isGif = url.contains(".gif");

        Glide.with(getContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        setImageBitmap(resource);
                    }
                });
    }

    /**
     * 初始化BitmapShader
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bmp = drawableToBitamp(drawable);
        if (bmp == null)
            return;
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
        scale = Math.max(getWidth() * 1.0f / bmp.getWidth(), getHeight()
                * 1.0f / bmp.getHeight());
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        if (w <= 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private boolean isGif = false;

    /**
     * 这是gif 的标记
     */
    public void setGifFlag(boolean isGif) {
        this.isGif = isGif;
        //        postInvalidate();
        //        invalidate();
    }
}