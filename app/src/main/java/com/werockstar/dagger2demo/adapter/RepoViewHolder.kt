package com.werockstar.dagger2demo.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView


import butterknife.BindView
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.model.Repo

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvName by lazy { view.findViewById(R.id.tvName) as TextView }
    val card by lazy { view.findViewById(R.id.card) as CardView }
    val tvLanguage by lazy { view.findViewById(R.id.tvLanguage) as TextView }

    fun bind(repo: Repo, clickRepository: OnClickRepository) {
        tvName.text = repo.nameRepo
        tvLanguage.text = repo.language

        card.setOnClickListener { clickRepository.onClickRepoItem(repo) }
    }
}
