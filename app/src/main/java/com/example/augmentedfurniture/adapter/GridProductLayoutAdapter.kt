package com.example.augmentedfurniture.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.HorizontalProductScrollModel
import com.example.augmentedfurniture.ui.activity.ProductDetailsActivity

class GridProductLayoutAdapter(private val horizontalProductScrollModelList: List<HorizontalProductScrollModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_scroll_item_layout, null)
            view.elevation = 0.0f
            view.setBackgroundColor(Color.parseColor("#ffffff"))

            // onclick listener for Grid Products
            view.setOnClickListener {
                val productDetailsIntent = Intent(parent.context,ProductDetailsActivity::class.java)
                parent.context.startActivity(productDetailsIntent)
            }

            val productImages : ImageView = view.findViewById(R.id.h_s_product_image)
            val productTitle: TextView = view.findViewById(R.id.h_s_product_title)
            val productDescription: TextView = view.findViewById(R.id.h_s_product_description)
            val productPrice: TextView = view.findViewById(R.id.h_s_product_price)

            productImages.setImageResource(horizontalProductScrollModelList[position].productImage)
            productTitle.text = horizontalProductScrollModelList[position].productTitle
            productDescription.text = horizontalProductScrollModelList[position].productDescription
            productPrice.text = horizontalProductScrollModelList[position].productPrice

        } else {
            view = convertView
        }
        return view
    }

}