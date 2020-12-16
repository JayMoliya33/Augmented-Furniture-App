package com.example.augmentedfurniture.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.OnBoardingViewPagerAdapter
import com.example.augmentedfurniture.base.BaseActivity
import com.example.augmentedfurniture.model.OnBoardingData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : BaseActivity() {

    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    lateinit var list: ArrayList<OnBoardingData>
    private var btnAnim: Animation? = null
    private var position: Int = 0
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun getLayoutResId(): Int {
        return R.layout.activity_onboarding
    }

    @SuppressLint("CommitPrefEdits")
    override fun initViews() {

        btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()

        list = ArrayList()
        list.add(
                OnBoardingData(
                        "Order Your Items...",
                        "Description Of First Title",
                        R.drawable.ic_checklist
                )
        )
        list.add(
                OnBoardingData(
                        "Choose Your Itmes",
                        "Description Of Second Title",
                        R.drawable.ic_choose_product
                )
        )
        list.add(
                OnBoardingData(
                        "Choose Your Payment Options",
                        "Description Of Third Title",
                        R.drawable.ic_payment
                )
        )
        list.add(
                OnBoardingData(
                        "Locate your shipping Adress",
                        "Description Of Fourth Title",
                        R.drawable.ic_delivery
                )
        )
        list.add(
                OnBoardingData(
                        "Enjoy with your products",
                        "Description Of Fifth Title",
                        R.drawable.ic_box_gift
                )
        )
        list.add(
                OnBoardingData(
                        "Customer Support",
                        "Description Of Sixth Title",
                        R.drawable.ic_helpline
                )
        )

        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, list)
        screnViewPager.adapter = onBoardingViewPagerAdapter

        tab_indicator.setupWithViewPager(screnViewPager)

    }

    override fun setListeners() {
        btnNext.setOnClickListener {
            position = screnViewPager.currentItem
            if (position < list.size) {
                position++
                screnViewPager.setCurrentItem(position)
            }

            if (position == list.size - 1) {
                loadScreen()
            }

        }
        tvSkip.setOnClickListener {
            moveToLoginActivity()
        }

        btnGetStarted.setOnClickListener {
            moveToLoginActivity()
        }

        tab_indicator.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == list.size - 1) {
                    loadScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun moveToLoginActivity() {
        editor.putBoolean("onBoardingComplete", true)
        editor.apply()

        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun loadScreen() {
        btnNext.visibility = View.GONE
        tab_indicator.visibility = View.GONE
        btnGetStarted.visibility = View.VISIBLE
        btnGetStarted.animation = btnAnim
    }

}