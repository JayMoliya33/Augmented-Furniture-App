package com.example.augmentedfurniture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.ProductSpecificationModel

class ProductSpecificationAdapter(private val productSpecificationModelList: List<ProductSpecificationModel>) :
    RecyclerView.Adapter<ProductSpecificationAdapter.MyViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.product_specification_item_layout,viewGroup,false)
        return MyViewHolder(view)
    }

   override fun onBindViewHolder(@NonNull viewHolder: MyViewHolder, position: Int) {
        val featureTitle :String = productSpecificationModelList[position].featureName
        val featureDetail :String = productSpecificationModelList[position].featureValue

       viewHolder.setFeatures(featureTitle,featureDetail)
    }

    override fun getItemCount(): Int {
        return productSpecificationModelList.size
    }

    inner class MyViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val featureName: TextView = itemView.findViewById(R.id.feature_name)
        private val featureValue: TextView =  itemView.findViewById(R.id.feature_value)

        fun setFeatures(featureTitle : String, featureDetail:String){
            featureName.text = featureTitle
            featureValue.text = featureDetail
        }
    }
}