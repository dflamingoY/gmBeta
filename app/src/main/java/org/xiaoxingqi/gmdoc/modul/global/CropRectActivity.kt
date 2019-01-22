package org.xiaoxingqi.gmdoc.modul.global

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.AsyncTask
import android.os.Environment
import android.text.TextUtils
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_crop_rect.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.http.UpQiNiuManager
import org.xiaoxingqi.gmdoc.entity.QINiuRespData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import java.io.File
import java.io.FileOutputStream

class CropRectActivity : BaseActivity<UserPresenter>() {
    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun qiniuToken(data: QINiuRespData) {
                sendPic(data)
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_crop_rect)
    }

    override fun initView() {

    }

    override fun initData() {
        val path = intent.getStringExtra("path")
        ImageLoader.getInstance().displayImage("file://$path", touchImg)
    }

    override fun initEvent() {
        tv_Cancel.setOnClickListener {
            finish()
        }
        tv_Commit.setOnClickListener {
            /**
             * 裁剪图片
             */
            save()
        }
    }

    private fun sendPic(data: QINiuRespData) {
        val manager = UpQiNiuManager(data.token, object : UpQiNiuManager.LoadStateListener {
            private var result: String? = null

            override fun success() {
                val intent = Intent()
                intent.putExtra("path", result)
                setResult(RESULT_OK, intent)
                showToast("上传成功")
                finish()
            }

            override fun fail() {
                Thread(Runnable { runOnUiThread { transLayout.showContent() } }).start()
            }

            override fun oneFinish(endTag: String, position: Int) {
                result = data.url + endTag
            }
        }, loadPath)

        manager.next()
    }

    private var loadPath: String? = null
    @SuppressLint("StaticFieldLeak")
    private fun save() {
        transLayout.showProgress()
        val task = object : AsyncTask<Void, Void, String?>() {
            override fun doInBackground(vararg params: Void?): String? {
                val bitmap = Bitmap.createBitmap(cropView.width, cropView.height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap!!)
                cropView.draw(canvas)
                val booFile = File(Environment.getExternalStorageDirectory(), IConstant.DOCNAME + "/" + IConstant.CACHE_NAME + "/" + System.currentTimeMillis() + ".png")
                if (!booFile.exists()) {
                    booFile.mkdirs()
                }
                val file = File(booFile.absolutePath + "/" + System.currentTimeMillis() + ".jpg")
                try {
                    if (bitmap != null) {
                        val fos = FileOutputStream(file)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                        fos.close()
                    }
                    bitmap.recycle()
                } catch (e: Exception) {
                    e.printStackTrace()
                    bitmap?.recycle()
                    return null
                }
                return file.absolutePath
            }

            override fun onPostExecute(result: String?) {
                if (TextUtils.isEmpty(result)) {
                    transLayout.showContent()
                } else {
                    loadPath = result
                    persent?.getQiNiuToken()
                }
            }
        }.execute()
    }
}