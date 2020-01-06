package org.xiaoxingqi.gmdoc.modul.lifeCircle

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.frag_lifecircle.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseFrag
import org.xiaoxingqi.gmdoc.entity.GroupData
import org.xiaoxingqi.gmdoc.impl.home.LifeFragCallback
import org.xiaoxingqi.gmdoc.presenter.home.LifeFragPresenter
import org.xiaoxingqi.gmdoc.wegidt.BaseTabTitleLayout

class LifCircleFragment : BaseFrag<LifeFragPresenter>() {

    private val chooseType = arrayOf("", "4", "1", "2", "3")
    private val fragments = arrayOfNulls<HomeCircleFragment>(5)

    private lateinit var viewPager: ViewPager
    private lateinit var tabTitleLayout: BaseTabTitleLayout

    override fun createPresent(): LifeFragPresenter {
        return LifeFragPresenter(activity!!, object : LifeFragCallback {
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
        presenter?.queryGroup()
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