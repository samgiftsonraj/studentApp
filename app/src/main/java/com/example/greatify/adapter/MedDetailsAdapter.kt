package com.example.greatify.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterMedDetailDesignBinding
import com.example.greatify.model.UserData
import com.example.greatify.view.fragments.MedicalReportFragment


class MedDetailsAdapter(
    private val userList: List<UserData>
): RecyclerView.Adapter<MedDetailsAdapter.MedDetailsViewHolder>(){

    inner class MedDetailsViewHolder( val itemBinding: AdapterMedDetailDesignBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: UserData) {

            itemBinding.name.setText(itemList.userName)
            itemBinding.dose.setText(itemList.Dosage)
          }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedDetailsViewHolder {
        return MedDetailsViewHolder(AdapterMedDetailDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: MedDetailsViewHolder, position: Int) {
        val fields = userList[position]
        holder.bind(itemList = fields)
            if(position == 0){
                val context = holder.itemView.context
                val intent = Intent(context, MedicalReportFragment::class.java)
                intent.putExtra("name", fields.userName)
                intent.putExtra("dose", fields.Dosage)
//                context.startActivity(intent)
//                holder.itemBinding.name.setText("Name")
//                holder.itemBinding.dose.setText("Dose")
//                change
            }
    }
}


