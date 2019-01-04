package org.xiaoxingqi.gmdoc.modul.home

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import com.bumptech.glide.BitmapRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.backgroundDrawable
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import org.xiaoxingqi.gmdoc.impl.home.UserInfoCallBack
import org.xiaoxingqi.gmdoc.parsent.home.HomeUserInfoPersent

/**
 * 用户个人中心界面
 */
class UserHomeActivity : BaseActivity<HomeUserInfoPersent>() {

    override fun createPresent(): HomeUserInfoPersent {
        return HomeUserInfoPersent(this, object : UserInfoCallBack() {
            override fun userInfo(info: UserInfoData?) {
                Glide.with(this@UserHomeActivity)
                        .load(info!!.data.top_image)
                        .asBitmap()
                        .into(object : BitmapImageViewTarget(iv_User_Bg) {
                            override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                                super.onResourceReady(resource, glideAnimation)
                                /**
                                 * 切割bitmap
                                 */
                                collapsing_toolbar.contentScrim = BitmapDrawable(resources, resource)
//                                val canvas = Canvas()
//                                canvas.drawBitmap(resource, )

                            }
                        })

            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_user_info)
    }

    override fun initView() {

    }

    override fun initData() {
        var userId = intent.getStringExtra("userId")
        if (TextUtils.isEmpty(userId)) {
            userId = ""
        }
        persent?.getUserInfo(userId)
    }

    override fun initEvent() {

    }

    override fun request() {


    }
}