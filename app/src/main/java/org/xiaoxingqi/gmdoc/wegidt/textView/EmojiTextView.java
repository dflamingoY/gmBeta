package org.xiaoxingqi.gmdoc.wegidt.textView;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;


import org.xiaoxingqi.gmdoc.tools.AppConfig;
import org.xiaoxingqi.gmdoc.tools.AppTools;
import org.xiaoxingqi.gmdoc.tools.FaceData;
import org.xiaoxingqi.gmdoc.wegidt.imagespan.VerticalImageSpan;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yzm on 2017/12/5.
 * <p>
 * 显示静态gif
 */

public class EmojiTextView extends AppCompatTextView {
    private Context mContext;

    public EmojiTextView(Context context) {
        this(context, null, 0);
    }

    public EmojiTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {

    }

    public void setDataText(AssetManager manager, String text) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        Pattern pattern = Pattern.compile(AppConfig.EMOJI);
        Matcher matcher = pattern.matcher(ssb);
        while (matcher.find()) {
            String group = matcher.group();
            if (group != null) {
                String faceId = null;
                int start = matcher.start();
                int end = start + group.length();
                if ((faceId = FaceData.gifFaceInfo.get(group)) != null) {
                    try {
                        InputStream open = manager.open(faceId);
                        BitmapDrawable drawable = new BitmapDrawable(open);
                        drawable.setBounds(8, 0, AppTools.dp2px(mContext, 15) + 8, AppTools.dp2px(mContext, 15));
                        ssb.setSpan(new VerticalImageSpan(drawable), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if ((faceId = FaceData.staticFaceInfo.get(group)) != null) {
                    try {
                        InputStream open = manager.open(faceId);
                        BitmapDrawable drawable = new BitmapDrawable(open);
                        drawable.setBounds(8, 0, AppTools.dp2px(mContext, 15) + 8, AppTools.dp2px(mContext, 15));
                        ssb.setSpan(new VerticalImageSpan(drawable), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        setText(ssb);
    }

}
