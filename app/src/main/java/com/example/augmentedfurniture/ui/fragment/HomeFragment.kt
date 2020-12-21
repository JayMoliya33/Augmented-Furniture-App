package com.example.augmentedfurniture.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.CategoryAdapter
import com.example.augmentedfurniture.adapter.HomePageAdapter
import com.example.augmentedfurniture.model.CategoryModel
import com.example.augmentedfurniture.model.HomePageModel
import com.example.augmentedfurniture.model.HorizontalProductScrollModel
import com.example.augmentedfurniture.model.SliderModel

class HomeFragment : Fragment() {

    // CategoryItem
    private var categoryModelFakeList: MutableList<CategoryModel> = ArrayList()
    private var categoryAdapter: CategoryAdapter? = null
    private var categoryRecyclerView: RecyclerView? = null

    private var homePageRecyclerView : RecyclerView?= null

    private val sliderModelList : MutableList<SliderModel> = ArrayList()

    private val horizontalProductScrollModelList : MutableList<HorizontalProductScrollModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview)
        // set RecyclerView
        setupRecyclerView()

        // set BannerSlider
        setBannerSlider()

        ////Horizontal Product Layout
        setHorizontalRecyclerView()

        ///////////
         homePageRecyclerView = view.findViewById(R.id.home_page_recyclerView)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        homePageRecyclerView?.layoutManager = layoutManager

        val homePageModelList: MutableList<HomePageModel> = ArrayList()
        homePageModelList.add(HomePageModel(0, sliderModelList))
        homePageModelList.add(HomePageModel(1, R.drawable.s, "#000000"))
        homePageModelList.add(HomePageModel(2, horizontalProductScrollModelList, "Popular product"))
        homePageModelList.add(HomePageModel(3, horizontalProductScrollModelList,"DEALS OF THE DAY"))
        homePageModelList.add(HomePageModel(1, R.drawable.s, "#000000"))

        val adapter = HomePageAdapter(homePageModelList)
        homePageRecyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()

        return view
    }

    ///Horizontal Product Layout
    private fun setHorizontalRecyclerView() {

        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.image, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.image, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.red_mail, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.green_mail, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.home_icon, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.ic_launcher, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.drawable.image, "Brown Leather Sofa", "by Trevi Furniture", "₹ 17,799/-"))
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

    // setRecyclerView for Different CategoriesItem
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