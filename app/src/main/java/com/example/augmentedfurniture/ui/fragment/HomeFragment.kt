package com.example.augmentedfurniture.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.CategoryAdapter
import com.example.augmentedfurniture.adapter.HorizontalProductScrollAdapter
import com.example.augmentedfurniture.adapter.SliderAdapter
import com.example.augmentedfurniture.model.CategoryModel
import com.example.augmentedfurniture.model.HorizontalProductScrollModel
import com.example.augmentedfurniture.model.SliderModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    // CategoryItem
    private var categoryModelFakeList: MutableList<CategoryModel> = ArrayList()
    private var categoryAdapter: CategoryAdapter? = null
    private var categoryRecyclerView: RecyclerView? = null
    // CategoryItem

    ///////////////////BANNER SLIDER
    private var sliderViewPager: ViewPager? = null
    private var sliderModelList: MutableList<SliderModel> = ArrayList()
    private var sliderAdapter: SliderAdapter? = null
    private var currentPage: Int = 2
    private var timer: Timer? = null
    ///////////////////BANNER SLIDER

    ///////////////////Strip Ad
    lateinit var stripContainer: ConstraintLayout
    lateinit var stripAdImage: ImageView
    ///////////////////Strip Ad

    ///////////////////Horizontal Product Layout
    lateinit var horizontalLayoutTitle: TextView
    lateinit var horizontalLayoutviewAllButton: Button
    lateinit var horizontalRecyclerView: RecyclerView
    private var horizontalProductScrollAdapter : HorizontalProductScrollAdapter?= null
    ///////////////////Horizontal Product Layout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview)
        // set RecyclerView
        setupRecyclerView()

        sliderViewPager = view?.findViewById(R.id.bannerSliderViewPager)!!
        // set BannerSlider
        setBannerSlider()

        ///////Strip Ad
        stripAdImage = view.findViewById(R.id.stripAdImage)
        stripContainer = view.findViewById(R.id.stripAdContainer)

        stripAdImage.setImageResource(R.drawable.s)
        stripContainer.setBackgroundColor(Color.parseColor("#000000"))
        ///////////////////Strip Ad

        ///////////////////Horizontal Product Layout
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title)
        horizontalLayoutviewAllButton = view.findViewById(R.id.horizontal_scroll_layout_view_all)
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview)

        setHorizontalRecyclerView()

        return view
    }

    private fun setHorizontalRecyclerView() {
        val horizontalProductScrollModelList : MutableList<HorizontalProductScrollModel> = ArrayList()

        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.image,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.custom_error_icon,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.red_mail,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.green_mail,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.home_icon,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.ic_launcher,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.image,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.image,"Brown Leather Sofa","by Trevi Furniture","₹ 17,799/-"))

        horizontalProductScrollAdapter = HorizontalProductScrollAdapter(horizontalProductScrollModelList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        horizontalRecyclerView.layoutManager = layoutManager

        horizontalRecyclerView.adapter = horizontalProductScrollAdapter
        horizontalProductScrollAdapter!!.notifyDataSetChanged()
    }

    ////// BANNER slider start
    private fun setBannerSlider() {

        Log.d("banner", "inside setBannerSlider()")

        sliderModelList.add(SliderModel(R.mipmap.home_icon, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.custom_error_icon, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.green_mail, "#077AE4"))

        sliderModelList.add(SliderModel(R.mipmap.red_mail, "#077AE4"))
        sliderModelList.add(SliderModel(R.drawable.slide, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.ic_launcher, "#077AE4"))
        sliderModelList.add(SliderModel(R.drawable.ic_baseline_email, "#077AE4"))
        sliderModelList.add(SliderModel(R.drawable.ic_home, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.home_icon, "#077AE4"))

        sliderModelList.add(SliderModel(R.mipmap.custom_error_icon, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.green_mail, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.red_mail, "#077AE4"))

        sliderAdapter = SliderAdapter(sliderModelList)
        sliderViewPager?.adapter = sliderAdapter
        sliderViewPager?.clipToPadding = false
        sliderViewPager?.pageMargin = 20

        sliderViewPager?.currentItem = currentPage

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

//        sliderViewPager?.setOnTouchListener { v, event ->
//            pageLooper()
//            stopBannerSlideShow()
//
//            if (event?.action == MotionEvent.ACTION_UP) {
//                startBannerSlideShow()
//            }
//
//            false
//        }

        sliderViewPager?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
                Log.d("banner", "inside onTouch()")
                pageLooper()
                stopBannerSlideShow()

                if (event?.action == MotionEvent.ACTION_UP) {
                    startBannerSlideShow()
                }
                return false
            }
        })
    }

    private fun pageLooper() {
        Log.d("banner", "inside pageLooper()")
        if (currentPage == sliderModelList.size - 2) {
            currentPage = 2
            sliderViewPager?.setCurrentItem(currentPage, false)
        }

        if (currentPage == 1) {
            currentPage = sliderModelList.size - 3
            sliderViewPager?.setCurrentItem(currentPage, false)
        }
    }

    private fun startBannerSlideShow() {
        Log.d("banner", "inside startBannerSlideShow()")

        val handler: Handler? = null
        val update = Runnable {
            if (currentPage >= sliderModelList.size) {
                currentPage = 1
            }
            sliderViewPager?.setCurrentItem(currentPage++, true)
        }

        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler?.post(update)
            }
        }, 3000, 3000)
    }

    private fun stopBannerSlideShow() {
        Log.d("banner", "inside stopBannerSlideShow()")
        timer?.cancel()
    }
    ////// BANNER slider end

    // setRecyclerView for Different
    // CategoriesItem
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        categoryRecyclerView?.layoutManager = layoutManager

        //////////////////////////////Categories Fake List
        categoryModelFakeList.add((CategoryModel("link", "Home")))
        categoryModelFakeList.add(CategoryModel("link", "Chair"))
        categoryModelFakeList.add(CategoryModel("link", "Sofas"))
        categoryModelFakeList.add(CategoryModel("link", "Bed"))
        categoryModelFakeList.add(CategoryModel("link", "Dining"))
        categoryModelFakeList.add(CategoryModel("link", "Decor"))
        categoryModelFakeList.add(CategoryModel("link", "CupBoards"))

        categoryAdapter = CategoryAdapter(categoryModelFakeList)
        categoryRecyclerView?.adapter = categoryAdapter
        categoryAdapter!!.notifyDataSetChanged()
    }


}