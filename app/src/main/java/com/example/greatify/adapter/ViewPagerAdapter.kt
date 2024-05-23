package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.R

class ViewPagerAdapter(
    private val names: List<String>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolderClass>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_view_pager, parent, false)
        return ViewHolderClass(view)
    }

    override fun getItemCount(): Int {
        return  names.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentimage = names[position]
        holder.message.text = currentimage
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val message: TextView = itemView.findViewById(R.id.ivmessages)
    }
}