package com.shlib.verifycodelib

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout

/**
 * Desc: 验证码输入框的光标
 * Created by sunhang on 18-4-24.
 * Email：sunh@eetrust.com
 */
class VerifyCodeCursor: LinearLayout {
    private lateinit var view: View
    private lateinit var alphaAnimation: Animation

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) :
            super(context, attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr){
        init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        view = View.inflate(context, R.layout.verify_code_cursor, this)
        alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 500
        alphaAnimation.interpolator = LinearInterpolator()
        alphaAnimation.repeatCount = Animation.INFINITE
        alphaAnimation.repeatMode = Animation.REVERSE
    }

    override fun setVisibility(visibility: Int) {
        if (visibility == View.VISIBLE) {
            view.startAnimation(alphaAnimation)
        } else {
            view.clearAnimation()
        }
        super.setVisibility(visibility)
    }
}