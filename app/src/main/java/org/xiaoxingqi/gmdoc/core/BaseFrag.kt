package org.xiaoxingqi.gmdoc.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.xiaoxingqi.gmdoc.parsent.BasePresent

abstract class BaseFrag<T : BasePresent> : Fragment() {

    private var mView: View? = null
    var toast: Toast? = null
    var persent: T? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView != null) {
            val viewGroup = mView?.parent?.let { it as ViewGroup }
            viewGroup?.removeAllViews()
        } else {
            mView = inflater.inflate(getlyoutId(), null)
            persent = createPresent()
            initView(mView)
            initData()
            bindEvent()
        }
        return mView
    }

    abstract fun getlyoutId(): Int

    abstract fun initView(view: View?)

    abstract fun initData()

    abstract fun bindEvent()

    abstract fun request(flag: Int)

    abstract fun createPresent(): T

    fun showToast(text: String) {
        if (toast == null) {
            toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(text)
        }
        toast!!.show()
    }

    override fun onDestroy() {

        super.onDestroy()
    }

}