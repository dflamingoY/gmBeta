package org.xiaoxingqi.gmdoc.wegidt.textView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.view.MotionEventCompat;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import org.xiaoxingqi.gmdoc.entity.other.SelectionBean;
import org.xiaoxingqi.gmdoc.tools.AppConfig;
import org.xiaoxingqi.gmdoc.tools.AppTools;
import org.xiaoxingqi.gmdoc.tools.FaceData;
import org.xiaoxingqi.gmdoc.wegidt.gifTools.GlideImageGetter;
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by yzm on 2017/12/4.
 * 显示表情 移除剧透
 * 文本可点击
 */

public class WalletTextView extends AppCompatTextView {
    private Context mContext;
    private List<Point> arrays = new ArrayList<>();

    public WalletTextView(Context context) {
        this(context, null);
    }

    public WalletTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {

    }

    public void setData(String text, int contentStart, String title, boolean isClick) {
        SpannableStringBuilder htmlStr = (SpannableStringBuilder) Html.fromHtml(text.toString());
        Pattern pattern = Pattern.compile(AppConfig.SPOLIER);
        Matcher matcher = pattern.matcher(htmlStr);
        int count = 0;
        while (matcher.find()) {//替换需要更改的文本
            final String at = matcher.group();
            if (at != null) {
                int start = matcher.start();
                int end = start + at.length();
                int orignalX = start - 5 * count;
                int orignalY = end - 5 * (count + 1);
                htmlStr.delete(orignalX, orignalX + "[剧透:".length());
                htmlStr.delete(orignalY, orignalY + 1);
                count++;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        setText(htmlStr);
        setMovementMethod(LinkMovementMethod.getInstance());
        final SpannableString tmep = (SpannableString) getText();
        if (tmep instanceof Spannable) {
            final int end = tmep.length();
            final Spannable sp = (Spannable) getText();
            ImageSpan[] imgs = sp.getSpans(0, end, ImageSpan.class);
            for (ImageSpan url : imgs) {
                VerticalImageSpan span = new VerticalImageSpan(getUrlDrawable(url.getSource(), this), url.getSource());
                tmep.setSpan(span, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        pattern = Pattern.compile(AppConfig.EMOJI);
        matcher.reset();
        matcher = pattern.matcher(tmep);
        while (matcher.find()) {
            String emoji = matcher.group();
            if (emoji != null) {
                String emojiPath = null;
                int start = matcher.start();
                int end = start + emoji.length();
                if ((emojiPath = FaceData.staticFaceInfo.get(emoji)) != null) {
                    try {
                        InputStream open = mContext.getAssets().open(emojiPath);
                        BitmapDrawable drawable = new BitmapDrawable(open);
                        drawable.setBounds(0, 0, AppTools.dp2px(mContext, 16), AppTools.dp2px(mContext, 16));
                        VerticalImageSpan span = new VerticalImageSpan(drawable);
                        tmep.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (isClick) {
            int start = 0;
            int end = title.length();
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2d7dd2"));
            tmep.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            arrays.add(new Point(start, end));

            ForegroundColorSpan contentSpan = new ForegroundColorSpan(Color.parseColor("#2d7dd2"));
            tmep.setSpan(contentSpan, contentStart, tmep.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            arrays.add(new Point(contentStart, tmep.length()));

            setOnTouchListener(new OnTouchListener() {

                int downX, downY;
                int id;
                SelectionBean downSection = null;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = MotionEventCompat.getActionMasked(event);
                    Layout layout = getLayout();
                    if (layout == null) {
                        return false;
                    }
                    int line = 0;
                    int index = 0;

                    switch (action) {
                        case MotionEvent.ACTION_DOWN://TODO 最后一行点击问题 网址链接
                            int actionIndex = event.getActionIndex();
                            id = event.getPointerId(actionIndex);
                            downX = (int) event.getX(actionIndex);
                            downY = (int) event.getY(actionIndex);
                            line = layout.getLineForVertical(getScrollY() + (int) event.getY());
                            index = layout.getOffsetForHorizontal(line, (int) event.getX());
                            int lastRight = (int) layout.getLineRight(line);
                            if (lastRight < event.getX()) {  //文字最后为话题时，如果点击在最后一行话题之后，也会造成话题被选中效果
                                return false;
                            }
                            for (Point point : arrays) {//判断是否存在点击剧透 取消剧透
                                if (index >= point.x && index <= point.y) {
                                    if (mResultClicklistener != null) {
                                        if (point.x == 0) {
                                            mResultClicklistener.titleClick();
                                        } else {
                                            mResultClicklistener.contentClick();
                                        }
                                    }

                                }
                            }
                            return false;
                    }
                    return true;
                }
            });
        }
        setText(tmep);
    }

    public static Drawable getUrlDrawable(String source, TextView mTextView) {
        GlideImageGetter imageGetter = new GlideImageGetter(mTextView.getContext(), mTextView);
        return imageGetter.getDrawable(source);
    }

    private OnResultClicklistener mResultClicklistener;

    public void setResultClicklistener(OnResultClicklistener resultClicklistener) {
        mResultClicklistener = resultClicklistener;
    }

    public interface OnResultClicklistener {

        void titleClick();

        void contentClick();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        arrays.clear();
    }
}
