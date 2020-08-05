package com.coffeetime.simplenetworkrequest

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.coffeetime.simplenetworkrequest.network.Flower
import com.coffeetime.simplenetworkrequest.ui.landing.UserApiStatus
import com.coffeetime.simplenetworkrequest.ui.overview.FlowerApiStatus
import com.coffeetime.simplenetworkrequest.ui.overview.PhotoGridAdapter

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("profilePicture")
        fun bindImage(imgView: ImageView, imgUrl: String?) {
            imgUrl?.let {
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )

                    .into(imgView)
            }
        }

        @JvmStatic
        @BindingAdapter("name")
        fun TextView.setName(item: Flower) {
            text = item.name
        }

        @JvmStatic
        @BindingAdapter("listData")
        fun bindRecyclerView(
            recyclerView: RecyclerView,
            data: List<Flower>?
        ) {
            val adapter = recyclerView.adapter as PhotoGridAdapter
            adapter.submitList(data)
        }

        @JvmStatic
        @BindingAdapter("flowerApiStatus")
        fun bindStatus(
            statusImageView: ImageView,
            status: FlowerApiStatus?
        ) {
            when (status) {
                FlowerApiStatus.LOADING -> {
                    statusImageView.visibility = View.VISIBLE
                    statusImageView.setImageResource(R.drawable.loading_animation)

                }
                FlowerApiStatus.ERROR -> {
                    statusImageView.visibility = View.VISIBLE
                    statusImageView.setImageResource(R.drawable.ic_connection_error)
                }
                FlowerApiStatus.DONE -> {
                    statusImageView.visibility = View.GONE

                }
            }
        }

        @JvmStatic
        @BindingAdapter("flowerApiStatus")
        fun bindStatus(
            progressBar: ProgressBar,
            status: FlowerApiStatus?
        ) {
            when (status) {
                FlowerApiStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE

                }
                FlowerApiStatus.ERROR -> {
                    progressBar.visibility = View.GONE
                }
                FlowerApiStatus.DONE -> {
                    progressBar.visibility = View.GONE

                }
            }
        }


    }


}