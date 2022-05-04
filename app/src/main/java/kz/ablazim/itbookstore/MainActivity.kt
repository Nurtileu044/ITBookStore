package kz.ablazim.itbookstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kz.ablazim.itbookstore.core.ITBookStoreApp
import kz.ablazim.itbookstore.presentation.ITBooksAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as ITBookStoreApp).mainViewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ITBooksAdapter()
        recyclerView.adapter = adapter

        viewModel.observe(this) {
            adapter.update(it.books)
        }
        viewModel.fetchBooks()
        // TODO observe fail
    }
}