package com.github.aleksandrgrebenkin.poplibs.di.modules

import android.widget.ImageView
import com.github.aleksandrgrebenkin.poplibs.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.poplibs.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageModule {

    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}