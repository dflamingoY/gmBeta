package org.xiaoxingqi.gmdoc.modul.game

import android.graphics.Bitmap
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import kotlinx.android.synthetic.main.activity_game_details.*
import kotlinx.android.synthetic.main.game_head.view.*
import org.xiaoxingqi.gmdoc.R
import org.xiaoxingqi.gmdoc.core.BaseActivity
import org.xiaoxingqi.gmdoc.core.adapter.BaseAdapterHelper
import org.xiaoxingqi.gmdoc.core.adapter.BaseQuickAdapter
import org.xiaoxingqi.gmdoc.core.adapter.QuickAdapter
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.impl.game.GameDetailCallBack
import org.xiaoxingqi.gmdoc.parsent.GameDetailPersent
import org.xiaoxingqi.gmdoc.tools.FastBlur
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GameDetailsActivity : BaseActivity<GameDetailPersent>() {
    private lateinit var adapter: QuickAdapter<HomeUserShareData.ContributeBean>
    private lateinit var headView: View
    private lateinit var gameId: String
    private val mData by lazy {
        ArrayList<HomeUserShareData.ContributeBean>()
    }

    override fun createPresent(): GameDetailPersent {
        return GameDetailPersent(this, object : GameDetailCallBack {

            override fun gameDetails(data: GameDetailsData?) {
                ImageLoader.getInstance().loadImage(data?.game?.cover, object : SimpleImageLoadingListener() {
                    override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {
                        super.onLoadingComplete(imageUri, view, loadedImage)
                        Observable.create(Observable.OnSubscribe<Bitmap> {
                            it.onNext(FastBlur().fastblur(loadedImage, 60, headView.iv_Bluer_Bg))
                            it.onCompleted()
                        }).observeOn(Schedulers.io())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : Subscriber<Bitmap>() {
                                    override fun onNext(t: Bitmap?) {
                                        headView.iv_Bluer_Bg.setImageBitmap(t)
                                    }

                                    override fun onCompleted() {

                                    }

                                    override fun onError(e: Throwable?) {
                                    }
                                })
                    }
                })


            }
        })
    }

    override fun setContent() {
        setContent(R.layout.activity_game_details)
    }

    override fun initView() {
        gameRecycler.layoutManager = LinearLayoutManager(this)
        headView = LayoutInflater.from(this).inflate(R.layout.game_head, null, false)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }
    }

    override fun initData() {
        gameId = intent.getStringExtra("gameId")
        adapter = object : QuickAdapter<HomeUserShareData.ContributeBean>(this, R.layout.item_dynamic, mData, headView) {
            override fun convert(helper: BaseAdapterHelper?, item: HomeUserShareData.ContributeBean?) {

            }
        }
        gameRecycler.adapter = adapter
        /**
         * 獲取游戲詳情
         */
        persent?.getGameDetail(gameId)
    }

    override fun initEvent() {

    }

    override fun request() {

    }
}