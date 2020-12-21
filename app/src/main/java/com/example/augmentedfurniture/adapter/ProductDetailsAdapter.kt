package com.example.augmentedfurniture.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.augmentedfurniture.ui.fragment.ProductDescriptionFragment
import com.example.augmentedfurniture.ui.fragment.ProductSpecificationFragment

class ProductDetailsAdapter(fm: FragmentManager, private val totalTab: Int ) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> {
                val productDescriptionFragment1 = ProductDescriptionFragment()
                productDescriptionFragment1
            }
            1 -> {
                val productSpecificationFragment = ProductSpecificationFragment()
                productSpecificationFragment
            }
            2 -> {
                val productDescriptionFragment2 = ProductDescriptionFragment()
                productDescriptionFragment2
            }
            else -> null!!
        }
    }

    // return total No of Tabs
    override fun getCount(): Int {
        return totalTab
    }
}