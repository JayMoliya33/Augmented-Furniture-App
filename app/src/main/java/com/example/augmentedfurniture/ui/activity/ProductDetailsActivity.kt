package com.example.augmentedfurniture.ui.activity

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.ProductDetailsAdapter
import com.example.augmentedfurniture.adapter.ProductImagesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    lateinit var productImagesViewPager : ViewPager
    private lateinit var viewPageIndicator : TabLayout
    lateinit var addToWishlistButton: FloatingActionButton
    private var ALREADY_ADDED_TO_WISHLISTS : Boolean = false

    private lateinit var productDetailsViewPager: ViewPager
    private lateinit var productDetailsTabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        toolbar = findViewById(R.id.toolbarCategory)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Product Details"

        productImagesViewPager = findViewById(R.id.product_images_viewpager)
        viewPageIndicator = findViewById(R.id.viewPager_indicator)
        addToWishlistButton = findViewById(R.id.add_to_wishlist_button)
        productDetailsViewPager = findViewById(R.id.product_details_viewpager)
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout)

        // demo product list
        val productImages : MutableList<Int> = ArrayList()

        productImages.add(R.drawable.images)
        productImages.add(R.drawable.product_image_demo)
        productImages.add(R.drawable.banner2)
        productImages.add(R.drawable.banner)

        val productImagesAdapter = ProductImagesAdapter(productImages)
        productImagesViewPager.adapter = productImagesAdapter

        // setViewPager
        viewPageIndicator.setupWithViewPager(productImagesViewPager,true)

        // click on AddtoWishlist button
        addToWishlistButton.setOnClickListener {
            if(ALREADY_ADDED_TO_WISHLISTS){

                ALREADY_ADDED_TO_WISHLISTS = false
                addToWishlistButton.supportImageTintList = ColorStateList.valueOf(Color.parseColor("#9e9e9e"))
            }else{

                ALREADY_ADDED_TO_WISHLISTS = true
                addToWishlistButton.supportImageTintList = resources.getColorStateList(R.color.themeColor)
            }
        }

        // Product Details
        productDetailsViewPager.adapter = ProductDetailsAdapter(supportFragmentManager,productDetailsTabLayout.tabCount)

        productDetailsViewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout){})
        productDetailsTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
               // tab?.position?.let { productDetailsViewPager.currentItem = it }
                productDetailsViewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate Menu
        menuInflater.inflate(R.menu.search_and_cart_icon,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle ActionBar Item click

        when (item.itemId) {
            R.id.main_search_icon -> {
                // search
                return true
            }
            R.id.main_cart_icon -> {
                // cart
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}