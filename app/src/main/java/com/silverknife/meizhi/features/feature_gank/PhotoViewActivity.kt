package com.silverknife.meizhi.features.feature_gank

import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silvericekey.skutilslibrary.utils.ImageLoderUtil
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.activity_gank_detail.back
import kotlinx.android.synthetic.main.activity_photo_view.*
import kotlinx.android.synthetic.main.item_gank_list.content_title
import kotlinx.android.synthetic.main.item_gank_list.image
import me.jessyan.autosize.utils.AutoSizeUtils

class PhotoViewActivity : BaseActivity<PhotoViewPresenter>() {
    companion object {
        val IMAGE = "image"
        val TITLE = "title"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_photo_view
    }

    override fun initTransitionViews() {
        addTransitionName(image, IMAGE)
//        addTransitionName(content_title, TITLE)
    }

    override fun initView() {
        openSlideToFinish = true
        var title = intent.getStringExtra("title")
        if (!TextUtils.isEmpty(title)) {
            content_title.text = title
        } else {
            content_title.text = "图片"
        }
        back.setOnClickListener { ActivityCompat.finishAfterTransition(this) }
        image.isEnabled = true
        var url = intent.getStringExtra("url")
        ImageLoderUtil.getInstance().bindImg(image.context, url, ImageLoderUtil.Builder().build()).override(AutoSizeUtils.dp2px(this, 360f), AutoSizeUtils.dp2px(this, 480f)).into(image)
        slide_back_layout.iView = this
    }

    override fun initPresenter(): PhotoViewPresenter {
        return PhotoViewPresenter()
    }
}