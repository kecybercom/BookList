package com.kelm.booklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(
    private val bookList: List<Book>,
    private val adapterOnClick: (Book) -> Unit
) :
    RecyclerView.Adapter<BookListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view, adapterOnClick)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        holder.setOnClickListener(book)
        holder.authorText.text = book.author
        holder.titleText.text = book.title
    }


    override fun getItemCount(): Int {
        return bookList.size
    }

    class ViewHolder(ItemView: View, val adapterOnClick: (Book) -> Unit) :
        RecyclerView.ViewHolder(ItemView) {

        val authorText: TextView = itemView.findViewById(R.id.authorText)
        val titleText: TextView = itemView.findViewById(R.id.titleText)

        fun setOnClickListener(book: Book) {
            itemView.setOnClickListener { adapterOnClick(book) }
        }

    }

}


