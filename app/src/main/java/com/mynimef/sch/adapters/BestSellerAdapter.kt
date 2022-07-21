package com.mynimef.sch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mynimef.sch.R
import com.mynimef.sch.models.BookInfo

class BestSellerAdapter(private val onClick: (BookInfo) -> Unit):
    RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    private var dataSet: Array<BookInfo> = emptyArray()

    fun setData(data: Array<BookInfo>) {
        this.dataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_best_seller, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = dataSet[position]

        Glide
            .with(viewHolder.imageView)
            .load(data.image)
            .centerCrop()
            .transform(CenterCrop(), RoundedCorners(40))
            .into(viewHolder.imageView)

        viewHolder.textName.text = data.title
        viewHolder.textAuthor.text = data.author
        viewHolder.textPrice.text = data.price.toString() + " €"
        viewHolder.textRating.text = data.rate.score.toString()
        viewHolder.textAmount.text = "(" + data.rate.amount.toString() + ")"

        viewHolder.itemView.setOnClickListener {
            onClick(data)
        }
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imageView: ImageView
        val textName: TextView
        val textAuthor: TextView
        val textPrice: TextView
        val textRating: TextView
        val textAmount: TextView

        init {
            imageView  = view.findViewById(R.id.imageView)
            textName   = view.findViewById(R.id.textName)
            textAuthor = view.findViewById(R.id.textAuthor)
            textPrice  = view.findViewById(R.id.textPrice)
            textRating = view.findViewById(R.id.textRating)
            textAmount = view.findViewById(R.id.textAmount)
        }
    }
}