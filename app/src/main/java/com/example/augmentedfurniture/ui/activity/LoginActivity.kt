package com.example.augmentedfurniture.ui.activity

import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.base.BaseActivity
import com.example.augmentedfurniture.ui.fragment.LoginFragment
import com.example.augmentedfurniture.utility.LOGIN_FRAGMENT

class LoginActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initViews() {

        navigateToFragment(LoginFragment(), false, LOGIN_FRAGMENT)

    }

    override fun setListeners() {
    }

    /* override fun onBackPressed() {

         if(supportFragmentManager.backStackEntryCount > 1){
             clearBackStack()
             popScreen()
         }
         else{
             super.onBackPressed()
         }

     }*/
}