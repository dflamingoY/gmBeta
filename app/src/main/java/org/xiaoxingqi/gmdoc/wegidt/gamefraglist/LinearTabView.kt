package org.xiaoxingqi.gmdoc.wegidt.gamefraglist

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.nineoldandroids.animation.*
import kotlinx.android.synthetic.main.linaer_title_sort.view.*
import org.jetbrains.anko.textColor
import org.xiaoxingqi.gmdoc.R

class LinearTabView : LinearLayout {

    constructor(context: Context, atts: AttributeSet) : super(context, atts) {
        initView()
    }

    private fun initView() {
        gravity = Gravity.CENTER
        val view = LayoutInflater.from(context).inflate(R.layout.linaer_title_sort, null)
        addView(view)
    }


    fun setName(name: String) {
        tv_Name.text = name
    }

    fun clearSelected() {
        isSelected = false
        val rotation = ObjectAnimator.ofFloat(iv_Arrow, "rotation", 0f)
        rotation.duration = 320
        rotation.start()
        rotation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                iv_Arrow.setImageResource(R.mipmap.btn_arrow_bottom_grey)
            }
        })
        val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), Color.parseColor("#FFff7a31"), Color.parseColor("#ff888888"))
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Int
            tv_Name.textColor = value
        }
        valueAnimator.duration = 320
        valueAnimator.start()
    }

    fun setSelected() {
        isSelected = true
        val rotation = ObjectAnimator.ofFloat(iv_Arrow, "rotation", 0f, 180f)
        rotation.duration = 320
        rotation.start()
        rotation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                iv_Arrow.setImageResource(R.mipmap.btn_arrow_top_orange_copy)
            }
        })
        val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), Color.parseColor("#ff888888"), Color.parseColor("#FFff7a31"))
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Int
            tv_Name.textColor = value
        }
        valueAnimator.duration = 320
        valueAnimator.start()

    }


}