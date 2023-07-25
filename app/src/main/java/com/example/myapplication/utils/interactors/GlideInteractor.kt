package com.example.myapplication.utils.interactors

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes

interface GlideInteractor {
    fun loadImage(url: String?, imageView: ImageView, needCache: Boolean = true)
    fun loadImage(
        url: String?,
        imageView: ImageView,
        needCache: Boolean = true,
        width: Int,
        height: Int,
    )

    fun loadImageWithFallBack(
        url: String?,
        imageView: ImageView,
        needCache: Boolean = true,
        urlFallBack: String?,
    )

    fun loadImage(
        url: String?,
        imageView: ImageView,
        needCache: Boolean = true,
        fallBackUrl: String,
    )

    fun loadCircleImage(
        url: String?,
        imageView: ImageView,
        needCache: Boolean = true,
        @DrawableRes errorRes: Int? = null,
    )

    fun loadCircleImageInBottomBar(
        url: String?,
        imageView: ImageView,
        typeOfBar: String,
        needCache: Boolean = true,
        @DrawableRes errorRes: Int? = null,
    )

    fun loadBlurImage(url: String?, imageView: ImageView, needCache: Boolean = true)
    fun loadImage(res: Int, imageView: ImageView)
    fun loadImageCenterCrop(
        url: String?,
        imageView: ImageView,
        needCache: Boolean,
        width: Int,
        height: Int,
    )

    fun loadImageCenterCrop(url: String?, imageView: ImageView, needCache: Boolean)
    fun loadImageCenterCropWithoutDimensions(url: String?, imageView: ImageView, needCache: Boolean)
    fun loadImageWithoutCenterCrop(
        url: String?,
        imageView: ImageView,
        needCache: Boolean,
        width: Int,
        height: Int,
    )

    fun clear(imageView: ImageView)
    fun clearMemoryCache()
    suspend fun cleadDiskCache()
}
