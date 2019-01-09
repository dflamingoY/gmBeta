package org.xiaoxingqi.gmdoc.wegidt.emoji;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.xiaoxingqi.gmdoc.R;
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper;
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter;
import org.xiaoxingqi.gmdoc.entity.emoji.EmojiArrayData;
import org.xiaoxingqi.gmdoc.entity.emoji.EmojiEntity;
import org.xiaoxingqi.gmdoc.tools.FaceData;
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/12/27.
 * 表情
 */

public class EmojiLayoutView extends BaseLayout {
    private Context mContext;
    private ViewPager mViewPager;
    private List<EmojiArrayData> mArrayData;
    private int gifCont = 0;
    private EmojindicatorView mIndexContainer;
    private TextView mViewEmoji;
    private TextView mViewDefault;

    public EmojiLayoutView(Context context) {
        this(context, null, 0);
    }

    public EmojiLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewPager);
        mIndexContainer = findViewById(R.id.linear_Index);
        initData();
        mViewPager.setAdapter(new EmojiAdapter());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position + 1 <= gifCont) {//gif 表情
                    mIndexContainer.setCurrent(gifCont, position + 1);
                    mViewDefault.setBackgroundColor(Color.parseColor("#b3b3b3"));
                    mViewDefault.setTextColor(getResources().getColor(R.color.color_white));
                    mViewEmoji.setBackgroundColor(getResources().getColor(R.color.color_white));
                    mViewEmoji.setTextColor(getResources().getColor(R.color.color_666666));
                    mViewDefault.setSelected(true);
                    mViewEmoji.setSelected(false);
                } else {//emoji 表情
                    mIndexContainer.setCurrent(mViewPager.getAdapter().getCount() - gifCont, position + 1 - gifCont);
                    mViewEmoji.setBackgroundColor(Color.parseColor("#b3b3b3"));
                    mViewEmoji.setTextColor(getResources().getColor(R.color.color_white));
                    mViewDefault.setBackgroundColor(getResources().getColor(R.color.color_white));
                    mViewDefault.setTextColor(getResources().getColor(R.color.color_666666));
                    mViewDefault.setSelected(false);
                    mViewEmoji.setSelected(true);
                }
            }
        });
        mIndexContainer.setCurrent(gifCont, 1);
        mViewEmoji = findViewById(R.id.tv_Emoji);
        mViewDefault = findViewById(R.id.tv_Default);
        mViewDefault.setSelected(true);
        mViewDefault.setOnClickListener(v -> {
            if (mViewPager.getAdapter().getCount() > gifCont) {
                mViewPager.setCurrentItem(0, false);
            }
            if (!mViewDefault.isSelected()) {
                mViewDefault.setBackgroundColor(Color.parseColor("#b3b3b3"));
                mViewDefault.setTextColor(getResources().getColor(R.color.color_white));
                mViewEmoji.setBackgroundColor(getResources().getColor(R.color.color_white));
                mViewEmoji.setTextColor(getResources().getColor(R.color.color_666666));
                mViewDefault.setSelected(true);
                mViewEmoji.setSelected(false);
            }
        });
        mViewEmoji.setOnClickListener(v -> {
            if (mViewPager.getAdapter().getCount() > gifCont) {
                mViewPager.setCurrentItem(gifCont, false);
            }
            if (!mViewEmoji.isSelected()) {
                mViewEmoji.setBackgroundColor(Color.parseColor("#b3b3b3"));
                mViewEmoji.setTextColor(getResources().getColor(R.color.color_white));
                mViewDefault.setBackgroundColor(getResources().getColor(R.color.color_white));
                mViewDefault.setTextColor(getResources().getColor(R.color.color_666666));
                mViewDefault.setSelected(false);
                mViewEmoji.setSelected(true);
            }
        });
    }

    /**
     * 分页展示
     */
    private void initData() {
        mArrayData = new ArrayList<>();
        int page = FaceData.gif.length % 20 == 0 ? FaceData.gif.length / 20 : FaceData.gif.length / 20 + 1;
        /**
         * 动态gif
         */
        for (int a = 0; a < page; a++) {
            EmojiArrayData data = new EmojiArrayData();
            data.getData().clear();
            if (a != page - 1) {
                for (int i = a * 20; i < (a + 1) * 20; i++) {
                    data.getData().add(new EmojiEntity(FaceData.gif[i], EmojiEntity.EmojiType.GIF));
                }
            } else {
                for (int i = 20 * a; i < FaceData.gif.length; i++) {
                    data.getData().add(new EmojiEntity(FaceData.gif[i], EmojiEntity.EmojiType.GIF));
                }
            }
            data.getData().add(new EmojiEntity("del", EmojiEntity.EmojiType.DEL));
            mArrayData.add(data);
        }
        gifCont = page;
        int emojiPage = FaceData.faceId.length % 20 == 0 ? FaceData.faceId.length / 20 : FaceData.faceId.length / 20 + 1;
        for (int a = 0; a < emojiPage; a++) {
            EmojiArrayData data = new EmojiArrayData();
            data.getData().clear();
            if (a != emojiPage - 1) {
                for (int i = a * 20; i < (a + 1) * 20; i++) {
                    data.getData().add(new EmojiEntity(FaceData.faceId[i], EmojiEntity.EmojiType.EMOJI));
                }
            } else {
                for (int i = 20 * a; i < FaceData.faceId.length; i++) {
                    data.getData().add(new EmojiEntity(FaceData.faceId[i], EmojiEntity.EmojiType.EMOJI));
                }
            }
            data.getData().add(new EmojiEntity("del", EmojiEntity.EmojiType.DEL));
            mArrayData.add(data);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.emoji_layout;
    }


    private class EmojiAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(mContext).inflate(R.layout.heard_recycler, null);
            EmojiArrayData data = mArrayData.get(position);
            QuickAdapter adapter = new QuickAdapter(mContext, R.layout.item_emoji_img, data.getData()) {
                @Override
                protected void convert(BaseAdapterHelper helper, Object item) {
                    EmojiEntity entity = (EmojiEntity) item;
                    if (!TextUtils.isEmpty(entity.getIconName())) {
                        if ("del".equals(entity.getIconName())) {
                            helper.getImageView(R.id.iv_emoji).setImageResource(R.mipmap.edit_delete);
                        } else {
                            Glide.with(mContext)
                                    .load("file:///android_asset/" + entity.getIconName())
                                    .into(new GlideDrawableImageViewTarget(helper.getImageView(R.id.iv_emoji), 0));
                        }
                    }
                }
            };
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 7));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener((view, position1) -> {
                if (mEmojiClickListener != null) {
                    EmojiEntity entity = (EmojiEntity) adapter.getItem(position1);
                    if (mEmojiClickListener != null) {
                        mEmojiClickListener.click(entity);
                    }
                }
            });
            container.addView(recyclerView);
            return recyclerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            if (mArrayData != null)
                return mArrayData.size();
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private OnEmojiClickListener mEmojiClickListener;

    public interface OnEmojiClickListener {
        void click(EmojiEntity entity);
    }

    public void setOnEmojiClicklistener(OnEmojiClickListener listener) {
        mEmojiClickListener = listener;
    }

}
