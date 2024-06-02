package com.example.timeconversionapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timeconversionapplication.databinding.ListPlaceBinding
import com.example.timeconversionapplication.databinding.ListProductBinding

class MyAdapter(private var dataSet: MutableList<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_PLACE = 1
    private val VIEW_TYPE_PRODUCT = 2

    inner class PlaceViewHolder(val binding: ListPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: WorkPlace) {
            binding.placeName.text = place.place_name
        }
    }

    inner class ProductViewHolder(val binding: ListProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.memo.text = product.memo
            binding.productName.text = product.product_name
        }
    }

//    inner class ProductViewHolder(val binding: ListProductBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(product: Product, dday: Dday) {
//            binding.memo.text = product.memo
//            binding.productName.text = product.product_name
//            binding.dday.text = dday.Dday.toString()
//        }
//    }

//    inner class ProductViewHolder(val binding: ListProductBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: Any) {
//            if (data is Product && data.dday != null) {
//                binding.memo.text = data.memo
//                binding.productName.text = data.product_name
//                binding.dday.text = data.dday.Dday.toString()
//            }
//        }
//    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataSet[position]) {
            is WorkPlace -> VIEW_TYPE_PLACE
            is Product -> VIEW_TYPE_PRODUCT
            else -> throw IllegalArgumentException("Invalid data at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PLACE -> {
                val binding = ListPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PlaceViewHolder(binding)
            }
            VIEW_TYPE_PRODUCT -> {
                val binding = ListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProductViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_PLACE -> {
                val placeHolder = holder as PlaceViewHolder
                placeHolder.bind(dataSet[position] as WorkPlace)
            }
            VIEW_TYPE_PRODUCT -> {
                val productHolder = holder as ProductViewHolder
                val product = dataSet[position] as? Product ?: return
                productHolder.bind(product)
            }
            else -> throw IllegalArgumentException("Invalid view holder type")
        }
    }

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder.itemViewType) {
//            VIEW_TYPE_PLACE -> {
//                val placeHolder = holder as PlaceViewHolder
//                placeHolder.bind(dataSet[position] as WorkPlace)
//            }
//            VIEW_TYPE_PRODUCT -> {
//                val productHolder = holder as ProductViewHolder
//                val product = dataSet[position] as? Product ?: return
//                val dday = dataSet[position] as? Dday ?: return
//                productHolder.bind(product, dday)
//            }
//            else -> throw IllegalArgumentException("Invalid view holder type")
//        }
//    }

    fun setList(newList: MutableList<Any>) {
        this.dataSet = newList
        notifyDataSetChanged()
    }
}