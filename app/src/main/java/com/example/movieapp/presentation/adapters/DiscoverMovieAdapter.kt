package com.example.movieapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.MovieResponse
import com.example.movieapp.databinding.ItemMovieDiscoverBinding

class DiscoverMovieAdapter(private val listener: OnMovieListener) :
    RecyclerView.Adapter<DiscoverMovieAdapter.Viewholder>() {
    private var item: List<MovieResponse.MovieResult> = ArrayList()

    fun updateList(list: List<MovieResponse.MovieResult>) {
        item = list
    }

    inner class Viewholder(private val binding: ItemMovieDiscoverBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(movie: MovieResponse.MovieResult) {
            binding.tvTitle.text = movie.title
            binding.cpScore.text = String.format(
                "%.2f", movie.popularity
            )

            // Done to prevent error from loading null images
            if (movie.posterPath.isNotBlank()) {
                Glide.with(binding.ivMoviePoster.context)
                    .load("https://image.tmdb.org/t/p/original" + movie.posterPath)
                    .into(binding.ivMoviePoster)
            } else {
                Glide.with(binding.ivMoviePoster.context)
                    .load(R.drawable.ic_launcher_background)
                    .into(binding.ivMoviePoster)
            }

            binding.cvMovieCard.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            listener.onMovieClick(adapterPosition)
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

    interface OnMovieListener {
        fun onMovieClick(position: Int)
    }
}