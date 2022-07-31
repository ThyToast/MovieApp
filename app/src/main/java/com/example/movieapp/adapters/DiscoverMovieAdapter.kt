package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.MovieResponse
import com.example.movieapp.databinding.ItemMovieDiscoverBinding

class DiscoverMovieAdapter(
    private val action: () -> Unit = {}
) : RecyclerView.Adapter<DiscoverMovieAdapter.Viewholder>() {
    private var item: List<MovieResponse.MovieResult> = ArrayList()

    fun updateList(list: List<MovieResponse.MovieResult>) {
        item = list
    }

    class Viewholder(val binding: ItemMovieDiscoverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponse.MovieResult) {
            binding.tvTitle.text = movie.title
            binding.cpScore.text = String.format(
                "%.2f", movie.popularity
            )
            if (movie.posterPath != null) {
                Glide.with(binding.ivMoviePoster.context)
                    .load("https://image.tmdb.org/t/p/original" + movie.posterPath)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.ivMoviePoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            ItemMovieDiscoverBinding.inflate(
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