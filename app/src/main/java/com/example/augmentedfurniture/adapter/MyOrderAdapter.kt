package com.example.augmentedfurniture.adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.MyOrderItemModel
import com.example.augmentedfurniture.ui.activity.OrderDetailsActivity

class MyOrderAdapter(private val myOrderItemModelList: List<MyOrderItemModel>) : RecyclerView.Adapter<MyOrderAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_order_item_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageResource: Int = myOrderItemModelList[position].productImage
        val rating: Int = myOrderItemModelList[position].rating
        val deliveredDate: String = myOrderItemModelList[position].deliveryStatus
        val title: String = myOrderItemModelList[position].productTitle

        holder.setData(imageResource, title, deliveredDate, rating,position)
    }

    override fun getItemCount(): Int {
        return myOrderItemModelList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val productImage: ImageView = itemView.findViewById(R.id.product_image)
        private val productTitle: TextView = itemView.findViewById(R.id.product_title)
        private val deliveryStatus: TextView = itemView.findViewById(R.id.order_delivered_date)
        private val orderIndicator: ImageView = itemView.findViewById(R.id.order_indicator)
        private val rateNowContainer: LinearLayout = itemView.findViewById(R.id.rate_now_container)

        fun setData(imageResource : Int ,title : String,deliveredDate : String, rating : Int, position: Int){
            productImage.setImageResource(imageResource)

            productTitle.text = title

            if(deliveredDate == "Cancelled")
            {
                orderIndicator.imageTintList = ColorStateList.valueOf(itemView.context.getColor(R.color.red))
            }else{
                orderIndicator.imageTintList = ColorStateList.valueOf(itemView.context.getColor(R.color.successGreen))
            }

            deliveryStatus.text = deliveredDate

            setRating(rating)
            for (i in 0 until rateNowContainer.childCount) {
                rateNowContainer.getChildAt(i).setOnClickListener {
                    setRating(i)
                }
            }

            if(position != 0) {
                itemView.setOnClickListener {
                    val orderDetailsIntent =
                        Intent(itemView.context, OrderDetailsActivity::class.java)
                    itemView.context.startActivity(orderDetailsIntent)
                }
            }
        }

        private fun setRating(starPosition : Int){
            for (x in 0 until rateNowContainer.childCount) {
                val starButton = rateNowContainer.getChildAt(x) as ImageView
                starButton.imageTintList = ColorStateList.valueOf(Color.parseColor("#bebebe"))
                if (x <= starPosition) {
                    // set color of star to yellow
                    starButton.imageTintList = ColorStateList.valueOf(Color.parseColor("#ffbb00"))
                }
            }
        }

    }
}