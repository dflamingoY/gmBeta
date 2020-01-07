package org.xiaoxingqi.gmdoc.modul.home

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_home_tab.*
import kotlinx.android.synthetic.main.layout_home_active_heard.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct
import org.xiaoxingqi.gmdoc.entity.ActiveBean
import org.xiaoxingqi.gmdoc.tools.FastBlur
import kotlin.math.abs

/**
 * 社区活动tab
 */
class HomeTabActivity : BaseAct() {
    private val fragments = Array(3) { HomeTabFragment() }
    private lateinit var data: ActiveBean
    override fun setContent() {
        setContent(R.layout.activity_home_tab)
    }

    override fun initView() {

    }

    override fun initData() {
        data = intent.getParcelableExtra("data")
        fragments.indices.forEach {
            fragments[it].arguments = Bundle().apply {
                putInt("type", it + 1)
                putInt("id", data.id)
            }
        }
        Glide.with(this)
                .asBitmap()
                .load(data.activity_pic)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        activeImg.post {
                            FastBlur().fastblur(resource, 60, iv_bg).let {
                                iv_bg.setImageBitmap(it)
                            }
                        }
                        activeImg.setImageBitmap(resource)
                    }
                })
        tv_ActiveDesc.text = data.activity_desc
        tv_activeName.text = data.activity_name
        tvToolbarName.text = data.activity_name

        viewPager.adapter = Adapter(supportFragmentManager)
    }

    override fun initEvent() {
        tabTitleLayout.setOnClick {
            viewPager.currentItem = tabTitleLayout.indexOfChild(it)
        }
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                tabTitleLayout.setCurrentSelect(position)
            }
        })
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val totalScrollRange: Int = appbar.totalScrollRange
            val scrimVisibleHeightTrigger: Int = collapsing_toolbar.scrimVisibleHeightTrigger
            tv_activeName.scaleX = 1 - abs(verticalOffset) * 1f / totalScrollRange
            tv_activeName.scaleY = 1 - abs(verticalOffset) * 1f / totalScrollRange
            if (abs(verticalOffset) > scrimVisibleHeightTrigger) {
                tvToolbarName.scaleX = (abs(verticalOffset) - scrimVisibleHeightTrigger) * 1f / (totalScrollRange - scrimVisibleHeightTrigger)
                tvToolbarName.scaleY = (abs(verticalOffset) - scrimVisibleHeightTrigger) * 1f / (totalScrollRange - scrimVisibleHeightTrigger)
            } else {
                tvToolbarName.scaleX = 0f
                tvToolbarName.scaleY = 0f
            }
        })
    }

    override fun request() {

    }

    private inner class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return 3
        }
    }


}