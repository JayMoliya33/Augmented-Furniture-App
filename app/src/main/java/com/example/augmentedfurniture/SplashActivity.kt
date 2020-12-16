package com.example.augmentedfurniture

import android.content.Intent
import android.os.Handler
import android.preference.PreferenceManager
import com.example.augmentedfurniture.base.BaseActivity
import com.example.augmentedfurniture.ui.activity.HomeActivity
import com.example.augmentedfurniture.ui.activity.LoginActivity
import com.example.augmentedfurniture.ui.activity.OnBoardingActivity

class SplashActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun initViews() {
        Handler().postDelayed(Runnable {
            val sp = PreferenceManager.getDefaultSharedPreferences(this)
            val check = sp.getBoolean("booleanIsChecked", false)
            val onBoardingComplete = sp.getBoolean("onBoardingComplete", false)

            if (!onBoardingComplete){
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }
            else if (check) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }, 2000)
    }

    override fun setListeners() {
    }
}