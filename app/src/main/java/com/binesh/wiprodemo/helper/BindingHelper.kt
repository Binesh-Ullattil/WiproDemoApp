package com.binesh.wiprodemo.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.binesh.wiprodemo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BindingHelper {

    companion object {

        @JvmStatic
        @BindingAdapter("setFeedImage")
        fun setFeedImage(imageView: ImageView, imagePath: String?) {
            val context = imageView.context
            Glide.with(context)
                .load(imagePath)
                .apply(
                    RequestOptions().centerInside().error(R.drawable.ic_gallery_default).placeholder(
                        R.drawable.ic_gallery_default
                    )
                )
                .into(imageView)
        }
    }
}