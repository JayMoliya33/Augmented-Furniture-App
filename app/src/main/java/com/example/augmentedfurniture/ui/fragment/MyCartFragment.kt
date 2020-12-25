package com.example.augmentedfurniture.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.CartAdapter
import com.example.augmentedfurniture.model.CartItemModel

class MyCartFragment : Fragment() {

    private var cartItemRecyclerView : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_my_cart, container, false)

        cartItemRecyclerView = view.findViewById(R.id.cart_items_recyclerview)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        cartItemRecyclerView?.layoutManager = linearLayoutManager

        val cartItemModelList : MutableList<CartItemModel> = ArrayList()
        cartItemModelList.add(CartItemModel(0,R.drawable.image,"Brown Leather Sofa",2,"Rs. 35000","Rs. 1500",1,0,0))
        cartItemModelList.add(CartItemModel(0,R.drawable.image,"Brown Leather Sofa",0,"Rs. 35000","Rs. 1500",1,1,0))
        cartItemModelList.add(CartItemModel(1,"Price (2 Items)","Rs. 35000/-","Free","Rs. 35000","Rs. 599/-"))

        val cartAdapter = CartAdapter(cartItemModelList)
        cartItemRecyclerView?.adapter = cartAdapter
        cartAdapter.notifyDataSetChanged()

        return view
    }

}