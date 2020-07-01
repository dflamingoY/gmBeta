package org.xiaoxingqi.gmdoc.modul.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_more_article.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct

class MoreArticleActivity : BaseAct() {
    private val fragments = Array(2) {
        RecommendArtFragment().apply {
            arguments = Bundle().apply {
                putInt("type", it + 2)
            }
        }
    }

    override fun setContent() {
        setContent(R.layout.activity_more_article)
    }

    override fun initView() {
        initActionBar(toolbar)
    }

    override fun initData() {
        viewPager.adapter = ArtAdapter(supportFragmentManager)

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

    }

    override fun request() {

    }

    private inner class ArtAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return 2
        }
    }

}