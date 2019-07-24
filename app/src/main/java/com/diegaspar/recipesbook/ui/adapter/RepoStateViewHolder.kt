package com.diegaspar.recipesbook.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.recipesbook.api.NetworkState
import kotlinx.android.synthetic.main.item_repo_state.view.*


class RepoStateViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(networkState: NetworkState?, callback: RecipeAdapter.OnClickListener) {
//        hideViews()
        setVisibleRightViews(networkState)
        itemView.repo_state_button.setOnClickListener { callback.onRetryClick() }
    }

//    private fun hideViews() {
//        itemView.repo_state_button.visibility = View.GONE
//        itemView.repo_error_msg.visibility = View.GONE
//        itemView.repo_progress_bar.visibility = View.GONE
//    }

    private fun setVisibleRightViews(networkState: NetworkState?) {
        when (networkState) {
            NetworkState.FAILED -> {
                itemView.repo_state_button.visibility = View.VISIBLE
                itemView.repo_error_msg.visibility = View.VISIBLE
            }
            NetworkState.RUNNING -> {
                itemView.repo_progress_bar.visibility = View.VISIBLE
            }
            NetworkState.SUCCESS -> {
                itemView.repo_state_button.visibility = View.GONE
                itemView.repo_error_msg.visibility = View.GONE
                itemView.repo_progress_bar.visibility = View.GONE
            } //do nothing
        }
    }
}