package com.werockstar.dagger2demo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.model.RepoCollection

class RepoAdapter constructor(private val repositories: List<RepoCollection>,
                              private val clickRepository: OnClickRepository)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_row, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repository = repositories[position]
        val viewHolder = holder as RepoViewHolder
        viewHolder.tvName.text = repository.nameRepo
        viewHolder.tvLanguage.text = repository.language

        viewHolder.cvRepo.apply {
            setOnClickListener { clickRepository.onClickRepoItem(repository) }
        }
    }

    override fun getItemCount(): Int = repositories.size

    interface OnClickRepository {
        fun onClickRepoItem(repo: RepoCollection)
    }

}
