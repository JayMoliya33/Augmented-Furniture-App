package com.example.augmentedfurniture.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.base.BaseActivity
import com.example.augmentedfurniture.ui.fragment.LoginFragment
import com.example.augmentedfurniture.utility.LOGIN_FRAGMENT

class LoginActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_home)

    }

     override fun initViews() {
        navigateToFragment(LoginFragment(), false, LOGIN_FRAGMENT)

    }

    override fun setListeners() {
    }

}