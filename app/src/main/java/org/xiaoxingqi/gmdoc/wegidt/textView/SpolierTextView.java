package org.xiaoxingqi.gmdoc.wegidt.textView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

import org.xiaoxingqi.gmdoc.entity.other.SelectionBean;
import org.xiaoxingqi.gmdoc.impl.IConstant;
import org.xiaoxingqi.gmdoc.tools.AppTools;
import org.xiaoxingqi.gmdoc.tools.FaceData;
import org.xiaoxingqi.gmdoc.tools.SPUtils;
import org.xiaoxingqi.gmdoc.wegidt.gifTools.GlideImageGetter;
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.xiaoxingqi.gmdoc.tools.AppConfig.ALL;
import static org.xiaoxingqi.gmdoc.tools.AppConfig.EMOJI;
import static org.xiaoxingqi.gmdoc.tools.AppConfig.SPOLIER;


/**
 * Created by yzm on 2017/12/4.
 * <p>
 * 所有标签都匹配
 *
 * @列表
 */

public class SpolierTextView extends AppCompatTextView {

    private List<Point> arrays = new ArrayList<>();
    private List<SelectionBean> selects = new ArrayList<>();
    private Paint mPaint;
    private Context mContext;
    private boolean isSpoiler = false;
    private boolean isAttchWindows = false;

    public SpolierTextView(Context context) {
        this(context, null, 0);
    }

    public SpolierTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpolierTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#6d6d6d"));
        mPaint.setStyle(Paint.Style.FILL);
        //        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 绘制一层阴影
         */
        if (isAttchWindows)
            getMeasureCoondiration(canvas);
        /**
         * 获取第一行换行的角标
         */
    }

    /**
     * 获取字符的 rect
     * 判断当前文字在第几行
     * 同一行 直接绘制黑色块
     * 如果是已经换行   判断换行  且获取换行的矩形
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void getMeasureCoondiration(Canvas canvas) {
        if (!SPUtils.getBoolean(mContext, IConstant.IS_SPOLIER, false)) {
            return;
        }
        Layout layout = getLayout();
        if (arrays.size() > 0) {
            for (int a = 0; a < arrays.size(); a++) {
                if (!isAttchWindows)
                    break;
                /**
                 * 获取一行的字数
                 */
                Rect bound = new Rect();
                /**
                 *当前文本所在行数
                 */
                int startLine = layout.getLineForOffset(arrays.get(a).x);
                /**
                 * 文本结束的行数
                 */

                int endLine = layout.getLineForOffset(arrays.get(a).y);
                int lineCount = layout.getLineCount();
                float lineSpacingExtra = getLineSpacingExtra();
                float lineSpacingMultiplier = getLineSpacingMultiplier();
                layout.getLineBounds(startLine, bound);
                //                int yAxisTop = bound.top;//字符顶部y坐标
                int yAxisBottom = bound.bottom;//字符底部y坐标
                int lineHeight = (int) ((getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) * 1f / lineCount + 0.5f);//计算单行的高度
                int yAxisTop = startLine * lineHeight;//字符顶部y坐标
                int xAxisLeft = (int) layout.getPrimaryHorizontal(arrays.get(a).x);//字符左边x坐标
                int xAxisRight = (int) layout.getSecondaryHorizontal(arrays.get(a).y);//偏移量
                if (false) {
                    continue;
                }
                if (startLine == endLine) {//只有一行的时候
                    canvas.drawRect(new RectF(xAxisLeft, lineHeight * startLine + 3, (int) layout.getSecondaryHorizontal(arrays.get(a).y), yAxisTop + lineHeight - 3), mPaint);
                } else {//多行
                    /**
                     * 换行绘制 需要绘制两行 黑色区域
                     */
                    canvas.drawRect(new RectF(xAxisLeft, yAxisTop + 3, bound.right, yAxisTop + lineHeight - 3), mPaint);
                    /**
                     * 循环绘制存在剧透的行数
                     */
                    if (endLine - startLine > 1) {
                        for (int i = startLine + 1; i < endLine; i++) {
                            if (!isAttchWindows)
                                return;
                            canvas.drawRect(new RectF(0, lineHeight * i + 3, bound.right, lineHeight * (i + 1) - 3), mPaint);
                        }
                    }
                    canvas.drawRect(new RectF(0, lineHeight * endLine + 3, xAxisRight, lineHeight * (endLine + 1) - 3), mPaint);
                }
            }
        }
    }

    public void setData(String text, String... tag) {
        arrays.clear();
        selects.clear();
        SpannableStringBuilder htmlStr = (SpannableStringBuilder) Html.fromHtml(text.toString());
        Pattern pattern = Pattern.compile(SPOLIER);
        Matcher matcher = pattern.matcher(htmlStr);
        while (matcher.find()) {//替换需要更改的文本
            final String at = matcher.group();
            if (at != null) {
                int start = matcher.start();
                int end = start + at.length();
                int orignalX = start - 5 * arrays.size();
                int orignalY = end - 5 * (arrays.size() + 1);
                arrays.add(new Point(orignalX, orignalY));
                htmlStr.delete(orignalX, orignalX + "[剧透:".length());
                htmlStr.delete(orignalY, orignalY + 1);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            //            setTextIsSelectable(true);
        }
        setText(htmlStr);
        setMovementMethod(LinkMovementMethod.getInstance());
        final SpannableString tmep = (SpannableString) getText();
        if (tmep instanceof Spannable) {
            final int end = tmep.length();
            final Spannable sp = (Spannable) getText();
            ImageSpan[] imgs = tmep.getSpans(0, end, ImageSpan.class);
            for (ImageSpan url : imgs) {
                VerticalImageSpan span = new VerticalImageSpan(getUrlDrawable(url.getSource(), this), url.getSource());
                tmep.setSpan(span, tmep.getSpanStart(url), tmep.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        pattern = Pattern.compile(ALL);
        matcher.reset();
        matcher = pattern.matcher(tmep);
        while (matcher.find()) {
            String at = matcher.group(1);
            String topic = matcher.group(2);
            String urlPath = matcher.group(3);
            if (at != null) {
                int start = matcher.start(1);
                int endSpoiler = start + at.length();
                selects.add(new SelectionBean(at, start, endSpoiler, 2));
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2d7dd2"));
                tmep.setSpan(colorSpan, start, endSpoiler, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            if (topic != null) {
                int start = matcher.start(2);
                int endSpoiler = start + topic.length();
                selects.add(new SelectionBean(topic, topic, start, endSpoiler, 3));
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2d7dd2"));
                tmep.setSpan(colorSpan, start, endSpoiler, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            if (urlPath != null) {
                int start = matcher.start(3);
                int endUrl = start + urlPath.length();
                selects.add(new SelectionBean(urlPath, start, endUrl, 4));
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2d7dd2"));
                tmep.setSpan(colorSpan, start, endUrl, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
        pattern = Pattern.compile(EMOJI);
        matcher.reset();
        matcher = pattern.matcher(tmep);
        while (matcher.find()) {
            String emoji = matcher.group();
            if (emoji != null) {
                int start = matcher.start();
                int emojiEnd = start + emoji.length();
                String emojiPath = null;
                if ((emojiPath = FaceData.staticFaceInfo.get(emoji)) != null) {
                    try {
                        InputStream open = mContext.getAssets().open(emojiPath);
                        BitmapDrawable drawable = new BitmapDrawable(open);
                        drawable.setBounds(0, 0, AppTools.dp2px(mContext, 16), AppTools.dp2px(mContext, 16));
                        VerticalImageSpan span = new VerticalImageSpan(drawable);
                        tmep.setSpan(span, start, emojiEnd, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (tag != null && tag.length > 0) {
            for (String str : tag)
                if (!TextUtils.isEmpty(str)) {
                    int start = tmep.toString().indexOf(str);
                    int end = tmep.toString().indexOf(str) + str.length();
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2d7dd2"));
                    try {
                        tmep.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    selects.add(new SelectionBean("", start, end, 1));
                }
        }

        setText(tmep);
        final BackgroundColorSpan span = new BackgroundColorSpan(Color.parseColor("#31000000"));
        final int slop = ViewConfiguration.get(mContext).getScaledTouchSlop();
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
                        Point clickPoint = null;
                        for (Point point : arrays) {//判断是否存在点击剧透 取消剧透
                            if (index >= point.x && index <= point.y) {
                                clickPoint = point;
                            }
                        }
                        if (clickPoint != null) {
                            arrays.remove(clickPoint);
                            invalidate();
                        }
                        for (SelectionBean section : selects) {
                            if (index >= section.getStart() && index <= section.getEnd()) {
                                tmep.setSpan(span, section.getStart(), section.getEnd(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                downSection = section;
                                setText(tmep);
                                getParent().requestDisallowInterceptTouchEvent(true);//不允许父view拦截
                                return true;
                            }
                        }

                        return false;
                    case MotionEvent.ACTION_MOVE:
                        int indexMove = event.findPointerIndex(id);
                        /**
                         * 会出现的异常 pointerIndex out of range
                         */
                        int currentX = 0;
                        int currentY = 0;
                        try {
                            currentX = (int) event.getX(indexMove);
                            currentY = (int) event.getY(indexMove);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (Math.abs(currentX - downX) < slop && Math.abs(currentY - downY) < slop) {
                            if (downSection == null) {
                                getParent().requestDisallowInterceptTouchEvent(false);//允许父view拦截
                                return false;
                            }
                            break;
                        }
                        downSection = null;
                        getParent().requestDisallowInterceptTouchEvent(false);//允许父view拦截
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        int indexUp = event.findPointerIndex(id);
                        tmep.removeSpan(span);
                        setText(tmep);
                        int upX = (int) event.getX(indexUp);
                        int upY = (int) event.getY(indexUp);
                        if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                            //TODO startActivity or whatever
                            if (downSection != null) {
                                if (downSection.getType() == 3) {//跳转搜索
                                    mContext.startActivity(new Intent(mContext, null)
                                            .putExtra("tag", downSection.getName())
                                            .putExtra("isTopic", true)
                                    );
                                } else if (downSection.getType() == 4) {
                                    mContext.startActivity(new Intent(mContext, null)
                                            .putExtra("path", downSection.getName())
                                    );
                                } else {
                                    if (mTextTouchListener != null) {
                                        mTextTouchListener.touch(downSection);
                                    }
                                }
                                downSection = null;
                            } else {
                                return false;
                            }
                        } else {
                            downSection = null;
                            return false;
                        }
                        break;
                }
                return true;
            }
        });
    }

    public interface OnTextTouchListener {
        void touch(SelectionBean bean);
    }

    private OnTextTouchListener mTextTouchListener;

    public void setOnTextTouchListener(OnTextTouchListener listener) {
        mTextTouchListener = listener;
    }

    public static Drawable getUrlDrawable(String source, TextView mTextView) {
        GlideImageGetter imageGetter = new GlideImageGetter(mTextView.getContext(), mTextView);
        return imageGetter.getDrawable(source);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttchWindows = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAttchWindows = false;
        //        arrays.clear();
        //        selects.clear();
    }
}
