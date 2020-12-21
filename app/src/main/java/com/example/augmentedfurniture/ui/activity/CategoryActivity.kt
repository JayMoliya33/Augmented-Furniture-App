package com.example.augmentedfurniture.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.HomePageAdapter
import com.example.augmentedfurniture.model.HomePageModel
import com.example.augmentedfurniture.model.HorizontalProductScrollModel
import com.example.augmentedfurniture.model.SliderModel

class CategoryActivity : AppCompatActivity() {

    private var categoryRecyclerView : RecyclerView? = null

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private val sliderModelList : MutableList<SliderModel> = ArrayList()

    private val horizontalProductScrollModelList : MutableList<HorizontalProductScrollModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        toolbar = findViewById(R.id.toolbarCategory)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("CategoryName")
        supportActionBar?.title = title

        categoryRecyclerView = findViewById(R.id.category_recyclerview)

        // set BannerSlider
        setBannerSlider()

        ////Horizontal Product Layout
        setHorizontalRecyclerView()

        ///////////
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        categoryRecyclerView?.layoutManager = layoutManager

        val homePageModelList: MutableList<HomePageModel> = ArrayList()
        homePageModelList.add(HomePageModel(0, sliderModelList))
        homePageModelList.add(HomePageModel(1, R.drawable.s, "#000000"))
        homePageModelList.add(HomePageModel(2, horizontalProductScrollModelList, "Popular product"))
        homePageModelList.add(HomePageModel(3, horizontalProductScrollModelList,"DEALS OF THE DAY"))
        homePageModelList.add(HomePageModel(1, R.drawable.s, "#000000"))

        val adapter = HomePageAdapter(homePageModelList)
        categoryRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    ///Horizontal Product Layout
    private fun setHorizontalRecyclerView() {

        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.images, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.images, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.red_mail, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.green_mail, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.home_icon, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.ic_launcher, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.images, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.images, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))

    }

    ////// BANNER slider
    private fun setBannerSlider() {

        sliderModelList.add(SliderModel(R.mipmap.home_icon, "#077AE4"))
        sliderModelList.add(SliderModel(R.drawable.banner, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.green_mail, "#077AE4"))

        sliderModelList.add(SliderModel(R.mipmap.red_mail, "#077AE4"))
        sliderModelList.add(SliderModel(R.drawable.ic_baseline_email, "#077AE4"))
        sliderModelList.add(SliderModel(R.drawable.add, "#077AE4"))
        sliderModelList.add(SliderModel(R.drawable.banner2, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.banner, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.home_icon, "#077AE4"))

        sliderModelList.add(SliderModel(R.drawable.banner, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.green_mail, "#077AE4"))
        sliderModelList.add(SliderModel(R.mipmap.red_mail, "#077AE4"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate Menu
        menuInflater.inflate(R.menu.search_icon,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle ActionBar Item click
        val id : Int = item.itemId

        if(id == R.id.searchCategory){
            return true
        }else if(id == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}