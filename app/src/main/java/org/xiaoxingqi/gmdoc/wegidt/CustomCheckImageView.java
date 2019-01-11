package org.xiaoxingqi.gmdoc.wegidt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import org.xiaoxingqi.gmdoc.R;


/**
 * Created by Kevin on 2016/6/13.
 */
@SuppressLint("AppCompatCustomView")
public class CustomCheckImageView extends ImageView implements View.OnClickListener {
    private boolean mIsSelected = false;
    private int mResIdOn = R.mipmap.btn_picker_oval_selected;
    private int mResIdOff = R.mipmap.btn_picker_oval_nor;

    public CustomCheckImageView(Context context) {
        super(context);
        initView(null);
    }

    public CustomCheckImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public CustomCheckImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        this.setOnClickListener(this);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomCheckImageView);
            if (typedArray.hasValue(R.styleable.CustomCheckImageView_checkState)) {
                mIsSelected = typedArray.getBoolean(R.styleable.CustomCheckImageView_checkState, false);
            }
            if (typedArray.hasValue(R.styleable.CustomCheckImageView_checkOn)) {
                mResIdOn = typedArray.getResourceId(R.styleable.CustomCheckImageView_checkOn, R.mipmap.btn_picker_oval_selected);
            }
            if (typedArray.hasValue(R.styleable.CustomCheckImageView_checkOff)) {
                mResIdOff = typedArray.getResourceId(R.styleable.CustomCheckImageView_checkOff, R.mipmap.btn_picker_oval_nor);
            }
            typedArray.recycle();
        }
        setSelected(mIsSelected);
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean mIsSelected) {
        this.mIsSelected = mIsSelected;
        this.setImageResource(mIsSelected ? mResIdOn : mResIdOff);
    }

    @Override
    public void onClick(View v) {
        setSelected(!mIsSelected);
        if (this.mOnStateChangeListener != null)
            this.mOnStateChangeListener.onStateChange(mIsSelected);
    }

    private OnStateChangeListener mOnStateChangeListener;

    public void setOnStateChangeListener(OnStateChangeListener mOnStateChangeListener) {
        this.mOnStateChangeListener = mOnStateChangeListener;
    }

    public interface OnStateChangeListener {
        public void onStateChange(boolean selected);
    }
}
