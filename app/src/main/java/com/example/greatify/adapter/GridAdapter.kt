package com.example.greatify.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.R
import com.example.greatify.databinding.AdapterGridCardBinding
import com.example.greatify.model.recylerviewModelclass.Gridclass
import com.example.greatify.view.activities.OtpActivity
import com.example.greatify.view.activities.TimeTableActivity


class GridAdapter(
    private val gridList : List<Gridclass>
): RecyclerView.Adapter<GridAdapter.GridViewHolder>(){

    inner class GridViewHolder(val itemBinding: AdapterGridCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: Gridclass){
            itemBinding.gridIcon1.setImageResource(itemList.icon1)
            itemBinding.gridIcon2 .setImageResource(itemList.icon2)
            itemBinding.categoryName.text = itemList.category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(AdapterGridCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return gridList.size
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val grid = gridList[position]
        holder.bind(itemList = grid)
        if(position ==6 || position ==7 ) holder.itemBinding.cardBorderBottom.visibility = View.INVISIBLE
        if(position ==2 || position ==5 ) holder.itemBinding.cardBorderRight.visibility = View.INVISIBLE
        if(position ==0){
                holder.itemBinding.categoryCard.setOnClickListener {
                    val context = holder.itemView.context
                    val intent = Intent(context, TimeTableActivity::class.java)
                    context.startActivity(intent)
            }

        }
    }


}