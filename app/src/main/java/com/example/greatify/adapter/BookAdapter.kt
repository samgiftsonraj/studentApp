package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.R
import com.example.greatify.model.recylerviewModelclass.Book

class BookAdapter(
    private val books: List<Book>
) : RecyclerView.Adapter<BookAdapter.BookViewHolderClass>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_book_card, parent, false)
        return BookViewHolderClass(view)
    }

    override fun getItemCount(): Int {
        return  books.size
    }

    override fun onBindViewHolder(holder: BookViewHolderClass, position: Int) {
        val bookimage = books[position]
        holder.bookimage.setImageResource(bookimage.bookImg)
        holder.bookname.text = bookimage.bookName
    }

    class BookViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val bookimage: ImageView = itemView.findViewById(R.id.bookImg)
        val bookname: TextView = itemView.findViewById(R.id.bookname)

    }
}