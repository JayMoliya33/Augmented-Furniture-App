package com.example.augmentedfurniture.ui.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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


    //// RATINGS LAYOUT
    lateinit var rateNowContainer: LinearLayout
    private val totalRatings: TextView? = null
    private val ratingsNoContainer: LinearLayout? = null
    private val ratingsProgressBarContainer: LinearLayout? = null
    private val totalRatingsFig: TextView? = null

    //// RATINGS LAYOUT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        toolbar = findViewById(R.id.toolbarCategory)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Product Details"
        supportActionBar?.setDisplayShowTitleEnabled(true)

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
        viewPageIndicator.setupWithViewPager(productImagesViewPager, true)

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
        productDetailsViewPager.adapter = ProductDetailsAdapter(
            supportFragmentManager,
            productDetailsTabLayout.tabCount
        )

        productDetailsViewPager.addOnPageChangeListener(object :
            TabLayout.TabLayoutOnPageChangeListener(
                productDetailsTabLayout
            ) {})
        productDetailsTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // tab?.position?.let { productDetailsViewPager.currentItem = it }
                productDetailsViewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        // Rating Layout
        rateNowContainer = findViewById(R.id.rate_now_container)

        // onclick Listener for Diff stars
        for (i in 0 until rateNowContainer.childCount) {
            rateNowContainer.getChildAt(i).setOnClickListener {
                setRating(i)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate Menu
        menuInflater.inflate(R.menu.search_and_cart_icon, menu)
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

    ////// RATINGS LAYOUT
    private fun setRating(starPosition: Int) {

        for (x in 0 until rateNowContainer.childCount) {

            val starBtn = rateNowContainer.getChildAt(x) as ImageView

            starBtn.imageTintList = ColorStateList.valueOf(Color.parseColor("#bebebe"))
            if (x <= starPosition) {
                // set color of star to yellow
                starBtn.imageTintList = ColorStateList.valueOf(Color.parseColor("#ffbb00"))
            }

        }

    }
    ///// RATINGS LAYOUT
}