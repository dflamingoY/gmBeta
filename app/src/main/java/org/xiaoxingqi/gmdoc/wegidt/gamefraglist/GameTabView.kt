package org.xiaoxingqi.gmdoc.wegidt.gamefraglist

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import android.util.AttributeSet
import android.util.Log
import com.nineoldandroids.animation.ObjectAnimator
import kotlinx.android.synthetic.main.layout_frag_game_tab.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.wegidt.BaseLayout

class GameTabView : BaseLayout {

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
    }

    private var isFirst = false
    private var current = 0
    private val mData: MutableList<GamePlatformData.PlatformListData> by lazy {
        ArrayList<GamePlatformData.PlatformListData>()
    }
    private lateinit var adapter: QuickAdapter<GamePlatformData.PlatformListData>
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
        gameClickTabView.setOnClickListener {
            when (it.id) {
                R.id.linearTab1 -> {
                    if (it.isSelected) {//
                        ObjectAnimator.ofFloat(relativePlat, "translationY", -AppTools.getWindowsHeight(context).toFloat()).setDuration(320).start()
                    } else {//hide
                        ObjectAnimator.ofFloat(relativePlat, "translationY", 0f).setDuration(320).start()
                    }
                }
                R.id.linearTab2 -> {

                }
                R.id.linearTab3 -> {

                }
                R.id.linearTab4 -> {

                }
            }
        }
        adapter.setOnItemClickListener { view, position ->
            gameClickTabView.setTabName(mData[current].name, mData[current].version[0].name, "", "")
        }
    }

    fun setCurrentPlat(current: Int) {
        this.current = current
        gameClickTabView.setTabName(mData[current].name, mData[current].version[0].name, "", "")
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_frag_game_tab
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!isFirst) {
            isFirst = true
            ObjectAnimator.ofFloat(relativePlat, "translationY", -AppTools.getWindowsHeight(context).toFloat()).setDuration(0).start()
            ObjectAnimator.ofFloat(recyclerOther, "translationY", -AppTools.getWindowsHeight(context).toFloat()).setDuration(0).start()
        }
    }

    /**
     * 设置游戏平台的数据
     */
    fun setPlatData(data: List<GamePlatformData.PlatformListData>) {
        mData.addAll(data)
        adapter.notifyDataSetChanged()
    }
}