package com.example.augmentedfurniture.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.SliderModel

class SliderAdapter(private val sliderModelList: List<SliderModel>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(container.context).inflate(R.layout.slider_layout, container, false)

        val bannerContainer : ConstraintLayout = view.findViewById(R.id.banner_container)
        bannerContainer.backgroundTintList = ColorStateList.valueOf(Color.parseColor(sliderModelList.get(position).backgroundColor))

        val banner : ImageView = view.findViewById(R.id.banner_slide)
        banner.setImageResource(sliderModelList.get(position).banner)
        container.addView(view,0)

        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return sliderModelList.size
    }
}