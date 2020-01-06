package org.xiaoxingqi.gmdoc.modul.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_more_article.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct

class MoreArticleActivity : BaseAct() {
    private val fragments = Array(2) {
        RecommendArtFragment().apply {
            arguments = Bundle().apply {
                putInt("type", it + 1)
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

    }

    override fun request() {

    }

    private inner class ArtAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return 2
        }
    }

}