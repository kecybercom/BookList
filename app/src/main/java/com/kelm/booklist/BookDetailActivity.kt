package com.kelm.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val author: TextView = findViewById(R.id.detailAuthor)
        val title: TextView = findViewById(R.id.detailTitle)
        val text: TextView = findViewById(R.id.detailText)
        val genre: TextView = findViewById(R.id.detailGenre)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val gson = Gson()
            val book = gson.fromJson(intent.getStringExtra("identifier"), Book::class.java)

            author.text = book.author
            title.text = book.title
            text.text = book.content
            genre.text = book.genre
        }
    }
}