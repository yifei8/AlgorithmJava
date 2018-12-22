package com.sjtu.yifei.algorithmjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        LogUtil.d("MainActivity dispatchTouchEvent" + ev.action)
        when {
            ev.action == MotionEvent.ACTION_DOWN -> {

            }
            ev.action == MotionEvent.ACTION_UP -> {
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        LogUtil.d("MainActivity onTouchEvent" + ev.action)
        return super.onTouchEvent(ev)
    }
}
