package com.example.augmentedfurniture.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.MyOrderAdapter
import com.example.augmentedfurniture.model.MyOrderItemModel

class MyOrdersFragment : Fragment() {

    lateinit var myOrderRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_orders, container, false)

        myOrderRecyclerView = view.findViewById(R.id.my_orders_recyclerview)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        myOrderRecyclerView.layoutManager = linearLayoutManager

        val myOrderItemModelList : MutableList<MyOrderItemModel> = ArrayList()
        myOrderItemModelList.add(MyOrderItemModel(R.drawable.image,2,"Brown Leather Sofa","Delivered on Mon,15th Jan 2021"))
        myOrderItemModelList.add(MyOrderItemModel(R.drawable.product_image_demo,5,"Brown Leather","Delivered on Mon,15th Jan 2021"))
        myOrderItemModelList.add(MyOrderItemModel(R.drawable.image,3,"Brown Leather","Cancelled"))

        val myOrderAdapter = MyOrderAdapter(myOrderItemModelList)
        myOrderRecyclerView.adapter = myOrderAdapter
        myOrderAdapter.notifyDataSetChanged()

        return view
    }

}