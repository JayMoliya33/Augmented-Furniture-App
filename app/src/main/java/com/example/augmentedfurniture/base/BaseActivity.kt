package com.example.augmentedfurniture.base

import android.app.AlertDialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.augmentedfurniture.R


abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mContext: Context
    private var isConnected = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getLayoutResId())
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        isConnected = false;
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initViews()
        setListeners()
    }

    abstract fun getLayoutResId(): Int

    abstract fun initViews()

    abstract fun setListeners()

    override fun onClick(view: View?) {
    }

    open fun navigateToFragment(
            fragment: Fragment,
            addToBackstack: Boolean,
            tag: String?
    ) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFrame, fragment, tag)
        if (addToBackstack) fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }

}