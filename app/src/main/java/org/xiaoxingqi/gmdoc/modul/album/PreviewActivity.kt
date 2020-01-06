package org.xiaoxingqi.gmdoc.modul.album

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.provider.MediaStore
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_preview.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct
import org.xiaoxingqi.gmdoc.wegidt.ivView.PhotoView
import java.io.File
import java.io.FilenameFilter
import java.util.ArrayList

/**
 * 预览图片的Activity
 */
class PreviewActivity : BaseAct() {

    private val mData by lazy { ArrayList<String>() }
    private var current = 0
    private var selected: ArrayList<String>? = null
    private var allowCount = 0

    override fun setContent() {
        setContentView(R.layout.activity_preview)
    }

    override fun initView() {

    }

    override fun initData() {
        allowCount = intent.getIntExtra("allow", 0)
        (intent.getSerializableExtra("selected") as ArrayList<String>)?.let {
            selected = it
        }
        val filePath = intent.getStringExtra("parentFile")
        current = intent.getIntExtra("current", -2)
        if (current != -2) {
            queryPhotoByFile(filePath)
        } else {
            (intent.getSerializableExtra("data") as ArrayList<String>)?.let {
                mData.addAll(it)
                viewPager.adapter = ImgAdapter()
                transLayout.showContent()
            }
        }
        if (null != selected) {
            if (selected?.size!! > 0) {
                tv_Count.visibility = View.VISIBLE
                tv_Count.text = "${selected!!.size}"
            }
        }
    }

    override fun initEvent() {
        viewBack.setOnClickListener {
            finish()
        }
        check_img.setOnStateChangeListener {
            if (it) {//选中状态
                if (selected?.size!! >= allowCount) {
                    showToast("最多可选${allowCount}张照片")
                    check_img.isSelected = false
                } else {
                    if (!selected?.contains(mData[viewPager.currentItem])!!) {
                        selected?.add(mData[viewPager.currentItem])
                    }
                }
            } else {//取消状态
                if (selected?.contains(mData[viewPager.currentItem])!!) {
                    selected?.remove(mData[viewPager.currentItem])
                }
            }
            selected?.let {
                tv_Count.visibility = if (it.size > 0) View.VISIBLE else View.GONE
            }
            tv_Count.text = "${selected?.size}"
        }
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                selected?.let {
                    check_img.isSelected = it.contains(mData[position])
                }
            }
        })
        tv_Commit.setOnClickListener {
            /**
             * 提交選擇
             */
            if (selected?.size == 0) {
                selected?.add(mData[viewPager.currentItem])
            }
            val intent = Intent()
            intent.putExtra("select", selected)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        viewBack.setOnClickListener {
            val intent = Intent()
            intent.putExtra("select", selected)
            setResult(RESULT_CANCELED, intent)
            finish()
        }
    }

    @SuppressLint("StaticFieldLeak")
    fun queryPhotoByFile(path: String?) {
        object : AsyncTask<Void, Void, ArrayList<String>>() {
            override fun doInBackground(vararg voids: Void): ArrayList<String>? {
                val arrays = ArrayList<String>()
                if (TextUtils.isEmpty(path)) {
                    val imageUrl = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    val resolver = this@PreviewActivity.contentResolver
                    val cursor = resolver.query(imageUrl, null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                            arrayOf("image/jpeg", "image/png", "image/gif"), MediaStore.Images.Media.DATE_MODIFIED)
                    if (null != cursor && cursor.count > 0) {
                        while (cursor.moveToNext()) {
                            val path = cursor.getString(cursor
                                    .getColumnIndex(MediaStore.Images.Media.DATA))
                            arrays.add(0, path)
                        }
                    }
                    cursor!!.close()
                } else {
                    val file = File(path)
                    if (file.isDirectory) {
                        val filter = FilenameFilter { file, s -> s.endsWith("png") || s.endsWith("jpg") || s.endsWith("jpeg") || s.endsWith("gif") }
                        val list = file.list(filter)
                        for (item in list) {
                            arrays.add("$path/$item")
                        }
                    }
                }
                return arrays
            }

            override fun onPostExecute(strings: ArrayList<String>) {
                super.onPostExecute(strings)
                mData.addAll(strings)
                viewPager.adapter = ImgAdapter()
                viewPager.setCurrentItem(if (current == -1) current + 1 else current, false)
                transLayout.showContent()
            }
        }.execute()
    }

    override fun request() {

    }

    private inner class ImgAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return mData.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(this@PreviewActivity).inflate(R.layout.layout_show_img_vedio, null)
            val lp = ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            val imageView = view.findViewById<PhotoView>(R.id.touchImg)
            imageView.enable()
            Glide.with(this@PreviewActivity)
                    .load(mData[position])
                    .into(imageView)
            container.addView(view, lp)
            imageView.setOnClickListener { v -> doAnimn() }
            return view
        }
    }

    private var isShow = false
    private var isRunning = false

    private fun doAnimn() {
        if (isRunning)
            return
        if (!isShow) {
            ObjectAnimator.ofFloat(topMenu, "translationY", -topMenu.measuredHeight.toFloat()).setDuration(220).start()
            val animator = ObjectAnimator.ofFloat(bottom_Menu, "translationY", topMenu.measuredHeight.toFloat()).setDuration(220)
            animator.addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    isShow = true
                    isRunning = false
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    isRunning = true

                }
            })
            animator.start()
        } else {
            ObjectAnimator.ofFloat(topMenu, "translationY", 0f).setDuration(220).start()
            val animator = ObjectAnimator.ofFloat(bottom_Menu, "translationY", 0f).setDuration(220)
            animator.addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    isShow = false
                    isRunning = false
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    isRunning = true

                }
            })
            animator.start()
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("select", selected)
        setResult(RESULT_CANCELED, intent)
        super.onBackPressed()
    }
}