package com.example.augmentedfurniture.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter

class ProductImagesAdapter(var productImages: List<Int>) : PagerAdapter() {

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {

        val productImage = ImageView(container.context)
        productImage.setImageResource(productImages[position])
        container.addView(productImage, 0)
        return productImage
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView?)
    }

    override fun getCount(): Int {
        return productImages.size
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
        return view === `object`
    }
}