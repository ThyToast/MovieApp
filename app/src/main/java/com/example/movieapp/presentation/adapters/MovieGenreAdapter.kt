package com.example.movieapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.MovieResponse
import com.example.movieapp.databinding.ItemMovieGenreBinding

class MovieGenreAdapter : RecyclerView.Adapter<MovieGenreAdapter.Viewholder>() {
    private var item: List<MovieResponse.MovieGenre> = ArrayList()

    fun updateList(list: List<MovieResponse.MovieGenre>) {
        item = list
    }

    class Viewholder(private val binding: ItemMovieGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponse.MovieGenre) {
            binding.cpGenre.text = movie.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            ItemMovieGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(item[position])
    }
}