package org.xiaoxingqi.gmdoc.modul.album

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.nineoldandroids.animation.Animator
import com.nineoldandroids.animation.AnimatorListenerAdapter
import com.nineoldandroids.animation.ObjectAnimator
import kotlinx.android.synthetic.main.activity_album.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseAct
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.FilePath
import org.xiaoxingqi.gmdoc.impl.IConstant
import org.xiaoxingqi.gmdoc.tools.AppTools
import org.xiaoxingqi.gmdoc.tools.FastBlur
import org.xiaoxingqi.gmdoc.wegidt.CustomCheckImageView
import java.io.File
import java.io.FilenameFilter
import java.util.ArrayList

/**
 * 本地相册
 */
class AlbumActivity : BaseAct() {
    private val WRITE_COARSE_LOCATION_REQUEST_CODE = 0x01
    private val CAMERA_REQUEST_CODE = 0x02
    private val CAMARECODE = 0x03
    private val PREVIEW_CODE = 0x04
    private var cameraFile: File? = null
    private val pathList = ArrayList<FilePath>()
    private lateinit var adapter: QuickAdapter<String>
    private val mData by lazy { ArrayList<String>() }
    private val selected by lazy { ArrayList<String>() }
    private var alowSeleted = 0
    private lateinit var fileAdapter: QuickAdapter<FilePath>
    private var currentFile = 0
    override fun setContent() {
        setContentView(R.layout.activity_album)
    }

    override fun initView() {
        ObjectAnimator.ofFloat(recycler, "translationY", -AppTools.getWindowsHeight(this).toFloat()).setDuration(0).start()
    }

    override fun initData() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        WRITE_COARSE_LOCATION_REQUEST_CODE)
            } else {
                request()
            }
        } else {
            request()
        }
        alowSeleted = intent.getIntExtra("allowCount", 0)
        mData.add("相机")
        adapter = object : QuickAdapter<String>(this, R.layout.item_select_pic, mData) {
            @SuppressLint("SetTextI18n")
            override fun convert(helper: BaseAdapterHelper?, item: String?) {
                if ("相机" == item) {
                    helper!!.getImageView(R.id.img).setImageResource(R.mipmap.img_takephoto)
                    Glide.with(helper.getImageView(R.id.img).context)
                            .load(R.mipmap.img_takephoto)
                            .asBitmap()
                            .error(R.drawable.img_empty_avatar_back)
                            .placeholder(R.mipmap.img_takephoto)
                            .centerCrop()
                            .into(helper.getImageView(R.id.img))
                    helper.getView(R.id.check_img).visibility = View.GONE
                } else {
                    if (item!!.endsWith(".gif")) {
                        helper!!.getView(R.id.iv_Gif).visibility = View.VISIBLE
                    } else {
                        helper!!.getView(R.id.iv_Gif).visibility = View.GONE
                    }
                    Glide.with(helper.getImageView(R.id.img).context)
                            .load(item)
                            .asBitmap()
                            .error(R.drawable.img_empty_avatar_back)
                            .centerCrop()
                            .skipMemoryCache(true)
                            .override(180, 180)
                            .into(helper.getImageView(R.id.img))
                    helper.getView(R.id.check_img).visibility = View.VISIBLE
                    val check = helper.getView(R.id.check_img) as CustomCheckImageView
                    //点击事件
                    check.setOnClickListener { v ->
                        if (!selected.contains(item)) {
                            if (selected.size >= alowSeleted) {
                                showToast("最多可选" + alowSeleted + "张照片")
                                return@setOnClickListener
                            }
                            selected.add(item)
                        } else {
                            if (selected.contains(item))
                                selected.remove(item)
                        }
                        check.isSelected = !check.isSelected
                        if (selected.size == 0) {
                            tv_Count.visibility = View.GONE
                            tv_Pre.setTextColor(Color.parseColor("#b2b2b2"))
                        } else {
                            tv_Count.visibility = View.VISIBLE
                            tv_Pre.setTextColor(Color.parseColor("#1a191a"))
                        }
                        tv_Count.text = "" + selected.size
                    }
                    check.isSelected = selected.contains(item)
                }
            }
        }
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
        fileAdapter = object : QuickAdapter<FilePath>(this, R.layout.item_photo_file_list, pathList) {
            override fun convert(helper: BaseAdapterHelper?, item: FilePath?) {
                Glide.with(helper!!.getImageView(R.id.iv_img).context)
                        .load(item!!.firstPath)
                        .error(R.drawable.img_empty_avatar_back)
                        .centerCrop()
                        .override(180, 180)
                        .into(GlideDrawableImageViewTarget(helper.getImageView(R.id.iv_img), 0))
                helper.getTextView(R.id.tv_Title).text = item.name
                helper.getTextView(R.id.tv_Count).text = item.count.toString() + ""
            }
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = fileAdapter
    }

    override fun initEvent() {
        tv_Title.setOnClickListener {
            if (recycler.y == 0f) {
                val duration = ObjectAnimator.ofFloat(recycler, "translationY", -AppTools.getWindowsHeight(this).toFloat()).setDuration(320)
                duration.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        iv_Bg.setImageBitmap(null)
                    }
                })
                duration.start()
            } else {
                ObjectAnimator.ofFloat(recycler, "translationY", 0f).setDuration(320).start()
                iv_Bg.setImageBitmap(FastBlur().fastblur(onCut(), 50, iv_Bg))
            }
        }
        fileAdapter.setOnItemClickListener { view, position ->
            val duration = ObjectAnimator.ofFloat(recycler, "translationY", -AppTools.getWindowsHeight(this).toFloat()).setDuration(320)
            duration.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    iv_Bg.setImageBitmap(null)
                }
            })
            duration.start()
            if (currentFile == position) {
                return@setOnItemClickListener
            }
            mData.clear()
            if (position == 0) {
                mData.add("相机")
                request()
            } else {
                queryPhotoByFile(pathList[position].path)
            }
            currentFile = position
        }
        tv_Pre.setOnClickListener {
            startActivityForResult(Intent(this, PreviewActivity::class.java)
                    .putExtra("selected", selected)
                    .putExtra("allow", alowSeleted)
                    .putExtra("data", selected), PREVIEW_CODE)
        }
        adapter.setOnItemClickListener { view, position ->
            if (position == 0) {
                /**
                 * 打开相机
                 */
                openCamera()
            } else {
                startActivityForResult(Intent(this, PreviewActivity::class.java)
                        .putExtra("selected", selected)
                        .putExtra("allow", alowSeleted)
                        .putExtra("current", if (currentFile == 0) position - 1 else position)
                        .putExtra("parentFile", pathList[currentFile].path), PREVIEW_CODE)
            }
        }
        tv_Commit.setOnClickListener {
            if (selected.size == 0) {
                showToast("请选择照片")
                return@setOnClickListener
            }
            intent.putExtra("result", selected)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun openCamera() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                        CAMERA_REQUEST_CODE)//自定义的code
            } else {
                camera()
            }
        } else {
            camera()
        }
    }

    private fun camera() {
        try {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            // 判断存储卡是否可以用，可用进行存储
            if (AppTools.hasSdcard()) {
                val file = File(Environment.getExternalStorageDirectory(), IConstant.DOCNAME + "/" + IConstant.CACHE_NAME)
                if (!file.exists()) {
                    file.mkdirs()
                }
                cameraFile = File(Environment.getExternalStorageDirectory(), IConstant.DOCNAME + "/" + IConstant.CACHE_NAME + "/" + System.currentTimeMillis() + ".png")
                var uri: Uri? = null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(
                            this,
                            "$packageName.fileprovider",
                            cameraFile!!)
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                } else {
                    uri = Uri.fromFile(cameraFile)
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(intent, CAMARECODE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("请打开相机权限")
        }
    }

    private fun onCut(): Bitmap {
        val b = Bitmap.createBitmap(linearContent.width, linearContent.height, Bitmap.Config.ARGB_4444)
        val c = Canvas(b)
        val bgDrawable = linearContent.background
        if (bgDrawable != null)
            linearContent.draw(c)
        else
            c.drawColor(Color.WHITE)
        linearContent.draw(c)
        return b
    }

    /**
     * query local photo
     */
    @SuppressLint("StaticFieldLeak")
    override fun request() {
        pathList.clear()
        object : AsyncTask<Void, Void, List<String>>() {
            override fun doInBackground(vararg params: Void?): List<String> {
                val data = ArrayList<String>()
                var firstPath: String?
                val parentList = ArrayList<String>()
                val imageUrl = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                val resolver = this@AlbumActivity.contentResolver
                val cursor = resolver.query(imageUrl, null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        arrayOf("image/jpeg", "image/png", "image/gif"), MediaStore.Images.Media.DATE_MODIFIED)
                if (null != cursor && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        val path = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Images.Media.DATA))
                        data.add(0, path)
                        firstPath = path
                        val parentFile = File(path).parentFile ?: continue
                        val parentPath = parentFile.absolutePath
                        var backResult: FilePath?
                        if (parentList.contains(parentPath))
                            continue//防止多次添加
                        else {
                            parentList.add(parentPath)
                            backResult = FilePath()
                            val index = parentPath.lastIndexOf("/")
                            backResult.name = parentPath.substring(index + 1)
                            backResult.path = parentPath
                            backResult.firstPath = firstPath
                        }
                        val filter = FilenameFilter { dir, filename -> filename.endsWith(".png") || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".gif") }
                        val picSize: Int//此目录下有几张图片
                        try {
                            picSize = parentFile.list(filter).size
                        } catch (e: Exception) {
                            e.printStackTrace()
                            continue
                        }
                        backResult.count = picSize
                        pathList.add(backResult)
                    }
                }
                cursor!!.close()
                return data
            }

            override fun onPostExecute(result: List<String>?) {
                if (null != result) {
                    mData.addAll(result)
                    adapter.notifyDataSetChanged()
                }
                pathList.add(0, FilePath().apply {
                    name = "全部图片"
                    count = mData.size
                })
                fileAdapter.notifyDataSetChanged()
            }
        }.execute()
    }

    @SuppressLint("StaticFieldLeak")
    fun queryPhotoByFile(file: String) {
        object : AsyncTask<Void, Void, Array<String>>() {
            override fun doInBackground(vararg voids: Void): Array<String>? {
                val file = File(file)
                if (file.isDirectory) {
                    val filter = FilenameFilter { file, s -> s.endsWith("png") || s.endsWith("jpg") || s.endsWith("jpeg") || s.endsWith("gif") }
                    return file.list(filter)
                }
                return null
            }

            override fun onPostExecute(strings: Array<String>) {
                super.onPostExecute(strings)
                for (str in strings)
                    mData.add("$file/$str")
                adapter.notifyDataSetChanged()
                if (mData.size == 0) {

                }
            }
        }.execute()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WRITE_COARSE_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                request()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PREVIEW_CODE) {
                data?.let {
                    val selected = it.getSerializableExtra("select") as ArrayList<String>
                    val intent = Intent()
                    intent.putExtra("result", selected)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            } else if (requestCode == CAMARECODE) {
                mData.add(1, cameraFile!!.absolutePath)
                adapter.notifyDataSetChanged()
                //在手机相册中显示刚拍摄的图片
                val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                val contentUri = Uri.fromFile(cameraFile)
                mediaScanIntent.data = contentUri
                sendBroadcast(mediaScanIntent)
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == PREVIEW_CODE) {
                data?.let {
                    selected.clear()
                    selected.addAll(it.getSerializableExtra("select") as ArrayList<String>)
                    adapter.notifyDataSetChanged()
                    if (selected.size == 0) {
                        tv_Count.visibility = View.GONE
                    } else {
                        tv_Count.visibility = View.VISIBLE
                        tv_Count.text = selected.size.toString()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mData.clear()
        pathList.clear()
        selected.clear()
    }

}