package com.teknokrait.mussichusetts.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teknokrait.mussichusetts.R
import android.content.Intent
import android.os.Handler


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        intentToLogin()
    }

    fun intentToLogin(){
        Handler().postDelayed(Runnable {
            val mainIntent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            this@SplashScreenActivity.startActivity(mainIntent)
            this@SplashScreenActivity.finish()
        }, 5000)
    }
}
