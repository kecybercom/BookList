package com.kelm.booklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kelm.booklist.api.ApiService
import com.kelm.booklist.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        loadBooks()
    }

    private fun loadBooks() {
        val service = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = service.getBookList("5", "300", "5", "sv_SE")
        requestCall.enqueue(object : Callback<ApiData> {
            override fun onResponse(call: Call<ApiData>, response: Response<ApiData>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val apiResponseData = response.body()!!
                    Log.d("Response", "bookList size : ${apiResponseData.data.size}")
                    recyclerView.apply {
                        setHasFixedSize(true)
                        adapter = BookListAdapter(apiResponseData.data) { book ->
                            onListItemClick(
                                book
                            )
                        }
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

            override fun onFailure(call: Call<ApiData>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong $t",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun onListItemClick(book: Book) {
        val intent = Intent(this, BookDetailActivity()::class.java)
        val gson = Gson()
        intent.putExtra("identifier", gson.toJson(book))
        startActivity(intent)
    }
}

