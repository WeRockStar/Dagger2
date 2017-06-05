package com.werocksta.dagger2demo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.werocksta.dagger2demo.R
import com.werocksta.dagger2demo.model.RepoCollection
import com.werocksta.dagger2demo.widget.CustomRecyclerView

class GithubRepoAdapter : CustomRecyclerView() {

    private var repositories: List<RepoCollection>? = null
    private lateinit var clickRepository: OnClickRepository

    fun setAdapter(repositories: List<RepoCollection>, clickRepository: OnClickRepository) {
        this.repositories = repositories
        this.clickRepository = clickRepository
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_row, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repository = repositories!![position]
        val viewHolder = holder as RepoViewHolder
        viewHolder.tvName.text = repository.nameRepo
        viewHolder.tvLanguage.text = repository.language

        viewHolder.cvRepo.setOnClickListener { v -> clickRepository.onClickRepoItem(repository) }
    }

    override fun getItemCount(): Int = repositories?.size ?: 0

    interface OnClickRepository {
        fun onClickRepoItem(repo: RepoCollection)
    }

}
