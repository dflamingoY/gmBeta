package org.xiaoxingqi.gmdoc.modul.lifeCircle

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.frag_lifecircle.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.entity.GroupData
import org.xiaoxingqi.gmdoc.impl.home.LifeFragCallback
import org.xiaoxingqi.gmdoc.parsent.home.LifeFragPersenter
import org.xiaoxingqi.gmdoc.wegidt.BaseTabTitleLayout

class LifCircleFragment : BaseFrag<LifeFragPersenter>() {

    private val chooseType = arrayOf("", "4", "1", "2", "3")
    private val fragments = arrayOfNulls<HomeCircleFragment>(5)

    private lateinit var viewPager: ViewPager
    private lateinit var tabTitleLayout: BaseTabTitleLayout

    override fun createPresent(): LifeFragPersenter {
        return LifeFragPersenter(activity!!, object : LifeFragCallback {
            override fun getGroupData(data: GroupData) {

            }
        })
    }

    override fun getlyoutId(): Int {
        return R.layout.frag_lifecircle
    }

    override fun initView(view: View?) {
        viewPager = view!!.lazzyPager
        tabTitleLayout = view.tabTitleLayout
    }

    override fun initData() {
        for (tag in chooseType.indices) {
            val frag = HomeCircleFragment()
            val bundle = Bundle()
            bundle.putString("chooseType", chooseType[tag])
            bundle.putString("groupId", "0")
            frag.arguments = bundle
            fragments[tag] = frag
        }
        viewPager.adapter = DynamicAdapter(childFragmentManager)
        persent?.queryGroup()
    }

    override fun bindEvent() {
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabTitleLayout.setCurrentSelect(position)
            }
        })
        tabTitleLayout.setOnClick {
            viewPager.setCurrentItem(tabTitleLayout.indexOfChild(it), false)
        }
    }

    override fun request(flag: Int) {

    }

    private inner class DynamicAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]!!
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}