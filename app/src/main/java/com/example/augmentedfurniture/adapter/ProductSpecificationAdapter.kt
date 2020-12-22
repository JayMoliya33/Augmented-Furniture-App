package com.example.augmentedfurniture.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.ProductSpecificationModel

class ProductSpecificationAdapter(private val productSpecificationModelList: List<ProductSpecificationModel>) :
    RecyclerView.Adapter<ProductSpecificationAdapter.MyViewHolder?>() {


    override fun getItemViewType(position: Int): Int {
        return when (productSpecificationModelList[position].type) {
            0 -> ProductSpecificationModel.SPECIFICATION_TITLE
            1 -> ProductSpecificationModel.SPECIFICATION_BODY
            else -> -1
        }
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        return when (viewType) {
            ProductSpecificationModel.SPECIFICATION_TITLE -> {
                // create textView Programmatically
                val title = TextView(viewGroup.context)
                title.setTypeface(null, Typeface.BOLD)
                title.setTextColor(Color.parseColor("#000000"))
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(
                    setDp(16, viewGroup.context),
                    setDp(16, viewGroup.context),
                    setDp(16, viewGroup.context),
                    setDp(8, viewGroup.context)
                )
                title.layoutParams = layoutParams
                MyViewHolder(title)
            }
            ProductSpecificationModel.SPECIFICATION_BODY -> {
                val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.product_specification_item_layout, viewGroup, false)
                MyViewHolder(view)
            }
            else -> null!!
        }
    }

    override fun onBindViewHolder(@NonNull viewHolder: MyViewHolder, position: Int) {
        when (productSpecificationModelList[position].type) {

            ProductSpecificationModel.SPECIFICATION_TITLE ->
                viewHolder.setTitle(productSpecificationModelList[position].title)

            ProductSpecificationModel.SPECIFICATION_BODY -> {
                val featureTitle: String? = productSpecificationModelList[position].featureName
                val featureDetail: String? =
                    productSpecificationModelList[position].featureValue
                if (featureDetail != null) {
                    if (featureTitle != null) {
                        viewHolder.setFeatures(featureTitle, featureDetail)
                    }
                }
            }
            else -> return
        }
    }

    override fun getItemCount(): Int {
        return productSpecificationModelList.size
    }

    inner class MyViewHolder(@NonNull itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private var featureName: TextView? = null
        private var featureValue: TextView? = null
        private var title: TextView? = null

        fun setTitle(titleText: String?) {
            title = itemView as TextView?
            title!!.text = titleText
        }

        fun setFeatures(featureTitle: String, featureDetail: String) {
            featureName = itemView.findViewById(R.id.feature_name)
            featureValue = itemView.findViewById(R.id.feature_value)
            featureName!!.text = featureTitle
            featureValue!!.text = featureDetail
        }
    }

    // for TextView
    private fun setDp(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }
}