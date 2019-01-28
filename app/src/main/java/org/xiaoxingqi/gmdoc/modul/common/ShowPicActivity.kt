package org.xiaoxingqi.gmdoc.modul.common

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_showpic.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct
import org.xiaoxingqi.gmdoc.dialog.DialogSavePic
import org.xiaoxingqi.gmdoc.entity.BaseImgBean
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.FileUtils
import org.xiaoxingqi.gmdoc.tools.SPUtils
import org.xiaoxingqi.gmdoc.wegidt.ivView.PhotoView
import java.io.File
import java.util.ArrayList
import java.util.concurrent.ExecutionException

class ShowPicActivity : BaseAct() {
    private var mData: ArrayList<BaseImgBean> = ArrayList()
    override fun setContent() {
        setContent(R.layout.activity_showpic)
    }

    override fun initView() {

    }

    override fun initData() {
        var imgs = intent.getSerializableExtra("imgs")?.let { it as ArrayList<BaseImgBean> }
        val index = intent.getIntExtra("index", 0)
        intent.getStringExtra("path")?.let {
            mData.add(BaseImgBean(it))
        }
        imgs?.let { mData.addAll(it) }
        viewPager.adapter = ImgAdapter()
        viewPager.setCurrentItem(index, false)
    }

    override fun initEvent() {

    }

    override fun request() {

    }

    private inner class ImgAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return if (null == mData) {
                0
            } else
                mData?.size!!
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = View.inflate(this@ShowPicActivity, R.layout.layout_show_pic_spoiler, null) as RelativeLayout
            //            SubsamplingScaleImageView scaleImageView = view.findViewById(R.id.imageView);
            val progressBar = view.findViewById<ProgressBar>(R.id.progress)
            val ivPic = view.findViewById<PhotoView>(R.id.showIamgeView)
            ivPic.scaleType = ImageView.ScaleType.FIT_CENTER
            ivPic.enable()
            val ivHide = view.findViewById<View>(R.id.imgHide)
            val imgBean = mData!![position]
            if ("1" == imgBean.spoiler) {//剧透
                if (SPUtils.getBoolean(this@ShowPicActivity, IConstant.IS_SPOLIER, false)) {
                    ivHide.visibility = View.VISIBLE
                    ivPic.visibility = View.GONE
                } else {
                    ivHide.visibility = View.GONE
                }
            }
            ivHide.setOnClickListener { v ->
                ivHide.visibility = View.GONE
                ivPic.visibility = View.VISIBLE
            }
            var url = ""
            if (imgBean.url.endsWith("gif")) {
                url = imgBean.url
            } else {
                url = imgBean.url + "?imageMogr2/auto-orient/thumbnail/!50p"// thumbnail/!50p/ ?imageMogr2/thumbnail/720x/auto-orient
            }
            Glide.with(this@ShowPicActivity)
                    .load(url)
                    .error(R.drawable.img_empty_avatar_back)
                    .into(object : GlideDrawableImageViewTarget(ivPic) {
                        override fun onResourceReady(resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) {
                            super.onResourceReady(resource, animation)
                            progressBar.visibility = View.GONE
                        }

                        override fun onLoadStarted(placeholder: Drawable?) {
                            super.onLoadStarted(placeholder)
                            progressBar.visibility = View.VISIBLE
                        }

                        override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                            super.onLoadFailed(e, errorDrawable)
                            progressBar.visibility = View.GONE
                        }
                    })
            container.addView(view)
            ivPic.setOnLongClickListener {
                /**
                 * 保存图片
                 * */
                DialogSavePic(this@ShowPicActivity).setOnClickListener(View.OnClickListener {


                }).show()
                false
            }
            ivPic.setOnClickListener {
                finish()
            }
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }

    /**
     * 保存
     */
    private fun save(savePath: String) {
        var path: String = savePath
        if (savePath.contains("?")) {
            path = savePath.substring(0, savePath.lastIndexOf("?"))
        }
        var suffix = path.substring(path.lastIndexOf("/") + 1)
        if (!suffix.contains(".jpg") || !suffix.contains(".png") || !suffix.contains(".gif")) {
            suffix = "$suffix.jpg"
        }
        val file = File(Environment.getExternalStorageDirectory(), IConstant.DOCNAME + "/" + IConstant.DOWNLOAD + "/" + suffix)
        if (file.exists()) {
            showToast("图片已存在")
            return
        }
        object : AsyncTask<Void, Void, File>() {

            override fun doInBackground(vararg voids: Void): File? {
                try {
                    return Glide.with(this@ShowPicActivity)
                            .load(path)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: ExecutionException) {
                    e.printStackTrace()
                }

                return null
            }

            override fun onPostExecute(s: File?) {
                if (s == null) {
                    showToast("图片保存失败")
                } else {
                    val bootfile = File(Environment.getExternalStorageDirectory(), IConstant.DOCNAME + "/" + IConstant.DOWNLOAD)
                    if (!bootfile.exists()) {
                        bootfile.mkdirs()
                    }
                    val file = File(bootfile.absolutePath + "/" + suffix)
                    FileUtils.copyFile(s, file)
                    //在手机相册中显示刚拍摄的图片
                    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    val contentUri = Uri.fromFile(file)
                    mediaScanIntent.data = contentUri
                    sendBroadcast(mediaScanIntent)
                    showToast("保存成功")
                }
            }
        }.execute()
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.act_exit_scale)
    }
}