package com.werockstar.dagger2demo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.model.Repo

class RepoAdapter constructor(private val repositories: List<Repo>,
                              private val clickRepository: OnClickRepository)
    : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_row, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository, clickRepository)
    }

    override fun getItemCount(): Int = repositories.size

}
