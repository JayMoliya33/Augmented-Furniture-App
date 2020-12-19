package com.example.augmentedfurniture.adapter

import android.graphics.Color
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVISIBLE
import androidx.viewpager.widget.ViewPager
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.HomePageModel
import com.example.augmentedfurniture.model.HomePageModel.Companion.BANNER_SLIDER
import com.example.augmentedfurniture.model.HomePageModel.Companion.GRID_PRODUCT_VIEW
import com.example.augmentedfurniture.model.HomePageModel.Companion.HORIZONTAL_PRODUCT_VIEW
import com.example.augmentedfurniture.model.HomePageModel.Companion.STRIP_AD_BANNER
import com.example.augmentedfurniture.model.HorizontalProductScrollModel
import com.example.augmentedfurniture.model.SliderModel
import java.util.*

class HomePageAdapter(private val homePageModelList: List<HomePageModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var lastposition = -1
    override fun getItemViewType(position: Int): Int {

        return when (homePageModelList[position].type) {
            0 -> BANNER_SLIDER
            1 -> STRIP_AD_BANNER
            2 -> HORIZONTAL_PRODUCT_VIEW
            3 -> GRID_PRODUCT_VIEW
            else -> -1
        }
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BANNER_SLIDER -> {
                val bannerSliderView: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.sliding_ad_layout, viewGroup, false)
                BannerSliderViewHolder(bannerSliderView)
            }
            STRIP_AD_BANNER -> {
                val stripAddView: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.strip_ad_layout, viewGroup, false)
                StripAdBannerViewHolder(stripAddView)
            }
            HORIZONTAL_PRODUCT_VIEW -> {
                val horizontalProductView: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.horizontal_scroll_layout, viewGroup, false)
                HorizontalProductViewHolder(horizontalProductView)
            }
            GRID_PRODUCT_VIEW -> {
                val gridProductView: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.grid_product_layout, viewGroup, false)
                GridProductViewHolder(gridProductView)
            }
            else -> return null!!
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (homePageModelList[position].type) {
            BANNER_SLIDER -> {
                val sliderModelList: List<SliderModel>? = homePageModelList[position].sliderModelList
                if (sliderModelList != null) {
                    (viewHolder as BannerSliderViewHolder?)!!.setBannerSliderViewPager(sliderModelList)
                }
            }
            STRIP_AD_BANNER -> {
                val resource = homePageModelList[position].resource
                val color = homePageModelList[position].backgroundColor
                (viewHolder as StripAdBannerViewHolder?)!!.setStripAd(resource, color)
            }
            HORIZONTAL_PRODUCT_VIEW -> {
                val horizontalLayoutTitle = homePageModelList[position].title
                val horizontalProductScrollModelList: List<HorizontalProductScrollModel>? = homePageModelList[position].horizontalProductScrollModelList
                if (horizontalProductScrollModelList != null) {
                    (viewHolder as HorizontalProductViewHolder?)!!.setHorizontalProductLayout(horizontalProductScrollModelList, horizontalLayoutTitle)
                }
            }
            GRID_PRODUCT_VIEW -> {
                val gridlayoutTitle = homePageModelList[position].title
                val gridProductScrollModelList: List<HorizontalProductScrollModel>? = homePageModelList[position].horizontalProductScrollModelList
                (viewHolder as GridProductViewHolder?)!!.setGridProductLayout(gridProductScrollModelList!!, gridlayoutTitle)
            }
            else -> return
        }
        if (lastposition < position) {
            val animation = AnimationUtils.loadAnimation(viewHolder.itemView.context, R.anim.fade_in)
            viewHolder.itemView.animation = animation
            lastposition = position
        }
    }

    override fun getItemCount(): Int {
        return if(homePageModelList.size>8) 8 else homePageModelList.size

    }

    // BannerSliderViewHolder class
    inner class BannerSliderViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val sliderViewPager: ViewPager = itemView.findViewById(R.id.bannerSliderViewPager)
        private var currentPage = 0
        private var timer: Timer? = null
        private val DELAY_TIME: Long = 3000
        private val PERIOD_TIME: Long = 3000

        fun setBannerSliderViewPager(sliderModelList: List<SliderModel>) {

            val sliderAdapter = SliderAdapter(sliderModelList)
            sliderViewPager.adapter = sliderAdapter
            sliderViewPager.clipToPadding = false
            sliderViewPager.pageMargin = 20

            sliderViewPager.currentItem = currentPage

            sliderViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(sliderModelList)
                    }
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    currentPage = position
                }
            })

            startBannerSlideShow(sliderModelList)

            sliderViewPager?.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
                    Log.d("banner", "inside onTouch()")
                    pageLooper(sliderModelList)
                    stopBannerSlideShow()

                    if (event?.action == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(sliderModelList)
                    }
                    return false
                }
            })
        }

        private fun pageLooper(sliderModelList: List<SliderModel>?) {
            if (currentPage == sliderModelList!!.size - 2) {
                currentPage = 2
                sliderViewPager.setCurrentItem(currentPage, false)
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size - 3
                sliderViewPager.setCurrentItem(currentPage, false)
            }
        }

        private fun startBannerSlideShow(sliderModelList: List<SliderModel>?) {
            val handler = Handler()
            val update = Runnable {
                if (currentPage >= sliderModelList!!.size) {
                }
                sliderViewPager.setCurrentItem(currentPage++, true)
            }
            timer = Timer()
            timer!!.schedule(object : TimerTask() {
                override fun run() {
                    handler.post(update)
                }
            }, DELAY_TIME, PERIOD_TIME)
        }

        private fun stopBannerSlideShow() {
            timer!!.cancel()
        }

    }

    // StripAdBannerViewHolder class
    inner class StripAdBannerViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val stripAdImage: ImageView = itemView.findViewById(R.id.stripAdImage)
        private val stripAdContainer: ConstraintLayout = itemView.findViewById(R.id.stripAdContainer)

        fun setStripAd(resource: Int, color: String?) {
            stripAdImage.setImageResource(resource)
            stripAdContainer.setBackgroundColor(Color.parseColor(color))
        }

    }

    inner class HorizontalProductViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val horizontalLayoutTitle: TextView = itemView.findViewById(R.id.horizontal_scroll_layout_title)
        private val horizontalViewAllBtn: Button = itemView.findViewById(R.id.horizontal_scroll_layout_view_all)
        private val horizontalRecyclerView: RecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview)

        fun setHorizontalProductLayout(horizontalProductScrollModelList: List<HorizontalProductScrollModel>, title: String?) {

            horizontalLayoutTitle.text = title

            if(horizontalProductScrollModelList.size > 8){
                horizontalViewAllBtn.visibility = VISIBLE
            }else{
                horizontalViewAllBtn.visibility = INVISIBLE
            }

            val horizontalProductScrollAdapter = HorizontalProductScrollAdapter(horizontalProductScrollModelList)
            val linearLayoutManager = LinearLayoutManager(itemView.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            horizontalRecyclerView.layoutManager = linearLayoutManager
            horizontalRecyclerView.adapter = horizontalProductScrollAdapter
            horizontalProductScrollAdapter.notifyDataSetChanged()
        }

    }

    inner class GridProductViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gridLayoutTitle: TextView = itemView.findViewById(R.id.grid_product_layout_title)
        private val gridLayoutViewAllButton: Button = itemView.findViewById(R.id.grid_product_layout_view_all_btn)
        private val gridView: GridView = itemView.findViewById(R.id.grid_product_layout_gridView)

        fun setGridProductLayout(horizontalProductScrollModelList: List<HorizontalProductScrollModel>, title: String?) {

            gridLayoutTitle.text = title
            gridView.adapter = GridProductLayoutAdapter(horizontalProductScrollModelList)
        }

    }

}
