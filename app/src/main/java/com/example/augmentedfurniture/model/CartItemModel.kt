package com.example.augmentedfurniture.model

class CartItemModel {
    var type: Int

    /// CART ITEM
    var productImage: Int = 0
    var productTitle: String? = null
    var freeCoupons: Int = 0
    var productPrice: String? = null
    var cuttedPrice: String? = null
    var productQuantity: Int = 0
    var offersApplied: Int = 0
    var couponsApplied: Int = 0

    constructor(
        type: Int,
        productImage: Int,
        productTitle: String?,
        freeCoupons: Int,
        productPrice: String?,
        cuttedPrice: String?,
        productQuantity: Int,
        offersApplied: Int,
        couponsApplied: Int
    ) {
        this.type = type
        this.productImage = productImage
        this.productTitle = productTitle
        this.freeCoupons = freeCoupons
        this.productPrice = productPrice
        this.cuttedPrice = cuttedPrice
        this.productQuantity = productQuantity
        this.offersApplied = offersApplied
        this.couponsApplied = couponsApplied
    }

    //// CART ITEM  & CART TOTAL
    var totalItems: String? = null
    var totalItemPrice: String? = null
    var deliveryPrice: String? = null

    /// CART TOTAL
    var savedAmount: String? = null
    var totalAmount: String? = null

    constructor(
        type: Int,
        totalItems: String?,
        totalItemPrice: String?,
        deliveryPrice: String?,
        totalAmount: String?,
        savedAmount: String?
    ) {
        this.type = type
        this.totalItems = totalItems
        this.totalItemPrice = totalItemPrice
        this.deliveryPrice = deliveryPrice
        this.totalAmount = totalAmount
        this.savedAmount = savedAmount
    }

    companion object {
        const val CART_ITEM = 0
        const val TOTAL_AMOUNT = 1
    }
}