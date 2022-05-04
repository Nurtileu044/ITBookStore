package kz.ablazim.itbookstore.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.ablazim.itbookstore.R
import kz.ablazim.itbookstore.core.Book

class ITBooksAdapter : RecyclerView.Adapter<ITBooksAdapter.ITBooksViewHolder>() {

    private val books = ArrayList<Book>()

    fun update(list: List<Book>) {
        books.clear()
        books.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITBooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_layout, parent, false)
        // TODO: progress and fail
        return ITBooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: ITBooksViewHolder, position: Int) =
        holder.bind(books[position])

    override fun getItemCount() = books.size

    inner class ITBooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(book: Book) {
            itemView.findViewById<TextView>(R.id.textView).text = book.title
        }
    }
}