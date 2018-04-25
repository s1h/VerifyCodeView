package com.shlib.verifycodelib

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Desc: 验证码控件
 *          原理：
 *              EditText编辑      TextView显示
 * Created by sunhang on 18-4-18.
 * Email：sunh@eetrust.com
 */
class VerifyCodeView : LinearLayout {
    private val codeViews = ArrayList<TextView>()
    private val cursorViews = ArrayList<VerifyCodeCursor>()

    private lateinit var verifyCodeLinear: LinearLayout
    private lateinit var verifyCodeEdit: EditText

    constructor(context: Context) :
            super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) :
            super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        val view = View.inflate(context, R.layout.verify_code_layout, this)
        val codeOne = view.findViewById<TextView>(R.id.codeOne)
        val codeTwo = view.findViewById<TextView>(R.id.codeTwo)
        val codeThree = view.findViewById<TextView>(R.id.codeThree)
        val codeFour = view.findViewById<TextView>(R.id.codeFour)
        val cursorOne = view.findViewById<VerifyCodeCursor>(R.id.cursorOne)
        val cursorTwo = view.findViewById<VerifyCodeCursor>(R.id.cursorTwo)
        val cursorThree = view.findViewById<VerifyCodeCursor>(R.id.cursorThree)
        val cursorFour = view.findViewById<VerifyCodeCursor>(R.id.cursorFour)
        verifyCodeLinear = view.findViewById(R.id.verify_code_linear)
        verifyCodeEdit = view.findViewById(R.id.verify_code_edit)
        codeViews.clear()
        codeViews.add(codeOne)
        codeViews.add(codeTwo)
        codeViews.add(codeThree)
        codeViews.add(codeFour)
        cursorViews.clear()
        cursorViews.add(cursorOne)
        cursorViews.add(cursorTwo)
        cursorViews.add(cursorThree)
        cursorViews.add(cursorFour)
        setColor(0)
        setCursor(0)
        verifyCodeLinear.setOnClickListener(onClickListener)
        verifyCodeEdit.addTextChangedListener(textWatcher)

    }

    private val onClickListener = OnClickListener {
        if (it.id == verifyCodeLinear.id) {
            val context = it.context as Context
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val length = s.length
            setColor(length)
            setCursor(length)
            for (i in codeViews.indices) {
                codeViews[i].text = ""
            }

            var j = 0
            while (j < length && j < codeViews.size) {
                codeViews[j].text = s[j].toString()
                j++
            }

            if (codeViews.size > 0 && length == codeViews.size) {
                listener?.verifyCodeComplete()
            }
        }

    }

    private fun setCursor(length: Int) {
        cursorViews.forEach { it.visibility = View.INVISIBLE }
        if (length < 4) {
            cursorViews[length].visibility = View.VISIBLE
        }
    }

    private fun setColor(length: Int) {
        val defaultBg = R.drawable.verify_code_line_default
        val focusBg = R.drawable.verify_code_line_focus
        codeViews.forEach { it.setBackgroundResource(defaultBg) }
        if (length < 4) {
            codeViews[length].setBackgroundResource(focusBg)
        }
    }

    private var listener: VerifyCodeCompleteListener? = null

    fun setCompleteListener(listener: VerifyCodeCompleteListener) {
        this.listener = listener
    }

    fun getText(): String {
        return verifyCodeEdit.text.toString()
    }

    fun clearText() {
        for (i in codeViews.indices) {
            codeViews[i].text = ""
        }
        verifyCodeEdit.text.clear()
    }
}