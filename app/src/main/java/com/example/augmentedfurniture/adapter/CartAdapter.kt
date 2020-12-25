package com.example.augmentedfurniture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.model.CartItemModel

class CartAdapter(private val cartItemModelList: List<CartItemModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (cartItemModelList[position].type) {
            0 -> CartItemModel.CART_ITEM
            1 -> CartItemModel.TOTAL_AMOUNT
            else -> -1
        }
    }

    @NonNull
    override fun onCreateViewHolder(
        @NonNull viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            CartItemModel.CART_ITEM -> {
                val cartItemView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.cart_item_layout, viewGroup, false)
                CartItemViewHolder(cartItemView)
            }
            CartItemModel.TOTAL_AMOUNT -> {
                val cartTotalView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.cart_total_amount_layout, viewGroup, false)
                CartTotalAmountViewHolder(cartTotalView)
            }
            else -> null!!
        }
    }

    override fun onBindViewHolder(@NonNull viewHolder: RecyclerView.ViewHolder, position: Int) {

        when (cartItemModelList[position].type) {

            CartItemModel.CART_ITEM -> {
                val resource: Int = cartItemModelList[position].productImage
                val title: String? = cartItemModelList[position].productTitle
                val freeCoupons: Int = cartItemModelList[position].freeCoupons
                val productPrice: String? = cartItemModelList[position].productPrice
                val cuttedPrice: String? = cartItemModelList[position].cuttedPrice
                val offersApplied: Int = cartItemModelList[position].offersApplied

                (viewHolder as CartItemViewHolder).setItemDetails(
                    resource,
                    title.toString(),
                    freeCoupons,
                    productPrice.toString(),
                    cuttedPrice.toString(),
                    offersApplied
                )
            }
            CartItemModel.TOTAL_AMOUNT -> {
                val totalItems: String? = cartItemModelList[position].totalItems
                val totalItemsPrice: String? = cartItemModelList[position].totalItemPrice
                val deliveryPrice: String? = cartItemModelList[position].deliveryPrice
                val totalAmount: String? = cartItemModelList[position].totalAmount
                val savedAmount: String? = cartItemModelList[position].savedAmount

                (viewHolder as CartTotalAmountViewHolder).setTotalAmount(
                    totalItems.toString(),
                    totalItemsPrice.toString(),
                    deliveryPrice.toString(),
                    totalAmount.toString(),
                    savedAmount.toString()
                )
            }
            else -> return
        }
    }

    override fun getItemCount(): Int {
        return cartItemModelList.size
    }

    // for CartItem
    internal inner class CartItemViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val productImage: ImageView = itemView.findViewById(R.id.product_image)
        private val freeCouponIcon: ImageView = itemView.findViewById(R.id.free_coupon_icon)
        private val productTitle: TextView = itemView.findViewById(R.id.product_title)
        private val freeCoupons: TextView = itemView.findViewById(R.id.tv_free_coupon)
        private val productPrice: TextView = itemView.findViewById(R.id.product_price)
        private val cuttedPrice: TextView = itemView.findViewById(R.id.cutted_price)
        private val offersApplied: TextView = itemView.findViewById(R.id.offers_applied)
        private val couponsApplied: TextView = itemView.findViewById(R.id.coupons_applied)
        private val productQuantity: TextView = itemView.findViewById(R.id.product_quantity)

        fun setItemDetails(
            resource: Int,
            title: String,
            freeCouponsNo: Int,
            productPriceText: String,
            cuttedPriceText: String,
            offerAppliedNo: Int
        ) {

            productImage.setImageResource(resource)
            productTitle.text = title
            if (freeCouponsNo > 0) {
                freeCouponIcon.visibility = View.VISIBLE
                freeCoupons.visibility = View.VISIBLE
                if (freeCouponsNo == 1) {
                    freeCoupons.text = "free $freeCouponsNo coupon"
                } else {
                    freeCoupons.text = "free $freeCouponsNo coupons"
                }
            } else {
                freeCouponIcon.visibility = View.INVISIBLE
                freeCoupons.visibility = View.INVISIBLE
            }
            productPrice.text = productPriceText
            cuttedPrice.text = cuttedPriceText
            if (offerAppliedNo > 0) {
                offersApplied.visibility = View.VISIBLE
                offersApplied.text = "$offerAppliedNo Offers Applied"
            } else {
                offersApplied.visibility = View.INVISIBLE
            }

            /*  productQuantity.setOnClickListener {
                  val quantityDialog = Dialog(itemView.context)
                  quantityDialog.setContentView(R.layout.quantity_dialog)
                  quantityDialog.window!!
                      .setLayout(
                          ViewGroup.LayoutParams.MATCH_PARENT,
                          ViewGroup.LayoutParams.WRAP_CONTENT
                      )
                  quantityDialog.setCancelable(false)
                  val quantityNo: EditText = quantityDialog.findViewById<EditText>(R.id.quantiy_no)
                  val cancelBtn = quantityDialog.findViewById<Button>(R.id.cancel_btn)
                  val okBtn = quantityDialog.findViewById<Button>(R.id.ok_btn)
                  cancelBtn.setOnClickListener { quantityDialog.dismiss() }
                  okBtn.setOnClickListener {
                      productQuantity.text = "Qty: " + quantityNo.getText()
                      quantityDialog.dismiss()
                  }
                  quantityDialog.show()
              }  */
        }

    }

    // for CartTotalAmout
    internal inner class CartTotalAmountViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val totalItems: TextView = itemView.findViewById(R.id.total_items)
        private val totalItemPrice: TextView = itemView.findViewById(R.id.total_items_price)
        private val deliveryPrice: TextView = itemView.findViewById(R.id.delivery_price)
        private val totalAmount: TextView = itemView.findViewById(R.id.total_price)
        private val savedAmount: TextView = itemView.findViewById(R.id.saved_amount)

        fun setTotalAmount(
            totalItemsText: String,
            totalItemsPriceText: String,
            deliveryPriceText: String,
            totalAmountText: String,
            savedAmountText: String
        ) {
            totalItems.text = totalItemsText
            totalItemPrice.text = totalItemsPriceText
            deliveryPrice.text = deliveryPriceText
            totalAmount.text = totalAmountText
            savedAmount.text = savedAmountText
        }

    }
}