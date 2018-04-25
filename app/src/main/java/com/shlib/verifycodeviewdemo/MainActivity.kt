package com.shlib.verifycodeviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shlib.verifycodelib.VerifyCodeCompleteListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyCodeView.setCompleteListener(object : VerifyCodeCompleteListener {
            override fun verifyCodeComplete() {
                Toast.makeText(this@MainActivity, verifyCodeView.getText(), Toast.LENGTH_SHORT).show()
            }

        })
    }
}
