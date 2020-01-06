package org.xiaoxingqi.gmdoc.wegidt.gamefraglist

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.nineoldandroids.animation.Animator
import com.nineoldandroids.animation.AnimatorListenerAdapter
import com.nineoldandroids.animation.ObjectAnimator
import kotlinx.android.synthetic.main.layout_frag_game_tab.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData
import org.xiaoxingqi.gmdoc.listener.OnGameTabClickListener
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

/**
 *   已发售
 *   即将发售
 *
 *   默认排序
 *   发布时间
 *   社区评分
 *   关注均分
 */
class GameTabView : BaseLayout {
    enum class ClickType {
        Version,
        Sell,
        Sort
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
    }

    private var isFirst = false
    private var current = 0
    private val mData: MutableList<GamePlatformData.PlatformListData> by lazy {
        ArrayList<GamePlatformData.PlatformListData>()
    }
    private lateinit var adapter: QuickAdapter<GamePlatformData.PlatformListData>
    private lateinit var sortOtherAdapter: QuickAdapter<GamePlatformData.PlatformData>
    private val sortData by lazy {
        ArrayList<GamePlatformData.PlatformData>()
    }
    private val tempData = arrayListOf(GamePlatformData.PlatformData(0, "已发售", true), GamePlatformData.PlatformData(1, "即将发售", false)
            , GamePlatformData.PlatformData(0, "默认排序", true), GamePlatformData.PlatformData(1, "发布时间", false)
            , GamePlatformData.PlatformData(2, "社区评分", false), GamePlatformData.PlatformData(4, "关注均分", false))
    private var clickType = ClickType.Version
    private var onTabClickListener: OnGameTabClickListener? = null
    private fun initView() {
        adapter = object : QuickAdapter<GamePlatformData.PlatformListData>(context, R.layout.item_text, mData) {
            override fun convert(helper: BaseAdapterHelper?, item: GamePlatformData.PlatformListData?) {
                if (helper!!.itemView.tag as Int == current) {
                    helper.getTextView(R.id.tv_SortTypeName).setTextColor(resources.getColor(R.color.color_shallow_yellow))
                } else {
                    helper.getTextView(R.id.tv_SortTypeName).setTextColor(Color.parseColor("#6d6d6d"))
                }
                helper.getTextView(R.id.tv_SortTypeName).text = item!!.name
            }
        }
        recyclerSort.layoutManager = GridLayoutManager(context, 4)
        recyclerSort.adapter = adapter
        sortOtherAdapter = object : QuickAdapter<GamePlatformData.PlatformData>(context, R.layout.item_horination_text, sortData) {
            override fun convert(helper: BaseAdapterHelper?, item: GamePlatformData.PlatformData?) {
                helper!!.getTextView(R.id.tv_SelectedName).setTextColor(if (item!!.isSelected) resources.getColor(R.color.color_shallow_yellow) else Color.parseColor("#444444"))
                helper.getView(R.id.iv_Selected).visibility = if (item.isSelected) VISIBLE else GONE
                helper.getTextView(R.id.tv_SelectedName).text = item.name
            }
        }
        recyclerOther.layoutManager = LinearLayoutManager(context)
        recyclerOther.adapter = sortOtherAdapter
        gameClickTabView.setOnClickListener {
            when (it.id) {
                R.id.linearTab1 -> {
                    if (it.isSelected) {//hide
                        anim(-AppTools.getWindowsHeight(context).toFloat(), 320, 0)
                    } else {//show
                        if (recyclerOther.y >= 0) {
                            closeMenu(recyclerOther, 120, 0)
                            anim(0f, 320, 120)
                        } else {
                            anim(0f, 320, 120)
                        }
                    }
                }
                R.id.linearTab2 -> {
                    if (it.isSelected) {
                        closeMenu(recyclerOther, 220, 0)
                    } else {
                        clickType = ClickType.Version
                        sortData.clear()
                        sortData.addAll(mData[current].version)
                        sortOtherAdapter.notifyDataSetChanged()
                        if (relativePlat.y >= 0) {
                            anim(-AppTools.getWindowsHeight(context).toFloat(), 120, 0)
                        }
                        if (recyclerOther.y >= 0) {
                            closeMenu(recyclerOther, 120, 0)
                            showMenu(recyclerOther, 220, 120)
                        } else {
                            showMenu(recyclerOther, 220, 0)
                        }
                    }
                }
                R.id.linearTab3 -> {
                    if (it.isSelected) {
                        closeMenu(recyclerOther, 220, 0)
                    } else {
                        clickType = ClickType.Sell
                        sortData.clear()
                        sortData.addAll(tempData.subList(0, 2))
                        sortOtherAdapter.notifyDataSetChanged()
                        if (relativePlat.y >= 0) {
                            anim(-AppTools.getWindowsHeight(context).toFloat(), 120, 0)
                        }
                        if (recyclerOther.y >= 0) {
                            closeMenu(recyclerOther, 120, 0)
                            showMenu(recyclerOther, 220, 120)
                        } else {
                            showMenu(recyclerOther, 220, 0)
                        }
                    }
                }
                R.id.linearTab4 -> {
                    if (it.isSelected) {
                        closeMenu(recyclerOther, 220, 0)
                    } else {
                        clickType = ClickType.Sort
                        sortData.clear()
                        sortData.addAll(tempData.subList(2, 6))
                        if (relativePlat.y >= 0) {
                            anim(-AppTools.getWindowsHeight(context).toFloat(), 120, 0)
                        }
                        if (recyclerOther.y >= 0) {
                            closeMenu(recyclerOther, 120, 0)
                            showMenu(recyclerOther, 220, 120)
                        } else {
                            showMenu(recyclerOther, 220, 0)
                        }
                    }
                }
            }
        }
        adapter.setOnItemClickListener { view, position ->
            current = position
            initdata()
            ObjectAnimator.ofFloat(relativePlat, "translationY", -AppTools.getWindowsHeight(context).toFloat()).setDuration(320).start()
            adapter.notifyDataSetChanged()
            gameClickTabView.setTabName(mData[current].name, mData[current].version[0].name, "", "")
            onTabClickListener?.onplatClick(mData[current].name, position)
        }
        sortOtherAdapter.setOnItemClickListener { view, position ->
            closeMenu(recyclerOther, 220, 0)
            when (clickType) {
                ClickType.Version -> {
                    gameClickTabView.setTabName("", sortData[position].name, "", "")
                    onTabClickListener?.versionClick(sortData[position].id)
                }
                ClickType.Sell -> {
                    gameClickTabView.setTabName("", "", sortData[position].name, "")
                    onTabClickListener?.sellClick(sortData[position].id)
                }
                ClickType.Sort -> {
                    gameClickTabView.setTabName("", "", "", sortData[position].name)
                    onTabClickListener?.releasClick(sortData[position].id)
                }
            }
        }
    }

    /**
     * 初始化 版本 是否发售  排序的内容
     */
    private fun initdata() {
        try {
            for (index in tempData.indices) {
                tempData[index].isSelected = index == 0 || index == 2
            }
        } catch (e: Exception) {
        }
    }

    fun setCurrentPlat(current: Int) {
        this.current = current
        gameClickTabView.setTabName(mData[current].name, mData[current].version[0].name, "", "")
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_frag_game_tab
    }

    private fun anim(animValue: Float, duration: Long, delay: Long) {
        val animator = ObjectAnimator.ofFloat(relativePlat, "translationY", animValue).setDuration(duration)
        animator.interpolator = LinearInterpolator()
        animator.startDelay = delay
        animator.start()
    }

    /**
     * 关闭menu
     */
    private fun closeMenu(view: View, duration: Long, delay: Long) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", -AppTools.dp2px(context, 240).toFloat()).setDuration(duration)
        animator.interpolator = LinearInterpolator()
        animator.startDelay = delay
        animator.start()
    }

    /**
     * 显示其他分类的Recycler
     */
    private fun showMenu(view: View, duration: Long, delay: Long) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", 0f).setDuration(duration)
        animator.interpolator = LinearInterpolator()
        animator.startDelay = delay
        animator.start()

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                sortOtherAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!isFirst) {
            isFirst = true
            ObjectAnimator.ofFloat(relativePlat, "translationY", -AppTools.getWindowsHeight(context).toFloat()).setDuration(0).start()
            ObjectAnimator.ofFloat(recyclerOther, "translationY", -AppTools.dp2px(context, 240).toFloat()).setDuration(0).start()
        }
    }


    /**
     * 设置游戏平台的数据
     */
    fun setPlatData(data: List<GamePlatformData.PlatformListData>) {
        mData.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun setOnTabListener(onTabListener: OnGameTabClickListener) {
        onTabClickListener = onTabListener
    }

}