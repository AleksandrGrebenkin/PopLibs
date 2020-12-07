package com.github.aleksandrgrebenkin.poplibs.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.aleksandrgrebenkin.poplibs.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .circleCrop()
            .into(container)
    }
}