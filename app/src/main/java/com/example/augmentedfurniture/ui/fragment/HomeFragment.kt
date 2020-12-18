package com.example.augmentedfurniture.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.CategoryAdapter
import com.example.augmentedfurniture.adapter.SliderAdapter
import com.example.augmentedfurniture.model.CategoryModel
import com.example.augmentedfurniture.model.SliderModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    // Category
    private var categoryModelFakeList: MutableList<CategoryModel> = ArrayList()
    private var categoryAdapter: CategoryAdapter? = null
    private var categoryRecyclerView: RecyclerView? = null

    ///////////////////BANNER SLIDER
    private var sliderViewPager: ViewPager? = null
    private var sliderModelList: MutableList<SliderModel> = ArrayList()
    private var sliderAdapter: SliderAdapter? = null
    private var currentPage: Int = 2
    private var timer: Timer?= null
    private val DELAY_TIME : Long = 3000
    private val PERIOD_TIME : Long = 3000

    ///////////////////BANNER SLIDER

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview)
        // set RecyclerView
        setupRecyclerView()

        sliderViewPager = view?.findViewById(R.id.bannerSlideViewPager)!!
        // set BannerSlider
        setBannerSlider()


        return view

    }

    ////// BANNER slider
    private fun setBannerSlider() {

        sliderModelList.add(SliderModel(R.mipmap.home_icon))
        sliderModelList.add(SliderModel(R.mipmap.custom_error_icon))

        sliderModelList.add(SliderModel(R.mipmap.green_mail))
        sliderModelList.add(SliderModel(R.mipmap.red_mail))
        sliderModelList.add(SliderModel(R.mipmap.ic_launcher_round))
        sliderModelList.add(SliderModel(R.mipmap.ic_launcher))
        sliderModelList.add(SliderModel(R.drawable.ic_baseline_email))
        sliderModelList.add(SliderModel(R.drawable.ic_home))
        sliderModelList.add(SliderModel(R.mipmap.home_icon))
        sliderModelList.add(SliderModel(R.mipmap.custom_error_icon))

        sliderModelList.add(SliderModel(R.mipmap.green_mail))
        sliderModelList.add(SliderModel(R.mipmap.red_mail))

        sliderAdapter = SliderAdapter(sliderModelList)
        sliderViewPager?.adapter = sliderAdapter
        sliderViewPager?.clipToPadding = false
        sliderViewPager?.pageMargin = 20

        sliderViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper()
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                currentPage = position

            }

        })

        startBannerSlideShow()

        sliderViewPager?.setOnTouchListener { v, event ->
            pageLooper()
            stopBannerSlideShow()

            if (event?.action == MotionEvent.ACTION_UP) {
                startBannerSlideShow()
            }

            false
        }

    }

    private fun pageLooper() {
        if (currentPage == sliderModelList.size - 2) {
            currentPage = 2
            sliderViewPager?.setCurrentItem(currentPage, false)
        }

        if (currentPage == 1) {
            currentPage = sliderModelList.size - 3
            sliderViewPager?.setCurrentItem(currentPage, false)
        }
    }

    private fun startBannerSlideShow(){

        val handler : Handler? = null
        val update = object : Runnable {

            override fun run() {
                if(currentPage>= sliderModelList.size){
                    currentPage = 1
                }
                sliderViewPager?.setCurrentItem(currentPage++,true)
            }
        }
        timer?.schedule(object : TimerTask(){
            override fun run() {
                handler?.post(update)
            }
        },DELAY_TIME,PERIOD_TIME)
    }

    private fun stopBannerSlideShow(){
        timer?.cancel()
    }

    ////// BANNER slider

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        categoryRecyclerView?.layoutManager = layoutManager

        //////////////////////////////Categories Fake List
        categoryModelFakeList.add((CategoryModel("link", "Hosdsdsme")))
        categoryModelFakeList.add(CategoryModel("link", "Chair"))
        categoryModelFakeList.add(CategoryModel("link", "Chair"))
        categoryModelFakeList.add(CategoryModel("link", "Chair"))
        categoryModelFakeList.add(CategoryModel("link", "Chair"))
        categoryModelFakeList.add(CategoryModel("link", "Chair"))
        categoryModelFakeList.add(CategoryModel("link", "Chair"))

        categoryAdapter = CategoryAdapter(categoryModelFakeList)
        categoryRecyclerView?.adapter = categoryAdapter
        categoryAdapter!!.notifyDataSetChanged()
    }


}