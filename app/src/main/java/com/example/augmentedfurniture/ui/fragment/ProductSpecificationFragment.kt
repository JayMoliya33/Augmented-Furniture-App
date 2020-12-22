package com.example.augmentedfurniture.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.adapter.ProductSpecificationAdapter
import com.example.augmentedfurniture.model.ProductSpecificationModel

class ProductSpecificationFragment : Fragment() {

    private var productSpecificationRecyclerView : RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_specification, container, false)

        productSpecificationRecyclerView = view.findViewById(R.id.product_specification_recyclerview)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        productSpecificationRecyclerView?.layoutManager = linearLayoutManager

        val productSpecificationModelList : MutableList<ProductSpecificationModel> = ArrayList()
        productSpecificationModelList.add(ProductSpecificationModel(0,"General"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"Dimension"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))
        productSpecificationModelList.add(ProductSpecificationModel(1,"RAM","4GB"))

        val productSpecificationAdapter = ProductSpecificationAdapter(productSpecificationModelList)
        productSpecificationRecyclerView?.adapter = productSpecificationAdapter
        productSpecificationAdapter.notifyDataSetChanged()

        return view
    }

}