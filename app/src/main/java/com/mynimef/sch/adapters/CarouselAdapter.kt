package com.mynimef.sch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mynimef.sch.R
import com.mynimef.sch.models.Carousel

class CarouselAdapter: RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    private var dataSet: Array<Carousel> = emptyArray()

    fun setData(data: Array<Carousel>) {
        this.dataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_carousel, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide
            .with(viewHolder.imageView)
            .load(dataSet[position].image)
            .centerCrop()
            .transform(CenterCrop(), RoundedCorners(40))
            .into(viewHolder.imageView)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imageView: ImageView

        init {
            imageView = view.findViewById(R.id.imageView)
        }
    }
}