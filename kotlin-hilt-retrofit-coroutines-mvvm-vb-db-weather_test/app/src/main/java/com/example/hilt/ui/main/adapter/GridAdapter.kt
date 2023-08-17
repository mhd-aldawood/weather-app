package com.example.hilt.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt.data.model.Item
import com.example.hilt.databinding.CardviewItemBinding
import com.example.hilt.ui.main.interfaces.OnItemClick

class GridAdapter(private val items: List<Item>,var itemclicked:OnItemClick) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( CardviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,itemclicked)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: CardviewItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val cardView: CardView = itemView.cardView
        private val textView1: TextView = itemView.textView1
        private val textView2: TextView = itemView.textView2

        fun bind(item: Item, itemclicked: OnItemClick) {
            itemView.setOnClickListener(View.OnClickListener {
                itemclicked.onClick(item)
            })
        }
    }
}
