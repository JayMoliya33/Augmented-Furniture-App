package com.example.augmentedfurniture.model

class ProductSpecificationModel {
    var type: Int

    ///// SPECIFICATION TITLE
    var title: String? = null

    constructor(type: Int, title: String?) {
        this.type = type
        this.title = title
    }

    ///// SPECIFICATION TITLE
    ///// SPECIFICATION BODY
    var featureName: String? = null

    ///// SPECIFICATION BODY
    var featureValue: String? = null

    constructor(type: Int, featureName: String?, featureValue: String?) {
        this.type = type
        this.featureName = featureName
        this.featureValue = featureValue
    }

    companion object {
        const val SPECIFICATION_TITLE = 0
        const val SPECIFICATION_BODY = 1
    }
}
