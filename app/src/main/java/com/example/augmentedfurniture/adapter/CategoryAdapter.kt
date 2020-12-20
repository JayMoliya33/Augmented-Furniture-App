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
import com.example.augmentedfurniture.model.CategoryModel
import com.example.augmentedfurniture.ui.activity.CategoryActivity
import com.example.augmentedfurniture.ui.activity.HomeActivity

class CategoryAdapter(private val categoryModelList: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder?>() {

    private var lastposition = -1

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.category_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull viewHolder: MyViewHolder, position: Int) {
        val icon: String = categoryModelList[position].categoryIconLink
        val name: String = categoryModelList[position].categoryName

        viewHolder.setCategory(name)

//        if (lastposition < position) {
//            val animation: Animation = AnimationUtils.loadAnimation(viewHolder.itemView.context, R.anim.fade_in)
//            viewHolder.itemView.animation = animation
//            lastposition = position
//        }
    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }

    inner class MyViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val categoryIcon: ImageView = itemView.findViewById(R.id.category_icon)
        private val categoryName: TextView = itemView.findViewById(R.id.category_name)

        fun setCategoryIcon(iconUrl: String) {
//            if (iconUrl != "null") {
//                Glide.with(itemView.context).load(iconUrl).apply(RequestOptions().placeholder(R.mipmap.bell)).into(categoryIcon)
//            } else {
//                categoryIcon.setImageResource(R.mipmap.home_icon)
//            }
        }

        fun setCategory(name: String) {
            categoryName.text = name

            if (name != "") {
                itemView.setOnClickListener{
                        val categoryIntent = Intent(itemView.context, CategoryActivity::class.java)
                        categoryIntent.putExtra("CategoryName", name)
                        itemView.context.startActivity(categoryIntent)
                }
            }
        }

    }

}