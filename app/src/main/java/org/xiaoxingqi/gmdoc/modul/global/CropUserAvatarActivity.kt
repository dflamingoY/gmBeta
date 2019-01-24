package org.xiaoxingqi.gmdoc.modul.global

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.AsyncTask
import android.os.Environment
import android.text.TextUtils
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_crop_avatar.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.http.UpQiNiuManager
import org.xiaoxingqi.gmdoc.entity.QINiuRespData
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.impl.home.UserCallback
import org.xiaoxingqi.gmdoc.presenter.home.UserPresenter
import java.io.File
import java.io.FileOutputStream

/**
 * 处理用户的头像
 */
class CropUserAvatarActivity : BaseActivity<UserPresenter>() {

    private var resultPath: String? = null

    override fun createPresent(): UserPresenter {
        return UserPresenter(this, object : UserCallback() {
            override fun qiniuToken(data: QINiuRespData) {
                val manager = UpQiNiuManager(data.token, object : UpQiNiuManager.LoadStateListener {
                    private var result: String? = null

                    override fun success() {
                        try {
                            File(resultPath).delete()
                        } catch (e: Exception) {
                        }
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
                }, resultPath)
                manager.next()
            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_crop_avatar)
    }

    override fun initView() {

    }

    override fun initData() {
        ImageLoader.getInstance().displayImage("file://" + intent.getStringExtra("path"), touchImg)
    }

    override fun initEvent() {
        tv_Cancel.setOnClickListener { finish() }
        tv_Commit.setOnClickListener {
            save()
        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun save() {
        transLayout.showProgress()
        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg voids: Void): String? {
                val bitmap = Bitmap.createBitmap(cropView.getWidth(), cropView.getHeight(), Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap!!)
                cropView.draw(canvas)
                val file = File(Environment.getExternalStorageDirectory(), IConstant.DOCNAME + "/" + IConstant.CACHE_NAME + "/" + System.currentTimeMillis() + ".png")
                try {
                    if (bitmap != null) {
                        val fos = FileOutputStream(file)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)
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

            override fun onPostExecute(s: String) {
                super.onPostExecute(s)
                if (!TextUtils.isEmpty(s)) {
                    resultPath = s
                    persent?.getQiNiuToken()
                }
            }
        }.execute()
    }
}