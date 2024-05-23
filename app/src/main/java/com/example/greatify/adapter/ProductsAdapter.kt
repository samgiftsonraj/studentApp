package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterProductsCardBinding
import com.example.greatify.model.recylerviewModelclass.Products

class ProductsAdapter(
    private val productList : List<Products>
): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){
    inner class ProductViewHolder(val itemBinding: AdapterProductsCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind( itemList: Products){
            itemBinding.productImage.setImageResource(itemList.productImg)
            itemBinding.productName.text = itemList.productName
            itemBinding.offerPrice.text = itemList.offerPrice
            itemBinding.oringinalPrice.text = itemList.originalPrice
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = AdapterProductsCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
       return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
       holder.bind(itemList = product)
    }


}