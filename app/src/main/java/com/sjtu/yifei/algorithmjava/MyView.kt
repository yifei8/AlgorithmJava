package com.sjtu.yifei.algorithmjava

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button

/**
 * [description]
 * author: yifei
 * created at 18/12/17 下午9:39
 */
public class MyView : Button {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        LogUtil.d("MyView dispatchTouchEvent" + ev.action)
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        LogUtil.d("MyView onTouchEvent" + ev.action)
        return super.onTouchEvent(ev)
    }



}