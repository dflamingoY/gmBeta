package org.xiaoxingqi.gmdoc.wegidt.textView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;


import org.xiaoxingqi.gmdoc.entity.other.SelectionBean;
import org.xiaoxingqi.gmdoc.tools.AppTools;
import org.xiaoxingqi.gmdoc.tools.FaceData;
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.xiaoxingqi.gmdoc.tools.AppConfig.EMOJI;
import static org.xiaoxingqi.gmdoc.tools.AppConfig.URLPATH;


/**
 * Created by yzm on 2017/12/5.
 * <p>
 * 消息对话 的textView
 */

public class MsgTextView extends AppCompatTextView {
    private Context mContext;
    private List<SelectionBean> selects = new ArrayList<>();

    public MsgTextView(Context context) {
        this(context, null, 0);
    }

    public MsgTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MsgTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setTextData(String text) {
        SpannableStringBuilder spanned = (SpannableStringBuilder) Html.fromHtml(text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            //            setTextIsSelectable(true);
        }
        setText(spanned);
        setMovementMethod(LinkMovementMethod.getInstance());
        SpannableString ssb = (SpannableString) getText();
        if (ssb instanceof Spannable) {
            int end = ssb.length();
            ImageSpan[] imgs = ssb.getSpans(0, end, ImageSpan.class);
            for (ImageSpan url : imgs) {
                VerticalImageSpan span = new VerticalImageSpan(SpoilerTextView.getUrlDrawable(url.getSource(), this), url.getSource());
                ssb.setSpan(span, ssb.getSpanStart(url), ssb.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        Pattern pattern = Pattern.compile("(" + EMOJI + ")" + "|" + "(" + URLPATH + ")");
        Matcher matcher = pattern.matcher(ssb);
        while (matcher.find()) {
            String emoji = matcher.group(1);
            String urlPath = matcher.group(2);
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
                        ssb.setSpan(span, start, emojiEnd, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (urlPath != null) {
                if (urlPath != null) {
                    int start = matcher.start(3);
                    int endUrl = start + urlPath.length();
                    selects.add(new SelectionBean(urlPath, start, endUrl, 4));
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2d7dd2"));
                    ssb.setSpan(colorSpan, start, endUrl, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
            }
        }
        setText(ssb);
        BackgroundColorSpan span = new BackgroundColorSpan(Color.parseColor("#31000000"));
        int slop = ViewConfiguration.get(mContext).getScaledTouchSlop();
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
                        line = layout.getLineForVertical(getScrollY() + (int) event.getY());//计算出当前点击的第几行
                        int lineCount = layout.getLineCount();
                        index = layout.getOffsetForHorizontal(line, (int) event.getX());
                        int lastRight = (int) layout.getLineRight(line);
                        if (lastRight < downX) {  //文字最后为话题时，如果点击在最后一行话题之后，也会造成话题被选中效果
                            return false;
                        }
                        for (SelectionBean section : selects) {
                            if (index >= section.getStart() && index <= section.getEnd()) {
                                ssb.setSpan(span, section.getStart(), section.getEnd(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                downSection = section;
                                setText(ssb);
                                getParent().requestDisallowInterceptTouchEvent(true);//不允许父view拦截
                                return true;
                            }
                        }

                        return false;
                    case MotionEvent.ACTION_MOVE:
                        int indexMove = event.findPointerIndex(id);
                        int currentX = (int) event.getX(indexMove);
                        int currentY = (int) event.getY(indexMove);
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
                        ssb.removeSpan(span);
                        setText(ssb);
                        int upX = (int) event.getX(indexUp);
                        int upY = (int) event.getY(indexUp);
                        if (Math.abs(upX - downX) < slop && Math.abs(upY - downY) < slop) {
                            //TODO startActivity or whatever
                            if (downSection != null) {
                                //                                Toast.makeText(mContext, downSection.getName(), Toast.LENGTH_SHORT);
                                if (downSection.getType() == 4) {
                                    //                                    mContext.startActivity(new Intent(mContext, Act_WebPlayer.class).putExtra("path", downSection.getName()));
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

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        selects.clear();
    }
}
