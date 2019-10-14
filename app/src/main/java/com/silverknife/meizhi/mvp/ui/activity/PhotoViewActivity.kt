package com.silverknife.meizhi.mvp.ui.activity

import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silvericekey.skutilslibrary.utils.ImageLoderUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.PhotoViewPresenter
import kotlinx.android.synthetic.main.activity_gank_detail.*
import kotlinx.android.synthetic.main.gank_list_item.*
import kotlinx.android.synthetic.main.gank_list_item.content_title

class PhotoViewActivity : BaseActivity<PhotoViewPresenter>() {
    companion object {
        val IMAGE = "image"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_photo_view
    }

    override fun initView() {
        var title = intent.getStringExtra("title")
        if (!TextUtils.isEmpty(title)) {
            content_title.text = title
        } else {
            content_title.text = "图片"
        }
        back.setOnClickListener { ActivityCompat.finishAfterTransition(this) }
        var url = intent.getStringExtra("url")
        ImageLoderUtil.bindImg(this, url, ImageLoderUtil.Builder().build()).into(image)
        addTransitionName(image, IMAGE)
    }

    override fun initPresenter(): PhotoViewPresenter {
        return PhotoViewPresenter()
    }
}