package com.silverknife.meizhi.mvp.model

class GankItemModel {
    var _id = ""
    var createdAt = ""
    var desc: String = ""
        get() = field
    var publishedAt = ""
        get() = field
    var source = ""
    var type = ""
    var url = ""
    var who: String = ""
        get() = field
    var images: ArrayList<String>? = null
    var used = false
}