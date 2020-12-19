package com.example.augmentedfurniture.model

class HomePageModel {
    var type: Int

    ///////////////////////// BANNER SLIDER  /////////////////////////////////////////
    var sliderModelList: List<SliderModel>? = null

    constructor(type: Int, sliderModelList: List<SliderModel>?) {
        this.type = type
        this.sliderModelList = sliderModelList
    }
    ///////////////////////// BANNER SLIDER  /////////////////////////////////////////

    ////////////////////////////////STRIP AD  ////////////////////////////////////////////////
    var resource: Int = 0
    var backgroundColor: String? = null

    constructor(type: Int, resource: Int, backgroundColor: String?) {
        this.type = type
        this.resource = resource
        this.backgroundColor = backgroundColor
    }
    ////////////////////////////////STRIP AD  ////////////////////////////////////////////////

    /////////////////////////////HORIZONTAL PRODUCT VIEW &&  GRID PRODUCT LAYOUT
    var title: String? = null
    var horizontalProductScrollModelList: List<HorizontalProductScrollModel>? = null

    constructor(type: Int, horizontalProductScrollModelList: List<HorizontalProductScrollModel>?,title: String?) {
        this.type = type
        this.horizontalProductScrollModelList = horizontalProductScrollModelList
        this.title = title
    }
    /////////////////////////////HORIZONTAL PRODUCT VIEW &&  GRID PRODUCT LAYOUT

    companion object {
        const val BANNER_SLIDER = 0
        const val STRIP_AD_BANNER = 1
        const val HORIZONTAL_PRODUCT_VIEW = 2
        const val GRID_PRODUCT_VIEW = 3
    }
}
