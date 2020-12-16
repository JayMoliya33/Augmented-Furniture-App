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
import com.example.augmentedfurniture.model.CategoryModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment(){

    private var categoryModelFakeList: MutableList<CategoryModel> = ArrayList<CategoryModel>()
    private var categoryAdapter: CategoryAdapter? = null
    private var categoryRecyclerView: RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview)

        setupRecyclerView()

        return view

    }

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