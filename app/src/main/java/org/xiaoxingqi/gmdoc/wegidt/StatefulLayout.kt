package org.xiaoxingqi.gmdoc.wegidt

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import org.xiaoxingqi.gmdoc.R


/**
 * Created by yzm on 2018/4/20.
 * 切换view 状态的基类  可以动态的切换 内容  loading offline progress 等四个状态
 */
abstract class StatefulLayout : FrameLayout {
    var mContext: Context? = null
    private var mInitialState: State? = null
    private var mProgressLayoutId: Int = 0
    private var mOfflineLayoutId: Int = 0
    private var mEmptyLayoutId: Int = 0
    private var mContentLayout: View? = null
    private var mProgressLayout: View? = null
    private var mOfflineLayout: View? = null
    private var mEmptyLayout: View? = null
    private var mState: State? = null
    private var mOnStateChangeListener: OnStateChangeListener? = null

    constructor(context: Context?) : this(context, null, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        setWillNotCacheDrawing(true)
        setBackgroundColor(Color.parseColor("#00000000"))
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.StatefulLayout)
        if (typedArray!!.hasValue(R.styleable.StatefulLayout_defaultState)) {
            val initialStateValue = typedArray.getInt(R.styleable.StatefulLayout_defaultState, State.CONTENT.getValue())
            mInitialState = State.CONTENT.valueToState(initialStateValue)
//            mInitialState = State.values()[initialStateValue]
        }
        if (isInEditMode) {
            return
        }
        if (typedArray.hasValue(R.styleable.StatefulLayout_progressLayout) && typedArray.hasValue(R.styleable.StatefulLayout_offlineLayout)
                && typedArray.hasValue(R.styleable.StatefulLayout_emptyLayout)) {
            mProgressLayoutId = typedArray.getResourceId(R.styleable.StatefulLayout_progressLayout, 0)
            mOfflineLayoutId = typedArray.getResourceId(R.styleable.StatefulLayout_offlineLayout, 0)
            mEmptyLayoutId = typedArray.getResourceId(R.styleable.StatefulLayout_emptyLayout, 0)
        } else {
            throw IllegalArgumentException("必须填写 progressLayout, offlineLayout 和 emptyLayout 属性。")
        }

        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupView()
    }

    fun getContentView(): View {
        return this
    }

    fun showContent() {
        setState(State.CONTENT)
    }

    fun showProgress() {
        setState(State.PROGRESS)
    }

    fun showOffline() {
        setState(State.OFFLINE)
    }

    fun showEmpty() {
        setState(State.EMPTY)
    }

    fun getState(): State? {
        return mState
    }

    enum class State(a: Int) {
        CONTENT(0), PROGRESS(1), OFFLINE(2), EMPTY(3);

        var mValue: Int = a

        fun valueToState(value: Int): State {
            val values = State.values()
            return values[value]
        }


        fun getValue(): Int {
            return mValue
        }
    }

    fun setState(state: State?) {
        mState = state
        //        mContentLayout.setVisibility(state == State.CONTENT ? View.VISIBLE : View.GONE);
        mProgressLayout?.visibility = if (state == State.PROGRESS) View.VISIBLE else View.GONE
        mOfflineLayout?.visibility = if (state == State.OFFLINE) View.VISIBLE else View.GONE
        mEmptyLayout?.visibility = if (state == State.EMPTY) View.VISIBLE else View.GONE
        if (mOnStateChangeListener != null)
            mOnStateChangeListener?.onStateChange(this, state)
        onSetState(state)
    }

    private fun setupView() {
        if (mContentLayout == null) {
            if (isInEditMode) {
                return
            }
            mContentLayout = getChildAt(0)
            mProgressLayout = LayoutInflater.from(context).inflate(mProgressLayoutId, null, false)
            mOfflineLayout = LayoutInflater.from(context).inflate(mOfflineLayoutId, null, false)
            mEmptyLayout = LayoutInflater.from(context).inflate(mEmptyLayoutId, null, false)
            addView(mProgressLayout)
            addView(mOfflineLayout)
            addView(mEmptyLayout)
            setState(mInitialState)
        }
    }

    abstract fun onSetState(state: State?)

    interface OnStateChangeListener {
        fun onStateChange(v: View, state: State?)
    }

}