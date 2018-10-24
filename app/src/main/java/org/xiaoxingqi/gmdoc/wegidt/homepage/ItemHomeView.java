//package org.xiaoxingqi.gmdoc.wegidt.homepage;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import com.bumptech.glide.Glide;
//
//import org.xiaoxingqi.gmdoc.R;
//import org.xiaoxingqi.gmdoc.entity.BaseHomeBean;
//import org.xiaoxingqi.gmdoc.wegidt.BaseLayout;
//
///**
// * Created by yzm on 2017/11/2.
// */
//
//public class ItemHomeView extends BaseLayout {
//    private Context mContext;
//
//    public ItemHomeView(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public ItemHomeView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        mContext = context;
//        initView();
//    }
//
//    private void initView() {
//        setBackgroundResource(R.drawable.game_click_drawable);
//
//
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.item_home_game_layout;
//    }
//
//    public void setData(BaseHomeBean bean) {
//        String url = "";
//        if (bean.getCover().contains("?")) {
//            url = bean.getCover() + "&imageMogr2/auto-orient/thumbnail/!200x200r";
//        } else {
//            url = bean.getCover() + "?imageMogr2/auto-orient/thumbnail/!200x200r";
//        }
//
//        mTvGameName.setText(bean.getGame_name());
//        mScoreView.setScore(bean.getScore());
//        mTvDesc.setText(bean.getIntroduce());
//        mTvTime.setText(bean.getSale_time());
//        mTvVersion.setText(bean.getVersion());
//        if (!TextUtils.isEmpty(bean.getExtra()))
//            mTvExtre.setText(bean.getExtra());
//    }
//
//    public void setName(String name) {
//        try {
//            if ("ios".equalsIgnoreCase(name)) {
//                mLinearTime.setVisibility(GONE);
//            } else {
//                mLinearTime.setVisibility(VISIBLE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
