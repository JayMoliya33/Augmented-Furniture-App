package com.example.augmentedfurniture.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.HorizontalProductScrollModel
import com.example.augmentedfurniture.ui.activity.ProductDetailsActivity

class HorizontalProductScrollAdapter(private val horizontalProductScrollModelList: List<HorizontalProductScrollModel>) : RecyclerView.Adapter<HorizontalProductScrollAdapter.MyViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.horizontal_scroll_item_layout, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull viewHolder: MyViewHolder, position: Int) {
        val resource: Int = horizontalProductScrollModelList[position].productImage
        val title: String = horizontalProductScrollModelList[position].productTitle
        val description: String = horizontalProductScrollModelList[position].productDescription
        val price: String = horizontalProductScrollModelList[position].productPrice

        viewHolder.setData(resource, title, description, price)
    }

    override fun getItemCount(): Int {
        return horizontalProductScrollModelList.size
    }

    inner class MyViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val productImage: ImageView = itemView.findViewById(R.id.h_s_product_image)
        private val productTitle: TextView = itemView.findViewById(R.id.h_s_product_title)
        private val productDescription: TextView = itemView.findViewById(R.id.h_s_product_description)
        private val productPrice: TextView = itemView.findViewById(R.id.h_s_product_price)



        // setData
        fun setData(resource: Int, title: String, description: String, price: String) {
            productImage.setImageResource(resource)
            productTitle.text = title
            productDescription.text = description
            productPrice.text = price

            itemView.setOnClickListener {
                val intent = Intent(itemView.context,ProductDetailsActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }

    }

}