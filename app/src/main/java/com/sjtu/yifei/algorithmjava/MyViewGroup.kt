package com.sjtu.yifei.algorithmjava

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 * [description]
 * author: yifei
 * created at 18/12/17 下午9:37
 */
public class MyViewGroup : FrameLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        LogUtil.d("MyViewGroup dispatchTouchEvent" + ev.action)

        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        LogUtil.d("MyViewGroup onInterceptTouchEvent" + ev.action)
        if (ev.action == MotionEvent.ACTION_DOWN) {
            return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        LogUtil.d("MyViewGroup onTouchEvent" + ev.action)
        return super.onTouchEvent(ev)
    }
}