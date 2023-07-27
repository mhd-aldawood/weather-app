package com.mindorks.framework.mvvm.ui.main.adapter

import android.R.attr.data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt.data.db.Transformer.convertMainEntityToMainModel
import com.example.hilt.data.db.entity.MainEntity
import com.example.hilt.data.model.Main
import com.example.hilt.databinding.ItemLayoutBinding
import kotlinx.android.synthetic.main.item_layout.view.*


class MainAdapter(
    private var main: MutableList<Main>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
DataViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int = main.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(main[position])


    fun addData(main_:Main) {
        main.clear()
        main.add(main_)
        notifyDataSetChanged()
    }
    fun addDataFromDb(main_: List<MainEntity>) {
        var temp=main_.map { convertMainEntityToMainModel(it) }
        main= temp.toMutableList()
        notifyDataSetChanged()

    }
    class DataViewHolder(itemView: ItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(main: Main) {
            itemView.textViewTopLeft.text="Temperature: "+main.temp.toString()
            itemView.textViewCenter.text="Date: "+main.requestDAte.toString()

        }
    }
}